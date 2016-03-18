package com.mycompany.signin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.charts.LineChart;
// import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

public class dailygraph extends AppCompatActivity {

    MyDBHandler dbHandler;
    int[] xaxis = new int[31];
    float[] yaxis = new float[31];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailygraph);
        dbHandler = new MyDBHandler(this, null, null, 2);

        BarChart chart = (BarChart) findViewById(R.id.dailychart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("Expenditure So far");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }


    // Y Axis Data
    private ArrayList<BarDataSet> getDataSet() {

        yaxis = dbHandler.Amount_get_daily_data();
        xaxis = dbHandler.Daily_get_day_data();

        ArrayList<BarDataSet> dataSets = null;
        ArrayList<BarEntry> valueSet1  = new ArrayList<>();
       for (int i=1 ; i<30; i++)
        {

            BarEntry v1e1 = new BarEntry(yaxis[i],xaxis[i] ); // Jan
            valueSet1.add(v1e1);
        }

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }


    // x axis Data
    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();

        for (int i =1; i<31;i++)
        {
            xAxis.add(""+i);
        }


        return xAxis;
    }
}
