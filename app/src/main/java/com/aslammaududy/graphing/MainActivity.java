package com.aslammaududy.graphing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    double xy = 10;
    double xaxis, yaxis;
    private LineGraphSeries<DataPoint> coorSeries, series;
    private DataPoint[] points;
    private GraphView graph;
    private EditText et_fungsi;
    boolean klik = false;
    OperasiGrafik grafik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graph = (GraphView) findViewById(R.id.graph);
        et_fungsi = (EditText) findViewById(R.id.fungsi);
        et_fungsi.setKeyListener(null);
        // et_hasil = (EditText) findViewById(R.id.hasil);
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

        coorSeries.setColor(Color.GRAY);

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
        Argument argument = new Argument("x = 1");
        Expression fungsi = new Expression(et_fungsi.getText().toString(), argument);

        grafik = new OperasiGrafik(fungsi, argument);
        klik = true;
        if (grafik.isFungsiKuadrat(fungsi)) //untuk fungsi kuadrat
        {
            xaxis = grafik.Xs;
            yaxis = grafik.Yp;
            xy = (xaxis * yaxis);
            if (xy < 0) {
                xy *= -1;
            } else if (xy == 0) {
                xy = 7;
            }
            setKoordinat(xy);
            series.resetData(gambarFungsiKuadrat(grafik.xFK, grafik.yFK));
        }
        //untuk fungsi trigonometri
        else if (grafik.isFungsiTrigon(fungsi)) {
            grafik.cariFungsiTrigon();

            setKoordinat(7);
            series.resetData(gambarFungsiTrigon(grafik.xTrigon, grafik.yTrigon));
        }
        //untuk persamaan linier (y=mx+b)
        else {
            grafik.cariFungsiLinier();

            xy = grafik.tipotLinier.get(0.0) * 2;
            if (xy < 0) {
                xy *= -1;
            }
            setKoordinat(xy);
            series.resetData(gambarFungsiLinier(grafik.tipotLinier));
        }
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
        if (klik) {
            et_fungsi.setText("");
        }
        klik = false;

        switch (view.getId()) {
            case R.id.sin:
                urusInput("sin");
                break;
            case R.id.cos:
                urusInput("cos");
                break;
            case R.id.tan:
                urusInput("tan");
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
            case R.id.hapus:
                et_fungsi.setText("");
                break;
            case R.id.bksp:
                String teks = et_fungsi.getText().toString();
                if (teks.length() > 0) {
                    String teksBaru = teks.substring(0, teks.length() - 1);
                    et_fungsi.setText(teksBaru);
                }
                break;
        }
    }

    //method untuk menggambar grafik fungsi linier
    private DataPoint[] gambarFungsiLinier(HashMap<Double, Double> tipotLinier) {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        points = new DataPoint[200];
        for (Map.Entry e : tipotLinier.entrySet()) {
            x.add((Double) e.getKey());
            y.add((Double) e.getValue());
        }
        Collections.sort(x);
        Collections.sort(y);
        //mengambar grafik
        for (int i = 0; i < 200; i++) {
            points[i] = new DataPoint(x.get(i), y.get(i));
        }
        return points;
    }

    //method untuk menggambar grafik fungsi kuadrat
    private DataPoint[] gambarFungsiKuadrat(ArrayList<Double> xFK, ArrayList<Double> yFK) {
        points = new DataPoint[xFK.size()];
        //mengambar grafik
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(xFK.get(i), yFK.get(i));
        }
        return points;
    }

    private DataPoint[] gambarFungsiTrigon(ArrayList<Double> xTrigon, ArrayList<Double> yTrigon) {
        points = new DataPoint[xTrigon.size()];
        //menggambar grafik
        for (int i = 0; i < points.length; i++) {
            points[i] = new DataPoint(xTrigon.get(i), yTrigon.get(i));
        }
        return points;
    }
}