package com.shkrub.onlineConverter.service.impl;

import com.shkrub.onlineConverter.entities.Rate;
import com.shkrub.onlineConverter.repositories.RateRepository;
import com.shkrub.onlineConverter.service.RateService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class RateServiceImpl implements RateService {
    private final RateRepository rateRepository;

    private enum Names {
        USD_BYN {
            @Override
            public String[] attr() {
                return new String[]{"USD", "покупка"};
            }
        },
        BYN_USD {
            @Override
            public String[] attr() {
                return new String[]{"USD", "продажа"};
            }
        },
        EUR_BYN {
            @Override
            public String[] attr() {
                return new String[]{"EUR", "покупка"};
            }
        },
        BYN_EUR {
            @Override
            public String[] attr() {
                return new String[]{"EUR", "продажа"};
            }
        },
        RUB_BYN {
            @Override
            public String[] attr() {
                return new String[]{"RUB", "покупка"};
            }
        },
        BYN_RUB {
            @Override
            public String[] attr() {
                return new String[]{"RUB", "продажа"};
            }
        },
        EUR_USD {
            @Override
            public String[] attr() {
                return new String[]{"EUR/USD", "покупка"};
            }
        },
        USD_EUR {
            @Override
            public String[] attr() {
                return new String[]{"EUR/USD", "продажа"};
            }
        };

        public abstract String[] attr();
    }

    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public List<Rate> getAllByDepartmentId(Long id) {
        return rateRepository.findAllByDepartmentId(id);
    }

    @Override
    public Double getProfitableValue(String from, String to) {
        List<Rate> rates = getAllByFromAndTo(from, to);
        return findProfitableRate(rates, rates.get(0).getOperation()).get(0).getValue();
    }

    @Override
    public Double getProfitableValueByCityId(String from, String to, Long id) {
        List<Rate> rates = getAllByFromAndTo(from, to);
        List<Rate> resultRates = new ArrayList<>();

        for(Rate rate : rates){
            if (rate.getValue() != 0) {
                if (rate.getDepartment().getCity().getId().equals(id)){
                    resultRates.add(rate);
                }
            }
        }
        return findProfitableRate(resultRates, resultRates.get(0).getOperation()).get(0).getValue();
    }

    private List<Rate> findProfitableRate(List<Rate> rates, String operation) {
        List<Rate> resultRates;
        if (operation.equals("продажа")) {
            resultRates = findProfitableRateForSold(rates);
        } else {
            resultRates = findProfitableRateForPurchase(rates);
        }
        return resultRates;
    }

    private List<Rate> findProfitableRateForSold(List<Rate> rates){
        double minVal = Double.MAX_VALUE;
        List<Rate> resultRates = new ArrayList<>();
        for (Rate rate : rates) {
            double value = rate.getValue();
            if (value < minVal) {
                minVal = value;
                resultRates.clear();
                resultRates.add(rate);
            } else if (value == minVal) {
                resultRates.add(rate);
            }
        }
        return resultRates;
    }

    private List<Rate> findProfitableRateForPurchase(List<Rate> rates) {
        double maxVal = 0;
        List<Rate> resultRates = new ArrayList<>();
        for(Rate rate : rates){
            double value = rate.getValue();
            if (value != 0 && value > maxVal){
                maxVal = value;
                resultRates.clear();
                resultRates.add(rate);
            } else if (value == maxVal){
                resultRates.add(rate);
            }
        }
        return resultRates;
    }

    private List<Rate> getAllByFromAndTo(String from, String to) {
        String attr[] = Names.valueOf((from + "_" + to).toUpperCase()).attr();
        return rateRepository.findAllByNameAndOperation(attr[0], attr[1]);
    }

    @Override
    @Transactional
    public void updateAll(List<Rate> newRates) {
        List<Rate> oldRates = (List<Rate>) rateRepository.findAll();
        ListIterator<Rate> listIterator = oldRates.listIterator();
        for (Rate newRate : newRates) {
            while (listIterator.hasNext()) {
                Rate oldRate = listIterator.next();
                if (newRate.equals(oldRate)) {
                    if (!oldRate.getValue().equals(newRate.getValue())) {
                        oldRate.setValue(newRate.getValue());
                    }
                }
            }
        }
    }

    @Override
    public void save(List<Rate> rates) {
        rateRepository.deleteAll();
        rateRepository.saveAll(rates);
    }
}
