package com.jcotes.kmeans.cluster;

import com.jcotes.kmeans.data_obj.Cluster;
import com.jcotes.kmeans.data_obj.Record;
import com.jcotes.kmeans.file_tools.fileIO;

import java.util.ArrayList;
import java.util.Random;

/**
 * KMeans uses KMeans++ to initialize clusters and Lloyd algorithm to cluster
 * the data.
 *
 * @author Josh Cotes
 * @version Homework 4
 */
public class KMeans {
    private ArrayList<Cluster> _clusters;
    private ArrayList<Record<Double>> _recordSet;
    private int _nClusters_K;
    private int _runs = 0;

    public KMeans(ArrayList<Record<Double>> records, int nClusters) {
        _recordSet = records;
        _nClusters_K = nClusters;
    }

    public KMeans(String fileName, int nClusters) {
        _recordSet = fileIO.loadDataSet(fileName);
        _nClusters_K = nClusters;
    }

    /**
     * Calculate sum of squared errors
     *
     * @return - The SSE
     */
    private double calculateSSE() {
        double sse = 0.0d;
        for (Cluster cluster : _clusters)
            sse += cluster.getSSE();
        return sse;
    }

    /**
     * Sets first clusters using KPP algorithm
     */
    private void initializeKPP() {

        ArrayList<Record<Double>> _recordsClone = (ArrayList<Record<Double>>) _recordSet.clone();
        Random rand = new Random();
        _clusters = new ArrayList<>();

        Cluster cluster = new Cluster();
        cluster.addRecord(_recordsClone.remove(Math.abs(rand.nextInt() % _recordsClone.size())));
        _clusters.add(cluster);

        int i = 0;
        for (; i < _nClusters_K; i++) {
            double sum = 0.0d;
            double[] distances = new double[_recordsClone.size()];
            for (int d = 0; d < _recordsClone.size(); d++) {
                Cluster closestC = nearestClusterCenter(_recordsClone.get(d));
                distances[d] = closestC.distanceToCentroid(_recordsClone.get(d));
                sum += distances[d];
            }
            sum *= rand.nextDouble();
            for (int c = 0; c < distances.length; c++) {
                sum -= distances[c];
                if (sum > 0) {
                    _recordsClone.get(c).setClusterIndex(i);
                    _clusters.add(new Cluster(_recordsClone.remove(c)));
                    break;
                }
            }
        }
        while (_recordsClone.size() > 0) {
            addRecordToClosestCluster(_recordsClone.remove(0));
        }
    }

    /**
     * Getter for the member variable _recordSet
     *
     * @return - the record set
     */
    public ArrayList<Record<Double>> getRecords() {
        return _recordSet;
    }

    private Cluster nearestClusterCenter(Record<Double> record) {
        Cluster closest = _clusters.get(0);
        for (Cluster cluster : _clusters) {
            if (cluster.distanceToCentroid(record) < closest.distanceToCentroid(record))
                closest = cluster;
        }
        return closest;
    }

    /**
     * Adds the given record to the cluster with the closest centroid, removing
     * the record from its cluster of origin.
     *
     * @param record - the object record
     */
    private void addRecordToClosestCluster(Record<Double> record) {

        Cluster closestCluster = _clusters.get(0);
        int closestClusterIndex = 0;

        for (int i = 0; i < _clusters.size(); i++) {
            if (_clusters.get(i).distanceToCentroid(record) < closestCluster.distanceToCentroid(record)) {
                closestCluster = _clusters.get(i);
                closestClusterIndex = i;
            }
        }
        record.setClusterIndex(closestClusterIndex);
        closestCluster.addRecord(record);
    }

    /**
     * Removes the Record[recordNumber] from the cluster and assigns it to
     * the cluster with the closest centroid.
     * @param cluster - cluster currently holding the record
     * @param recordNumber - record number
     */
    private void addRecordToClosestCluster(Cluster cluster, int recordNumber) {

        Cluster closestCluster = cluster;
        Record<Double> objectRecord = closestCluster.getRecords().get(recordNumber);
        int closestClusterIndex = _clusters.indexOf(cluster);

        for (int i = 0; i < _clusters.size(); i++) {
            // if cluster(i) centroid is closer than currently assigned centroid
            if (_clusters.get(i).distanceToCentroid(objectRecord) < closestCluster.distanceToCentroid(objectRecord)) {
                closestCluster = _clusters.get(i);
                closestClusterIndex = i;
            }
        }
        // only move the record if it is assigned to a new cluster
        if (!closestCluster.equals(cluster)) {
            cluster.getRecords().get(recordNumber).setClusterIndex(closestClusterIndex);
            closestCluster.addRecord(cluster.getRecords().remove(recordNumber));
        }
    }

    /**
     * Executes the Lloyd kmeans algorith.
     * @param maxIterations - maximum iterations (convergence breaks)
     * @return - the final clusters
     */
    public ArrayList<Cluster> doKMeans(int maxIterations) {

        _runs++;
        int i = 0;
        double lastSSE = 0.0;
        System.out.println("Assigning random clusters...");
        initializeKPP();
        System.out.println("Reassigning records to clusters to " + maxIterations + " max iterations or convergence...");

        while (i++ < maxIterations && lastSSE != calculateSSE()) {
            lastSSE = calculateSSE();
            for (Cluster cluster : _clusters) {
                for (int j = 0; j < cluster.getRecords().size(); j++) {
                    addRecordToClosestCluster(cluster, j);
                }
                cluster.setCentroid();
            }
        }
        System.out.println("KMeans complete, total iterations: " + i);

        // check for local optimum and adjust accordingly
        for (Cluster cluster : _clusters) {
            if (cluster.getRecords().size() < _recordSet.size() * .015) {
                System.out.println("\nLocal optimum cluster, re-running kmeans...\n");
                if (_runs <= 10) {
                    doKMeans(maxIterations);
                    break;
                } else
                    System.out.println("\nMaximum runs reached\n");
            }
        }
        return _clusters;
    }
}
