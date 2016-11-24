package com.jcotes.kmeans.data_obj;

import java.util.ArrayList;

/**
 * Holds a row of attributes stored in a typed ArrayList
 *
 * @author Josh Cotes
 */
public class Record<T> {

    private ArrayList<T> _values;
    private ArrayList<String> _names;
    private int _clusterIndex;

    public Record(ArrayList<T> values, ArrayList<String> names) {
        this._values = values;
        this._names = names;
    }

    public Record(ArrayList<T> values) {
        this._values = values;
        this._names = new ArrayList<>();
    }

    public Record() {
        this._values = new ArrayList<>();
        this._names = new ArrayList<>();
    }

    /**
     * Adds a value to the record.
     *
     * @param d - the value
     */
    public void addValue(T d) {
        _values.add(d);
    }

    /**
     * Sets value at location loc to the given value.
     *
     * @param loc   - the index
     * @param value - the value
     */
    public void setValue(int loc, T value) {
        _values.set(loc, value);
    }

    /**
     * Get the cluster index.
     *
     * @return - the index
     */
    public int getClusterIndex() {
        return _clusterIndex;
    }

    /**
     * Set the index of the cluster associate with this record.
     * @param i - the index
     */
    public void setClusterIndex(int i) {
        _clusterIndex = i;
    }

    /**
     * Get the values in this record.
     * @return - the values
     */
    public ArrayList<T> getValues() {
        return _values;
    }

    /**
     * Set the values to the given parameter.
     * @param vals - the values
     */
    public void setValues(ArrayList<T> vals) {
        _values = vals;
    }

    /**
     * Get the names associated with this record.
     * @return - the names
     */
    public ArrayList<String> getNames() {
        return _names;
    }

    /**
     * Set the names associated with this record.
     * @param names - the list of names
     */
    public void setNames(ArrayList<String> names) {
        _names = names;
    }

    /**
     * Gets the value at the given index.
     * @param index - the index
     * @return - the value
     */
    public T getValue(int index) {
        return _values.get(index);
    }

    /**
     * Returns the size of the record
     * @return - the record size
     */
    public int size() {
        return _values.size();
    }
}
