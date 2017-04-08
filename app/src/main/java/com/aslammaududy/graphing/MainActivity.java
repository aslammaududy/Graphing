package com.aslammaududy.graphing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {
    double xaxis, yaxis, xy = 10;
    private LineGraphSeries<DataPoint> coorSeries, series;
    private GraphView graph;
    private EditText et_xAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graph = (GraphView) findViewById(R.id.graph);
        et_xAxis = (EditText) findViewById(R.id.x_axis);
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

        series = new LineGraphSeries<>();
        graph.addSeries(series);

        //gambarkan koordinat x dan y(kartesius)
        coorSeries = new LineGraphSeries<>(dataPoints);
        coorSeries = new LineGraphSeries<>(dataPoints1);
        graph.addSeries(coorSeries);
        graph.addSeries(coorSeries);

        //kunci koordinat y supaya tidak berubah jika data terlalu besar
        //contoh: https://i.stack.imgur.com/LDCE7.png
        setKoordinat(xy);
    }

    private void setKoordinat(double xy) {
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMaxY(xy);
        graph.getViewport().setMinY(xy * (-1));

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(xy);
        graph.getViewport().setMinX(xy * (-1));
    }

    //method onclick tombol
    public void gambarOnClick(View view) {
        xaxis = Double.parseDouble(et_xAxis.getText().toString());

        xy = (xaxis * yaxis) / 2;
        setKoordinat(xy);

        series.resetData(gambarGrafGaris(xaxis, yaxis));
    }

    //method untuk menggambar grafik berdasarkan koordinat x dan y
    private DataPoint[] gambarGrafGaris(double x, double y) {

        //membuat batas minimum x dan y
        x = x + (-3 * (100 / 2));
        y = y + (-3 * (100 / 2));

        //mengambar grafik
        DataPoint[] points = new DataPoint[100];
        for (int i = 0; i < 100; i++) {
            points[i] = new DataPoint(x, y);
            x += 3;
            y += 3;
        }
        return points;
    }
}
