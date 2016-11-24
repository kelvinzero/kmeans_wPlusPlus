package com.jcotes.kmeans.data_obj;

import java.util.ArrayList;

/**
 * Holds a row of attributes stored in a typed ArrayList
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
        this._values = new ArrayList<T>();
        this._names = new ArrayList<>();
    }

    public void addValue(T d) {
        _values.add(d);
    }

    public void setValue(int loc, T value) {
        _values.set(loc, value);
    }

    public int getClusterIndex() {
        return _clusterIndex;
    }

    public void setClusterIndex(int i) {
        _clusterIndex = i;
    }

    public ArrayList<T> getValues() {
        return _values;
    }

    public void setValues(ArrayList<T> vals) {
        _values = vals;
    }

    public ArrayList<String> getNames() {
        return _names;
    }

    public void setNames(ArrayList<String> names) {
        _names = names;
    }

    public T getValue(int index) {
        return _values.get(index);
    }

    public int size() {
        return _values.size();
    }
}
