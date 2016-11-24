package com.jcotes;

import com.jcotes.cluster.KMeans;
import com.jcotes.data_obj.Cluster;
import com.jcotes.file_tools.fileIO;
import com.jcotes.visualize.LineChart;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        KMeans km = new KMeans("synthetic_control_data.txt", 6);
        ArrayList<Cluster> clusters = km.doKMeans(1000);

        LineChart linechart = new LineChart("Cluster Analysis", "Cluster", clusters.get(0));
        /*
        linechart.pack();
        RefineryUtilities.centerFrameOnScreen(linechart);
        linechart.setVisible(true);
*/
        LineChart linechart2 = new LineChart("Cluster Analysis", "Cluster", clusters.get(1));
  /*      linechart2.pack();
        RefineryUtilities.centerFrameOnScreen(linechart2);
        linechart2.setVisible(true);
*/
        LineChart linechart3 = new LineChart("Cluster Analysis", "Cluster", clusters.get(2));
  /*      linechart3.pack();
        RefineryUtilities.centerFrameOnScreen(linechart3);
        linechart3.setVisible(true);
*/
        LineChart linechart4 = new LineChart("Cluster Analysis", "Cluster", clusters.get(3));
  /*      linechart4.pack();
        RefineryUtilities.centerFrameOnScreen(linechart4);
        linechart4.setVisible(true);
*/
        LineChart linechart5 = new LineChart("Cluster Analysis", "Cluster", clusters.get(4));
  /*      linechart5.pack();
        RefineryUtilities.centerFrameOnScreen(linechart5);
        linechart5.setVisible(true);
*/
        LineChart linechart6 = new LineChart("Cluster Analysis", "Cluster", clusters.get(5));
  /*      linechart6.pack();
        RefineryUtilities.centerFrameOnScreen(linechart6);
        linechart6.setVisible(true);
*/
        JFrame frame = new JFrame("Cluster Analysis");
        frame.setLayout(new GridLayout(2, 3));
        frame.getContentPane().add(new ChartPanel(linechart.getChart()));
        frame.getContentPane().add(new ChartPanel(linechart2.getChart()));
        frame.getContentPane().add(new ChartPanel(linechart3.getChart()));
        frame.getContentPane().add(new ChartPanel(linechart4.getChart()));
        frame.getContentPane().add(new ChartPanel(linechart5.getChart()));
        frame.getContentPane().add(new ChartPanel(linechart6.getChart()));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        fileIO.writeClusterFile(km.getRecords());
    }
}
