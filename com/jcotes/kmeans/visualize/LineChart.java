package com.jcotes.kmeans.visualize;

import com.jcotes.kmeans.data_obj.Cluster;
import com.jcotes.kmeans.data_obj.Record;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * Builds a LineChart using the JFreeChart open source JFrame graphing utility.
 * Charts are built using info from a given cluster and its records.
 *
 * @author Josh Cotes
 */
public class LineChart extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFreeChart _lineChart;
    private int _key = 0;

    /**
     * Builds a new JFreeChart from a given cluster.
     *
     * @param applicationTitle - title shown in the window
     * @param chartTitle       - title shown above the chart
     * @param cluster          - the cluster to graph
     */
    public LineChart(String applicationTitle, String chartTitle, Cluster cluster) {
        super(applicationTitle);

        // based on the dataset we create the chart
        _lineChart = ChartFactory
                .createXYLineChart(
                        chartTitle,
                        "Point", "Value",
                        createDataset(cluster),
                        PlotOrientation.VERTICAL,
                        false, false, false);


        // Adding chart into a chart panel
        XYPlot plot = (XYPlot) _lineChart.getPlot();
        for (int i = 0; i < plot.getSeriesCount(); i++) {
            plot.getRenderer().setSeriesPaint(i, new Color(80, 50, 255));
        }
        plot.getDomainAxis().setAutoRange(true);
        plot.getRangeAxis().setRange(-20.0d, 80.0d);
    }

    /**
     * Getter for the _lineChart member variable
     *
     * @return - the lineChart
     */
    public JFreeChart getChart() {
        return _lineChart;
    }

    /**
     * Creates a dataset from all of the records in the given cluster
     *
     * @param cluster - the cluster
     * @return - the XYDataset containing the dataSet
     */
    private XYDataset createDataset(Cluster cluster) {

        XYSeries series;
        XYSeriesCollection collection = new XYSeriesCollection();
        for (Record record : cluster.getRecords()) {
            series = new XYSeries(_key++ + "");
            for (int i = 0; i < record.getValues().size(); i++) {
                series.add(new Integer(i), (Double) record.getValue(i));
            }
            collection.addSeries(series);
        }
        return collection;
    }
}