package com.example.posebymlkit;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PracticeResultActivity extends AppCompatActivity {

    LineChartData lineChartData;
    LineChart lineChart;
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Entry> yData = new ArrayList<>();

    Intent intent;
    Bundle bundle;
    String cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_result);

        intent = new Intent();
        bundle = getIntent().getExtras();
        cardView = bundle.getString("cardView");

        HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(this);
        List<HistoricalRecord> historicalRecord = hr.getHistoricalRecordByPoseName(cardView);
        Collections.reverse(historicalRecord);

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