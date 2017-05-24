package com.aslammaududy.graphing;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.parsertokens.Token;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aslammaududy on 17/04/17.
 */

public class OperasiGrafik {
    private ArrayList<Token> token;
    ArrayList<Double> xFK, yFK, xTrigon, yTrigon;
    HashMap<Double, Double> tipotLinier = new HashMap<>();
    double amp, Yg, Xs, Yp;
    String s;
    private Expression exp;
    private Argument arg;

    public OperasiGrafik(Expression ekspresi, Argument argument) {
        exp = ekspresi;
        arg = argument;
        s = exp.getExpressionString();
    }

    public void cariFungsiLinier() {
        //menentukan titik potong x dan y
        for (int i = -100; i < 100; i++) {
            double d = i;
            arg.setArgumentValue(d);
            Yg = exp.calculate();
            tipotLinier.put(d, Yg);
        }
    }

    public void cariFungsiKuadrat() {
        ArrayList<Double> akar = new ArrayList<>();
        xFK = new ArrayList<>();
        yFK = new ArrayList<>();
        double j = -10;
        //menentukan titik potong x dan y
        for (int i = 0; i < 100; i++) {
            arg.setArgumentValue(j);
            Yp = exp.calculate();
            xFK.add(i, j);
            yFK.add(i, Yp);
            
        /*Mencari akar-akar fungsi kuadrat
        * dengan cara loop nilai x
        * yang jika dikalkulasikan menghasilkan 0*/
            if (Yp == 0) {
                akar.add(j);
            }
            j += 0.25;
        }

        /* Mencari nilai x simetris
        *  jika hanya terdapat 1 akar maka kalikan dengan 2 dan dibagi 2
        *  jika terdapat 2 akar tambahkan kedua akar tersebut kemudian bagi dengan 2*/
        if (akar.size() < 2) {
            Xs = (akar.get(0) * 2) / 2;
        } else {
            Xs = (akar.get(0) + akar.get(1)) / 2;
        }

        /* Mencari nilai y puncak dengan cara
        *  mensubtitusikan nilai x simetris ke fungsi
        *  Yp=f(Xs)*/
        arg.setArgumentValue(Xs);
        Yp = exp.calculate();
    }

    public void cariFungsiTrigon() {
        xTrigon = new ArrayList<>();
        yTrigon = new ArrayList<>();
        double j = -180;

        for (int i = 0; i < 3600; i++) {
            arg.setArgumentValue(j);
            amp = exp.calculate();
            xTrigon.add(i, j);
            yTrigon.add(i, amp);
            j += 0.5;
        }
    }

    boolean isFungsiKuadrat(Expression e) {
        boolean fungsiKuadrat = false;

        /*memotong ekspresi matematika
        * contoh: x^2-2
        * menjadi: 'x', '^', '2', '-', '2' */
        token = e.getCopyOfInitialTokens();

        for (Token t : token) {
            if (t.keyWord.equals("^")) {
                fungsiKuadrat = true;
            }
        }
        return fungsiKuadrat;
    }

    boolean isFungsiTrigon(Expression e) {
        String kw;
        boolean trigonometri = false;
        /*memotong ekspresi matematika
        * contoh: x^2-2
        * menjadi: 'x', '^', '2', '-', '2' */
        token = e.getCopyOfInitialTokens();

        for (Token t : token) {
            kw = t.keyWord;
            if (kw.equals("sin") || kw.equals("cos") || kw.equals("tan")) {
                trigonometri = true;
            }
        }
        return trigonometri;
    }
}
