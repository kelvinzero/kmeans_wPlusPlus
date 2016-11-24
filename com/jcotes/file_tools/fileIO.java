package com.jcotes.file_tools;

import com.jcotes.data_obj.Record;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Josh on 11/20/2016.
 */
public class fileIO {

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

    public static void writeClusterFile(ArrayList<Record<Double>> records) {
        try {

            File file = new File("./recordclusterinfo.txt");
            FileWriter writer = new FileWriter(file.getAbsoluteFile());
            BufferedWriter fout = new BufferedWriter(writer);
            for (int r = 0; r < records.size(); r++) {
                fout.write("<" + r + 1 + ", " + (records.get(r).getClusterIndex() + 1) + ">\n");
            }
            fout.close();
        } catch (Exception ex$) {
            ex$.printStackTrace();
        }
    }

    public static void writeClusterFiles(int k, ArrayList<Record<Double>> records) {
/*
        try {

                File file = new File("./cluster" + i + "out.txt");
                FileWriter writer = new FileWriter(file.getAbsoluteFile());
                BufferedWriter fout = new BufferedWriter(writer);
                for(int r = 0; r < records.size(); r++) {
                    fout.write("<" + r + ", " + records.get(r).getClusterIndex() + ">");
                }
                fout.close();
            }
        }
        catch (Exception ex$){
            ex$.printStackTrace();
        }
    */
    }

}
