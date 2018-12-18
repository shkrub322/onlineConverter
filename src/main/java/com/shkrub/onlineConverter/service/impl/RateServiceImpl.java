package com.shkrub.onlineConverter.service.impl;

import com.shkrub.onlineConverter.entities.Rate;
import com.shkrub.onlineConverter.repositories.RateRepository;
import com.shkrub.onlineConverter.service.RateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    private List<Rate> getAllByFromAndTo(String from, String to) {
        String attr[] = Names.valueOf((from + "_" + to).toUpperCase()).attr();
        List<Rate> rates = rateRepository.findAllByNameAndOperation(attr[0], attr[1]);
        if (attr[1].equals("продажа")) {
            for (Rate rate : rates) {
                if (rate.getValue() != 0){
                    rate.setValue((double) Math.round(Math.pow(rate.getValue(), -1) * 1000) / 1000);
                }
            }
        }
        return rates;
    }

    private List<Rate> findProfitableValue(List<Rate> rates) {
        List<Rate> resultRate = new ArrayList<>();
        double maxVal = 0;
        for (Rate rate : rates) {
            double value = rate.getValue();
            if (value != 0 && value > maxVal) {
                maxVal = value;
                resultRate.clear();
                resultRate.add(rate);
            } else if (value == maxVal) {
                resultRate.add(rate);
            }
        }
        return resultRate;
    }

    @Override
    public List<Rate> getProfitableValue(String from, String to) {
        List<Rate> rates = getAllByFromAndTo(from, to);
        return findProfitableValue(rates);
    }

    @Override
    public List<Rate> getProfitableValueByRegionId(String from, String to, Long regionId) {
        List<Rate> rates = getAllByFromAndTo(from, to);
        List<Rate> resultRates = new ArrayList<>();
        for (Rate rate : rates) {
            if (rate.getDepartment().getCity().getRegion().getId().equals(regionId)) {
                resultRates.add(rate);
            }
        }
        return findProfitableValue(resultRates);
    }

    @Override
    public List<Rate> getProfitableValueByCityId(String from, String to, Long cityId) {
        List<Rate> rates = getAllByFromAndTo(from, to);
        List<Rate> resultRates = new ArrayList<>();
        for (Rate rate : rates) {
            if (rate.getDepartment().getCity().getId().equals(cityId)) {
                resultRates.add(rate);
            }
        }
        return findProfitableValue(resultRates);
    }

    @Override
    public void updateAll(List<Rate> newRates) {
        for (Rate rate : newRates){
            rateRepository.save(rate);
        }
    }
}
