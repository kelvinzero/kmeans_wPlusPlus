package com.jcotes.kmeans;

import com.jcotes.kmeans.cluster.KMeans;
import com.jcotes.kmeans.data_obj.Cluster;
import com.jcotes.kmeans.file_tools.fileIO;
import com.jcotes.kmeans.visualize.LineChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Driver file to execute kmeans clustering algorithm
 *
 * @author Josh Cotes
 */
public class DemoCluster {

    private static final String DEFAULTFILE = "./synthetic_control_data.txt";
    private static int defaultIterations = 500;
    private static int kCount = 6;


    /**
     * Interprets args and executes kmeans algorithm. Renders JFrame with line graphs
     * for each cluster, drawing line graphs for each record in the cluster.
     * Program arguments:
     * <p>
     * kmeans.jar                                           - executes using default paramaters
     * kmeans.jar <filename> <k - clusters>                 - executes using default iterations
     * kmeans.jar <filename> <k - clusters> <iterations>    - executes using params from args
     *
     * @param args - the program arguments
     */
    public static void main(String[] args) {

        KMeans km;

        if (args.length == 1) {
            System.out.println("loading data set from file " + args[0]);
            km = new KMeans(args[0], 6);
        } else if (args.length == 2) {
            System.out.println("loading data set from file " + args[0]);
            km = new KMeans(args[0], Integer.parseInt(args[1]));
            kCount = Integer.parseInt(args[1]);
        } else if (args.length == 3) {
            System.out.println("loading data set from file " + args[0]);
            km = new KMeans(args[0], Integer.parseInt(args[1]));
            kCount = Integer.parseInt(args[1]);
            defaultIterations = Integer.parseInt(args[2]);
        } else {
            System.out.println("too many or no args, default run");
            System.out.println("loading data set from file " + DEFAULTFILE);
            km = new KMeans(DEFAULTFILE, kCount);
        }

        System.out.println("assigning k = " + kCount + " clusters...");
        ArrayList<Cluster> clusters = km.doKMeans(defaultIterations);
        ArrayList<LineChart> clusterCharts = new ArrayList<>();

        for (int i = 0; i < clusters.size(); i++)
            clusterCharts.add(new LineChart("Cluster Analysis", "Cluster " + (i + 1), clusters.get(i)));

        JFrame frame = new JFrame("Cluster Analysis");
        frame.setLayout(new GridLayout(2, 3));

        for (LineChart chart : clusterCharts)
            frame.getContentPane().add(new ChartPanel(chart.getChart()));

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        fileIO.writeRecordInfoFile(km.getRecords(), "resultfile.txt");
        fileIO.writeClusterFiles(clusters);
    }
}
