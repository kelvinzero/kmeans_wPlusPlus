package com.jcotes.data_obj;

import java.util.ArrayList;

/**
 * Holds a collection of typed ArrayList records with associated utility methods.
 *
 * @author Josh Cotes
 */
public class Dataset<T> {

    ArrayList<T> _records;

    public Dataset(ArrayList<T> _records) {
        this._records = _records;
    }

    public Dataset() {
        _records = new ArrayList<>();
    }

    public void addRecord(T record) {
        _records.add(record);
    }

    public T getRecord(int index) {
        return _records.get(index);
    }

    public int size() {
        return _records.size();
    }

    public ArrayList<T> getRecordSet() {
        return _records;
    }
}
