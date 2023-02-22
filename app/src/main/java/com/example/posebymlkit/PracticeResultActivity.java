package com.example.posebymlkit;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PracticeResultActivity extends AppCompatActivity {

    LineChartData lineChartData;
    LineChart lineChart;
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Entry> yData = new ArrayList<>();

    ListView listView;
    SimpleAdapter simpleAdapter;

    HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(this);

    Intent intent;
    Bundle bundle;
    String cardView;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_result);

        intent = new Intent();
        bundle = getIntent().getExtras();
        cardView = bundle.getString("cardView");
        date = bundle.getString("date");

        List<HistoricalRecord> historicalRecord = hr.getHistoricalRecordByPoseName(cardView,8);
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

        displayListView();
    }
    private void displayListView() {

        String[] joint = {"右臀", "左臀", "右膝", "左膝", "右手肘", "左手肘", "右腋下", "左腋下", "右肩", "左肩",
                "右膝不超過腳趾", "左膝不超過腳趾", "右大腿平行地面", "左大腿平行地面", "右胯下", "左胯下",
                "右手臂垂直地面", "左手臂垂直地面", "右手斜上舉", "左手斜上舉", "右腳腳跟著地", "左腳腳跟著地", "身體垂直"};
        listView = findViewById(R.id.listView);
        HistoricalRecord historicalRecord;
        Log.d("date",date + " ");
        if (date == null){
            historicalRecord = hr.getHistoricalRecordByPoseName(cardView,1).get(0);
        }
        else {
            historicalRecord = hr.getHistoricalRecordByDate(date);
        }

        final ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for (int i = 5; i <= 27; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            String complete = historicalRecord.get(i);
            Log.d("joint_complete",i + " " + complete);
            if (historicalRecord.get(i) != null){
                hashMap.put("joint", joint[i-5]);
                hashMap.put("complete", complete);
                arrayList.add(hashMap);
            }
        }
        Log.d("arr:",arrayList.toString());
        String[] from = {"joint", "complete"};
        int[] value = {R.id.textView, R.id.textView2};
        simpleAdapter =
                new SimpleAdapter(this, arrayList, R.layout.result_list_layout, from, value);
        listView.setAdapter(simpleAdapter);

    }
}