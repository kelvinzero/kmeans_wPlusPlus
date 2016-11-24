package com.jcotes.kmeans.data_obj;

import java.util.ArrayList;

/**
 * Cluster holding a record and its data.
 */
public class Cluster {

    private ArrayList<Record<Double>> _records;
    private Record<Double> _centroid;
    private boolean _centroidUpdated;

    public Cluster(ArrayList<Record<Double>> records) {
        this._records = records;
        _centroidUpdated = false;
    }

    public Cluster() {
        _records = new ArrayList<>();
        _centroidUpdated = false;
    }

    public Cluster(Record<Double> initialCenter) {
        _records = new ArrayList<>();
        _centroid = initialCenter;
        _records.add(initialCenter);
        _centroidUpdated = true;
    }

    public Record getCentroid() {
        if (!_centroidUpdated)
            setCentroid();
        return _centroid;
    }

    public ArrayList<Record<Double>> getRecords() {
        return _records;
    }

    public void addRecord(Record<Double> record) {
        _records.add(record);
        _centroidUpdated = false;
    }

    public void setCentroid() {

        _centroid = new Record<>();
        if (_records.size() == 1) {
            _centroid.setValues(_records.get(0).getValues());
            return;
        }
        if (_records.size() == 0) {
            System.out.println("Record set is empty, cannot calculate centroid.");
            return;
        }

        for (int i = 0; i < _records.get(0).size(); i++) // set the centroid attributes values to 0.0d O(a)
            _centroid.addValue(0.0d);

        for (Record<Double> record : _records) { // accumulate the totals for each attribute in the centroid values O(an)
            for (int i = 0; i < _centroid.size(); i++)
                _centroid.setValue(i, _centroid.getValue(i) + record.getValue(i));
        }
        for (int i = 0; i < _centroid.size(); i++) // divide each centroid attribute value by N O(a)
            _centroid.setValue(i, _centroid.getValue(i) / _records.size());
        _centroidUpdated = true;
    }

    private double getEuclideanDistance(Record<Double> recordOne, Record<Double> recordTwo) {
        double distance = 0.0d;
        for (int i = 0; i < recordOne.size(); i++)
            distance += Math.pow((recordOne.getValue(i) - recordTwo.getValue(i)), 2);
        return Math.sqrt(distance);
    }

    public double distanceToCentroid(Record record) {
        if (!_centroidUpdated)
            setCentroid();
        return getEuclideanDistance(record, _centroid);
    }

    private double calculateSquareError(Record record) {
        if (!_centroidUpdated)
            setCentroid();
        double squaredError = 0.0d;
        for (int i = 0; i < _centroid.size(); i++)
            squaredError += Math.pow((Double) record.getValue(i) - _centroid.getValue(i), 2);
        return squaredError;
    }

    public double getSSE() {
        double SSE = 0.0d;
        for (Record _record : _records)
            SSE += calculateSquareError(_record);
        return SSE;
    }
}
