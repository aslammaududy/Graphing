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
    private EditText et_fungsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graph = (GraphView) findViewById(R.id.graph);
        et_fungsi = (EditText) findViewById(R.id.fungsi);
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

    //method onclick tombol gambar
    public void gambarOnClick(View view) {
        xaxis = Double.parseDouble(et_fungsi.getText().toString());

        xy = (xaxis * yaxis) / 2;
        setKoordinat(xy);

        series.resetData(gambarGrafGaris(xaxis, yaxis));
    }

    //method untuk urus input ke dalam layar fungsi
    private void urusInput(String input) {
        et_fungsi = (EditText) findViewById(R.id.fungsi);
        String teks = et_fungsi.getText().toString();
        teks += input;
        et_fungsi.setText(teks);
    }

    //method onclick keypad
    public void keypadOnClick(View view) {
        switch (view.getId()) {
            case R.id.sin:
                urusInput("sin(");
                break;
            case R.id.cos:
                urusInput("cos(");
                break;
            case R.id.tan:
                urusInput("tan(");
                break;
            case R.id.x:
                urusInput("x");
                break;
            case R.id.y:
                urusInput("y");
                break;
            case R.id.pow:
                urusInput("^");
                break;
            case R.id.bagi:
                urusInput("/");
                break;
            case R.id.kali:
                urusInput("*");
                break;
            case R.id.kurang:
                urusInput("-");
                break;
            case R.id.tambah:
                urusInput("+");
                break;
            case R.id.krg_bka:
                urusInput("(");
                break;
            case R.id.krg_ttp:
                urusInput(")");
                break;
            case R.id.koma:
                urusInput(".");
                break;
            case R.id.nol:
                urusInput("0");
                break;
            case R.id.satu:
                urusInput("1");
                break;
            case R.id.dua:
                urusInput("2");
                break;
            case R.id.tiga:
                urusInput("3");
                break;
            case R.id.empat:
                urusInput("4");
                break;
            case R.id.lima:
                urusInput("5");
                break;
            case R.id.enam:
                urusInput("6");
                break;
            case R.id.tujuh:
                urusInput("7");
                break;
            case R.id.delapan:
                urusInput("8");
                break;
            case R.id.sembilan:
                urusInput("9");
                break;
        }
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
