package com.mycompany.signin;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class monthlygraph extends AppCompatActivity {

    MyDBHandler dbHandler;
    int[] xaxis = new int[12];
    float[] yaxis = new float[12];


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthlygraph);
        dbHandler = new MyDBHandler(this, null, null, 2);


       BarChart chart = (BarChart) findViewById(R.id.monthlychart);

       BarData data = new BarData(getXAxisValues(), getDataSet());
       chart.setData(data);
       chart.setDescription("Expenditure So far");
       chart.animateXY(2000, 2000);
       chart.invalidate();

    }

    private ArrayList<BarDataSet> getDataSet() {

        yaxis = dbHandler.Amountgetmonthlydata();
        xaxis = dbHandler.Monthgetmonthlydata();

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(yaxis[1],xaxis[1] ); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(yaxis[2],xaxis[2]); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(yaxis[3],xaxis[3]); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(yaxis[4],xaxis[4]); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(yaxis[5],xaxis[5]); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(yaxis[6],xaxis[6] ); // Jun
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(yaxis[7],xaxis[7] ); // Apr
        valueSet1.add(v1e7);
        BarEntry v1e8 = new BarEntry(yaxis[8],xaxis[8] ); // May
        valueSet1.add(v1e8);
        BarEntry v1e9 = new BarEntry(yaxis[9],xaxis[9] ); // Jun
        valueSet1.add(v1e9);
        BarEntry v1e10 = new BarEntry(yaxis[10],xaxis[10] ); // Apr
        valueSet1.add(v1e10);
        BarEntry v1e11 = new BarEntry(yaxis[11],xaxis[11] ); // May
        valueSet1.add(v1e11);
        BarEntry v1e12 = new BarEntry(yaxis[12],xaxis[12]); // Jun
        valueSet1.add(v1e12);
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();

        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUNE");
        xAxis.add("JULY");
        xAxis.add("AUG");
        xAxis.add("SEP");
        xAxis.add("OCT");
        xAxis.add("NOV");
        xAxis.add("DEC");
        return xAxis;
    }
}