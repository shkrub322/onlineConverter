package com.shkrub.onlineConverter.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shkrub.onlineConverter.entities.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonParser {
    private static List<Region> regions;
    private static List<City> cities;
    private static List<Bank> banks;
    private static List<Department> departments;
    private static List<Rate> rates;
    private static String jsonRegions;
    private static String jsonCities;
    private static String jsonData;

    static {
        readJsonFile();
        convertJsonToObject();
    }

    private static void readJsonFile(){
        Scanner scannerRegions = null;
        Scanner scannerCitites = null;
        Scanner scannerData = null;
        try {
            FileReader regions = new FileReader("/home/shkrub322/IdeaProjects/coursework/onlineConverter/src/main/java/com/shkrub/onlineConverter/parser/json/regions.json");
            FileReader cities = new FileReader("/home/shkrub322/IdeaProjects/coursework/onlineConverter/src/main/java/com/shkrub/onlineConverter/parser/json/cities.json");
            FileReader data = new FileReader("/home/shkrub322/IdeaProjects/coursework/onlineConverter/src/main/java/com/shkrub/onlineConverter/parser/json/data.json");

            scannerRegions = new Scanner(regions);
            scannerCitites = new Scanner(cities);
            scannerData = new Scanner(data);

            scannerRegions.useDelimiter("\\Z");
            scannerCitites.useDelimiter("\\Z");
            scannerData.useDelimiter("\\Z");

            jsonRegions = scannerRegions.next();
            jsonCities = scannerCitites.next();
            jsonData = scannerData.next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scannerRegions != null) {
                scannerRegions.close();
            }
            if (scannerData != null) {
                scannerData.close();
            }
            if (scannerCitites != null) {
                scannerCitites.close();
            }
        }
    }

    private static void convertJsonToObject(){
        ObjectMapper objectMapper = new ObjectMapper();
        regions = new ArrayList<Region>();
        cities = new ArrayList<City>();
        banks = new ArrayList<Bank>();
        departments = new ArrayList<Department>();
        rates = new ArrayList<Rate>();

        try {
            JsonNode jsonNodeRegion = objectMapper.readValue(jsonRegions, JsonNode.class);
            JsonNode jsonNodeCities = objectMapper.readValue(jsonCities, JsonNode.class);
            JsonNode jsonNodeData = objectMapper.readValue(jsonData, JsonNode.class);

            for(JsonNode jsonNode : jsonNodeRegion){
                regions.add(new Region(jsonNode.get("region").asText()));
            }

            for (JsonNode jsonNode : jsonNodeCities){
                String regionName = jsonNode.get("region").asText();
                Region region = findEl(regions, regionName);
                if (region != null){
                    cities.add(new City(jsonNode.get("city").asText(), region));
                }
            }

            for (JsonNode jsonNode : jsonNodeData){
                String bankName = jsonNode.get("bank").asText();
                Bank bank = findEl(banks, bankName);
                if (bank == null){
                    banks.add(new Bank(bankName));
                }
            }
            Collections.sort(banks);

            for (JsonNode jsonNode : jsonNodeData){
                String cityName = jsonNode.get("city").asText();
                String bankName = jsonNode.get("bank").asText();
                String depName = jsonNode.get("name").asText();
                String address = jsonNode.get("address").asText();
                City city = findEl(cities, cityName);
                Bank bank = findEl(banks, bankName);
                Department department = null;
                if (city != null && bank != null){
                    department = new Department(depName, address, city, bank);
                    departments.add(department);
                }
                JsonNode jsonNodeRates = jsonNode.get("rate");
                for (JsonNode rate: jsonNodeRates){
                    Iterator<String> it = rate.fieldNames();
                    String currency = it.next();
                    rates.add(new Rate(currency, rate.get("operation").asText(), rate.get(currency).asDouble(), department));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<City> getCities() {
        return cities;
    }

    public static List<Bank> getBanks() {
        return banks;
    }

    public static List<Department> getDepartments() {
        return departments;
    }

    public static List<Region> getRegions() {
        return regions;
    }

    public static List<Rate> getRates() {
        return rates;
    }

    private static  <T> T findEl(List<T> list, Object elements) {
        for (T el: list){
            if (el.equals(elements)){
                return el;
            }
        }
        return null;
    }
}
