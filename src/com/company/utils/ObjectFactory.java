package com.company.utils;

import com.company.DataObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 11:46
 */
public class ObjectFactory {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Random random = new Random();
    private Map<Integer, DataObject> employees = new HashMap<>();
}
