package com.jcotes.kmeans.data_obj;

import java.util.ArrayList;

/**
 * Holds a collection of typed ArrayList records with associated utility methods.
 *
 * @author Josh Cotes
 */
public class Dataset<T> {

    private ArrayList<T> _records;

    public Dataset(ArrayList<T> _records) {
        this._records = _records;
    }

    public Dataset() {
        _records = new ArrayList<>();
    }

    /**
     * Adds a Record to the current collection of records
     *
     * @param record - the record
     */
    public void addRecord(T record) {
        _records.add(record);
    }

    /**
     * Getter retrieves the record at the given index
     *
     * @param index - the index
     * @return - the record
     */
    public T getRecord(int index) {
        return _records.get(index);
    }

    /**
     * Gets the size of the records list
     *
     * @return - the size
     */
    public int size() {
        return _records.size();
    }

    /**
     * Returns the records
     * @return - the records
     */
    public ArrayList<T> getRecordSet() {
        return _records;
    }
}
