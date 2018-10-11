package com.company;

import java.util.*;

/**
 * Source with random DataObject
 */
public class DataObjectSource {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Random random = new Random();
    private Map<Integer, DataObject> dataObjectMap = new HashMap<>();
    private List<Integer> keys = new ArrayList<>();


    public DataObjectSource(int objectsNumber) {
        for (int i = 0; i < objectsNumber; i++) {
            int key = generateRandomKey(objectsNumber);
            DataObject dataObject = generateRandomDataObject(key);
            dataObjectMap.put(key, dataObject);
            keys.add(key);
        }
    }

    public Integer getRandomKey() {
        int index = random.nextInt(keys.size());
        return keys.get(index);
    }

    public DataObject getObject(Integer key) {
        return dataObjectMap.get(key);
    }

    private DataObject generateRandomDataObject(int key) {
        return new DataObject(key, generateRandomValue());
    }

    private String generateRandomValue() {
        StringBuilder sB = new StringBuilder();
        sB.append("DataObject: ");
        for (int i = 0; i < 10; i++) {
            sB.append(generateRandomString());
        }
        return sB.toString();
    }

    private char generateRandomString() {
        return ALPHABET.charAt(random.nextInt(ALPHABET.length()));
    }

    private int generateRandomKey(int maxNumberKeys) {
        int key;
        do {
            key = random.nextInt(maxNumberKeys) + 1;
        } while (dataObjectMap.containsKey(key));
        return key;
    }

    @Override
    public String toString() {
        return "DataObjectSource{" +
                "dataObjectMap=\n" + dataObjectMap.toString().replace("},", "},\n") +
                "}";
    }
}
