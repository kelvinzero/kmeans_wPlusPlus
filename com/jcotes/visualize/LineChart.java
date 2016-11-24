package com.jcotes.visualize;


import com.jcotes.data_obj.Cluster;
import com.jcotes.data_obj.Record;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class LineChart extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFreeChart _lineChart;
    private int _key = 0;

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
        // ChartPanel chartPanel = new ChartPanel(linechart);
        // settind default size
        // chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));
        XYPlot plot = (XYPlot) _lineChart.getPlot();
        for (int i = 0; i < plot.getSeriesCount(); i++) {
            plot.getRenderer().setSeriesPaint(i, new Color(80, 40, 255));
        }

        plot.getDomainAxis().setAutoRange(true);
        plot.getRangeAxis().setRange(-20.0d, 80.0d);

        // add to contentPane
        // setContentPane(chartPanel);
    }

    public JFreeChart getChart() {
        return _lineChart;
    }

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