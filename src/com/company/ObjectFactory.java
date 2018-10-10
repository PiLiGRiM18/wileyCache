package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Factory gives random DataObject map
 */
public class ObjectFactory {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Random random = new Random();
    private Map<Integer, DataObject> dataObjectMap = new HashMap<>();

    public ObjectFactory(int objects) {
        for (int i = 0; i < objects; i++) {
            int key = getRandomKey(objects);
            DataObject dataObject = getRandomDataObject(key);
            dataObjectMap.put(key, dataObject);
        }
    }

    private DataObject getRandomDataObject(int key) {
        return new DataObject(key, getRandomValue());
    }

    private String getRandomValue() {
        StringBuilder sB = new StringBuilder();
        sB.append("DataObject: ");
        for (int i = 0; i < 10; i++) {
            sB.append(getRandomString());
        }
        return sB.toString();
    }

    private char getRandomString() {
        return ALPHABET.charAt(random.nextInt(ALPHABET.length()));
    }

    private int getRandomKey(int keys) {
        int key;
        do {
            key = random.nextInt(keys) + 1;
        } while (dataObjectMap.containsKey(key));
        return key;
    }

    @Override
    public String toString() {
        return "ObjectFactory{" +
                "dataObjectMap=\n" + dataObjectMap.toString().replace("},", "},\n") +
                "}";
    }
}
