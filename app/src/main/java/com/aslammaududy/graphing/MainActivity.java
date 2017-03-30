package com.aslammaududy.graphing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {
    double xaxis, yaxis;
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

        //sistem koordinat x
        for (int i = 0; i < 400; i++) {
            dataPoints[i] = new DataPoint(x, y);
            x += 10;
        }

        //sistem koordinat y
        x = 0;
        y = -100;
        for (int i = 0; i < 400; i++) {
            dataPoints1[i] = new DataPoint(x, y);
            y += 10;
        }

        //gambarkan koordinat x dan y(kartesius)
        graph.addSeries(series = new LineGraphSeries<>(dataPoints));
        graph.addSeries(series = new LineGraphSeries<>(dataPoints1));

        //kunci koordinat y supaya tidak berubah jika data terlalu besar
        //contoh: https://i.stack.imgur.com/LDCE7.png
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMaxY(50);
        graph.getViewport().setMinY(-50);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(50);
        graph.getViewport().setMinX(-50);
    }

    //method onclick tombol
    public void gambarOnClick(View view) {
        xaxis = Double.parseDouble(et_xAxis.getText().toString());
        yaxis = Double.parseDouble(et_yAxis.getText().toString());

        gambarGrafGaris(xaxis, yaxis);
    }

    //method untuk menggambar grafik berdasarkan koordinat x dan y
    private void gambarGrafGaris(double x, double y) {
        double d = x * y;
        int jum = (int) d;

        //membuat batas minimum x dan y
        x = x + (-3 * (100 / 2));
        y = y + (-3 * (100 / 2));

        //mengambar grafik
        DataPoint[] point = new DataPoint[100];
        for (int i = 0; i < 100; i++) {
            point[i] = new DataPoint(x, y);
            x += 3;
            y += 3;
        }
        graph.addSeries(series = new LineGraphSeries<>(point));
        series.resetData(point);
    }
}
