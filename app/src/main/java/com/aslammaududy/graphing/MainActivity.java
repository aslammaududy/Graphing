package com.aslammaududy.graphing;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {
    private LineGraphSeries<DataPoint> series;
    private GraphView graph;
    private EditText et_xAxis, et_yAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graph = (GraphView) findViewById(R.id.graph);
        et_xAxis = (EditText) findViewById(R.id.x_axis);
        et_yAxis = (EditText) findViewById(R.id.y_axis);

        DataPoint[] dataPoints = new DataPoint[400];
        DataPoint[] dataPoints1 = new DataPoint[400];
        double x = -100;
        double y = 0;

        //coordinate system x
        for (int i = 0; i < 400; i++) {
            dataPoints[i] = new DataPoint(x, y);
            x += 0.5;
        }

        x = 0;
        y = -100;
        for (int i = 0; i < 400; i++) {
            dataPoints1[i] = new DataPoint(x, y);
            y += 0.5;
        }
        graph.addSeries(series = new LineGraphSeries<>(dataPoints));
        graph.addSeries(series = new LineGraphSeries<>(dataPoints1));

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMaxY(100);
        graph.getViewport().setMinY(-100);
    }
}
