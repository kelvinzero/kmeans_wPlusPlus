package com.jcotes.kmeans.file_tools;

import com.jcotes.kmeans.data_obj.Cluster;
import com.jcotes.kmeans.data_obj.Record;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * File IO utility to handle loading records and writing cluster info
 *
 * @author Josh Cotes
 */
public class fileIO {

    /**
     * Loads a data set from a given file name, expected regex delmeter is "  *"
     *
     * @param fileName - the file name
     * @return - an ArrayList of Record<Double> type
     */
    public static ArrayList<Record<Double>> loadDataSet(String fileName) {

        ArrayList<Record<Double>> recordSet = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader relationStream = new BufferedReader(isr);
            String line;

            while ((line = relationStream.readLine()) != null) {
                if (!line.equals("")) {
                    String[] row = line.split("  *");
                    Record<Double> newRecord = new Record<>();
                    ArrayList<Double> values = new ArrayList<>();
                    newRecord.setValues(values);
                    Arrays.stream(row).forEach(C -> values.add(Double.parseDouble(C)));
                    recordSet.add(newRecord);
                }
            }
            inputStream.close();
            isr.close();
            relationStream.close();
            return recordSet;
        } catch (Exception $fnfx) {
            $fnfx.printStackTrace();
            return null;
        }
    }

    /**
     * Writes a file containing info about each record and the cluster containing it
     * in the format <record#, cluster#>
     *
     * @param records  - the ArrayList of records
     * @param filename - the filename to load
     */
    public static void writeRecordInfoFile(ArrayList<Record<Double>> records, String filename) {
        try {

            File file = new File(filename);
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter fout = new BufferedWriter(writer);
            for (int r = 0; r < records.size(); r++) {
                fout.write("<" + (r + 1) + ", " + (records.get(r).getClusterIndex() + 1) + ">\n");
            }
            fout.close();
        } catch (Exception ex$) {
            ex$.printStackTrace();
        }
    }

    /**
     * Writes an individual file for each cluster, each file containing all records in
     * that cluster.
     *
     * @param clusters - the ArrayList of clusters
     */
    public static void writeClusterFiles(ArrayList<Cluster> clusters) {

        try {
            for (int i = 0; i < clusters.size(); i++) {
                File file = new File("./cluster" + (i + 1) + "out.txt");
                FileWriter writer = new FileWriter(file.getAbsoluteFile());
                BufferedWriter fout = new BufferedWriter(writer);
                ArrayList<Record<Double>> records = clusters.get(i).getRecords();

                for (Record<Double> record : records) {
                    for (int c = 0; c < record.size(); c++) {
                        fout.write(record.getValue(c).toString() + " ");
                    }
                    fout.write("\n");
                }
                fout.close();
            }
        } catch (Exception ex$) {
            ex$.printStackTrace();
        }
    }
}
