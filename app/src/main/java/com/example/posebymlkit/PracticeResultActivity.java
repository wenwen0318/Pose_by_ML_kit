package com.example.posebymlkit;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class PracticeResultActivity extends AppCompatActivity {

    LineChartData lineChartData;
    LineChart lineChart;
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Entry> yData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_result);

        lineChart = findViewById(R.id.lineChart);
        lineChartData = new LineChartData(lineChart,this);

        for(int i = 0;i<8;i++){
            xData.add(i+"");
            yData.add(new Entry(i, (int)(Math.random()*100)));
        }
        lineChartData.initX(xData);
        lineChartData.initY(0F,100F);
        lineChartData.initDataSet(yData);
    }
}