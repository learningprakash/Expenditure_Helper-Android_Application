package com.mycompany.signin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class yearlygraph extends AppCompatActivity {


    int[] xaxis = new int[8];
    float[] yaxis = new float[8];
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yearlygraph);
        dbHandler = new MyDBHandler(this, null, null, 2);

        BarChart chart = (BarChart) findViewById(R.id.yearlychart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("Expenditure So far");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    private ArrayList<BarDataSet> getDataSet() {
        yaxis = dbHandler.Amountgetyearlydata();
        xaxis = dbHandler.Yeargetyearlydata();

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(yaxis[0],xaxis[0] ); // 2011
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(yaxis[1],xaxis[1]); // 2012
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(yaxis[2],xaxis[2]); // 2013
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(yaxis[3],xaxis[3]); // 2014
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(yaxis[4],xaxis[4]); // 2015
        valueSet1.add(v1e5);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();

        xAxis.add(""+2011);
        xAxis.add(""+2012);
        xAxis.add(""+2013);
        xAxis.add(""+2014);
        xAxis.add(""+2015);
        return xAxis;
    }
}
