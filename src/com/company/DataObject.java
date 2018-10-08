package com.company;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class for caching.
 */
public class DataObject implements Serializable {

    private int id;
    private String data;

    public DataObject(int id, String name) {
        this.id = id;
        this.data = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataObject that = (DataObject) o;
        return id == that.id &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    @Override
    public String toString() {
        return "DataObject{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
