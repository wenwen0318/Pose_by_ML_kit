package com.example.posebymlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.example.posebymlkit.database.MenuHistory;
import com.example.posebymlkit.database.MenuHistoryDBHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HistoryListActivity extends AppCompatActivity {

    ListView listView;
    SimpleAdapter simpleAdapter;

    HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(this);
    MenuHistoryDBHandler mh = new MenuHistoryDBHandler(this);

    Intent intent = new Intent();
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        Button button2 = findViewById(R.id.toMenuBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenuLayout();
            }
        });

        displayUniListView();
    }

    public void toUniLayout(){
        setContentView(R.layout.activity_history_list);

        Button button2 = findViewById(R.id.toMenuBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenuLayout();
            }
        });

        displayUniListView();
    }

    public void toMenuLayout(){
        setContentView(R.layout.activity_menu_list);

        Button button1 = findViewById(R.id.toUniBtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toUniLayout();
            }
        });
        displayMenuListView();
    }

    private void displayUniListView() {

        listView = findViewById(R.id.listView);

        List<HistoricalRecord> historicalRecord = hr.getHistoricalRecordByMode("pose", 29);
        Collections.reverse(historicalRecord);

        final ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for (HistoricalRecord record:historicalRecord) {
            HashMap<String, String> hashMap = new HashMap<>();
            int resId = getApplicationContext().getResources().getIdentifier(
                    record.getPoseName().toLowerCase(),
                    "drawable",
                    getPackageName());
            hashMap.put("poseImage", "android.resource://" + getPackageName() + "/" + resId);
            hashMap.put("poseName", record.getPoseName());
            hashMap.put("date", record.getDate());
            hashMap.put("level", "");
            hashMap.put("complete", record.getAllComplete() + "%");
            arrayList.add(hashMap);

        }
        String[] from = {"poseImage", "poseName", "date", "level", "complete"};
        int[] value = {R.id.poseImage, R.id.poseName, R.id.date, R.id.poseLevel, R.id.allComplete};
        simpleAdapter =
                new SimpleAdapter(this, arrayList, R.layout.history_list_layout, from, value);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(onUniItemClickListener);
    }

    private void displayMenuListView() {

        listView = findViewById(R.id.listView);

        List<MenuHistory> menuHistory = mh.getAllMenuHistory();
        Collections.reverse(menuHistory);

        final ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for (MenuHistory history : menuHistory) {
            HashMap<String, String> hashMap = new HashMap<>();
            int menuTime = 0;
            hashMap.put("menuName", history.getMenuName());
            hashMap.put("menuDate", history.getMenuDate());
            for(int i=3;i<22;i++){
                if(history.get(i) == null){
                    break;
                }
                HistoricalRecord historicalRecord = hr.getHistoricalRecordByDate(history.get(i));
                menuTime += historicalRecord.getTime();
            }
            hashMap.put("menuTime", Integer.toString(menuTime)+"s");
            arrayList.add(hashMap);

        }
        String[] from = {"menuName", "menuDate", "menuTime"};
        int[] value = {R.id.menuName, R.id.menuDate, R.id.menuTime};
        simpleAdapter =
                new SimpleAdapter(this, arrayList, R.layout.menu_history_layout, from, value);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(onMenuItemClickListener);
    }

    private final AdapterView.OnItemClickListener onUniItemClickListener = new AdapterView.OnItemClickListener(){
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

    private final AdapterView.OnItemClickListener onMenuItemClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView menuName = view.findViewById(R.id.menuName);
            TextView menuDate = view.findViewById(R.id.menuDate);
            intent.setClass(HistoryListActivity.this, MenuResultActivity.class);
            bundle.putString("menuName",menuName.getText().toString());
            bundle.putString("menuDate",menuDate.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);

        }
    };
}