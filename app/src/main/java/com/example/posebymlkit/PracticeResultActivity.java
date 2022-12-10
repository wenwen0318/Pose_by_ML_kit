package com.example.posebymlkit;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

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

        HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(this);
        List<HistoricalRecord> historicalRecord = hr.getHistoricalRecordByPoseName("Warrior2");

        lineChart = findViewById(R.id.lineChart);
        lineChartData = new LineChartData(lineChart,this);

        int i = 0;
        for (HistoricalRecord h : historicalRecord) {
            xData.add(i+"");
            yData.add(new Entry(i,h.getAllComplete()));
            i++;
        }
        lineChartData.initX(xData);
        lineChartData.initY(0F,100F);
        lineChartData.initDataSet(yData);
    }
}