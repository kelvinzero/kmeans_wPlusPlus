package com.jcotes.kmeans;

import com.jcotes.kmeans.cluster.KMeans;
import com.jcotes.kmeans.data_obj.Cluster;
import com.jcotes.kmeans.file_tools.fileIO;
import com.jcotes.kmeans.visualize.LineChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DemoCluster {

    private static final String DEFAULTFILE = "./synthetic_control_data.txt";
    private static int DEFAULTITERATIONS = 500;
    private static int _K = 6;

    public static void main(String[] args) {

        KMeans km = null;

        if (args.length == 1) {
            System.out.println("loading data set from file " + args[0]);
            km = new KMeans(args[0], 6);
        } else if (args.length == 2) {
            System.out.println("loading data set from file " + args[0]);
            km = new KMeans(args[0], Integer.parseInt(args[1]));
            _K = Integer.parseInt(args[1]);
        } else if (args.length == 3) {
            System.out.println("loading data set from file " + args[0]);
            km = new KMeans(args[0], Integer.parseInt(args[1]));
            _K = Integer.parseInt(args[1]);
            DEFAULTITERATIONS = Integer.parseInt(args[2]);
        } else {
            System.out.println("too many or no args, default run");
            System.out.println("loading data set from file " + DEFAULTFILE);
            km = new KMeans(DEFAULTFILE, _K);
        }

        System.out.println("assigning k = " + _K + " clusters...");
        ArrayList<Cluster> clusters = km.doKMeans(DEFAULTITERATIONS);
        ArrayList<LineChart> clusterCharts = new ArrayList<>();
        for (int i = 0; i < clusters.size(); i++) {
            clusterCharts.add(new LineChart("Cluster Analysis", "Cluster " + (i + 1), clusters.get(i)));
        }

        JFrame frame = new JFrame("Cluster Analysis");
        frame.setLayout(new GridLayout(2, 3));

        for (LineChart chart : clusterCharts) {
            frame.getContentPane().add(new ChartPanel(chart.getChart()));
        }

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        fileIO.writeClusterFile(km.getRecords(), "rowclusternfo.txt");
        fileIO.writeClusterFiles(clusters);
    }
}
