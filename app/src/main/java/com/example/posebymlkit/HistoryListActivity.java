package com.example.posebymlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HistoryListActivity extends AppCompatActivity {

    ListView listView;
    SimpleAdapter simpleAdapter;

    HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(this);

    Intent intent = new Intent();
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        displayListView();
    }

    private void displayListView() {

        listView = findViewById(R.id.listView);

        List<HistoricalRecord> historicalRecord = hr.getAllHistoricalRecord();
        Collections.reverse(historicalRecord);

        final ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for (HistoricalRecord record:historicalRecord) {
            HashMap<String, String> hashMap = new HashMap<>();
            Log.d("record:", record.getPoseName() + " " + record.getDate() + " " + record.getLevel() + " " + record.getAllComplete());
            hashMap.put("poseName", record.getPoseName());
            hashMap.put("date", record.getDate());
            hashMap.put("level", "");
            hashMap.put("complete", Float.toString(record.getAllComplete()) + "%");
            arrayList.add(hashMap);

        }
        String[] from = {"poseName", "date", "level", "complete"};
        int[] value = {R.id.poseName, R.id.date, R.id.poseLevel, R.id.allComplete};
        simpleAdapter =
                new SimpleAdapter(this, arrayList, R.layout.history_list_layout, from, value);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(onItemClickListener);
    }

    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView poseName = view.findViewById(R.id.poseName);
            TextView date = view.findViewById(R.id.date);
            intent.setClass(HistoryListActivity.this, PracticeResultActivity.class);
            bundle.putString("cardView",poseName.getText().toString());
            bundle.putString("date",date.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}