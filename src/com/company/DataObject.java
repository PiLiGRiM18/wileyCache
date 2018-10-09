package com.company;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class for caching.
 */
public class DataObject implements Serializable {

    private int key;
    private String value;

    public DataObject(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataObject that = (DataObject) o;
        return key == that.key &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "DataObject{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
