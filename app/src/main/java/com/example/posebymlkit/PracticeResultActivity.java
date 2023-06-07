package com.example.posebymlkit;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.example.posebymlkit.database.PoseWrongTTSDBHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PracticeResultActivity extends AppCompatActivity {

    LineChartData lineChartData;
    LineChart lineChart;
    ArrayList<String> xData = new ArrayList<>();
    ArrayList<Entry> yData = new ArrayList<>();

    RecyclerView jointRecycleView;
    MyListAdapter jointListAdapter;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    List<HistoricalRecord> historicalRecords;
    HistoricalRecord historicalRecord;
    HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(this);
    PoseWrongTTSDBHandler pwt = new PoseWrongTTSDBHandler(this);

    String suggest = "";
    TextView suggestTextView;

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
        System.out.println("cardView : "+cardView);
        System.out.println("date : "+date);

        historicalRecords = hr.getHistoricalRecordByPoseName(cardView,8);
        Collections.reverse(historicalRecords);

        lineChart = findViewById(R.id.lineChart);
        lineChartData = new LineChartData(lineChart,this);

        int i = 0;
        for (HistoricalRecord h : historicalRecords) {
            xData.add((i+1)+"");
            yData.add(new Entry(i,h.getAllComplete()));
            i++;
        }
        lineChartData.initX(xData);
        lineChartData.initY(0F,100F);
        lineChartData.initDataSet(yData);

        suggestTextView = findViewById(R.id.suggestTextView);

        getPracticeInfo();
        setTitle();

        jointRecycleView = findViewById(R.id.jointCompleteRecyclerView);
        jointRecycleView.setLayoutManager(new LinearLayoutManager(this));
        jointRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        jointListAdapter = new MyListAdapter();
        jointRecycleView.setAdapter(jointListAdapter);
    }
    private void setTitle() {
        View poseTitleView = findViewById(R.id.poseTitleView);
        ImageView poseImage = poseTitleView.findViewById(R.id.poseImage);
        TextView poseName = poseTitleView.findViewById(R.id.poseName);
        TextView poseDate = poseTitleView.findViewById(R.id.date);
        TextView allComplete = poseTitleView.findViewById(R.id.allComplete);
        int imageId = getApplicationContext().getResources().getIdentifier(
                cardView.toLowerCase(),
                "drawable",
                getPackageName());
        poseImage.setImageResource(imageId);
        poseName.setText(cardView);
        poseDate.setText(date);
        allComplete.setText(historicalRecord.getAllComplete()+ "%");
    }

    private void getPracticeInfo() {
        String[] poseStandards = getResources().getStringArray(R.array.poseStandards);
        Log.d("date",date + " ");
        if (date == null){
            historicalRecord = hr.getHistoricalRecordByPoseName(cardView,1).get(0);
        }
        else {
            historicalRecord = hr.getHistoricalRecordByDate(date);
        }
        for (int i = 6; i <= 29; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            String complete = historicalRecord.get(i);
            Log.d("joint_complete",i + " " + complete);
            if (historicalRecord.get(i) != null){
                hashMap.put("poseStandardName", poseStandards[i-6]);
                hashMap.put("jointComplete", complete);
                if (Float.parseFloat(complete) < 60){
                    if (i-6 <= 21){
                        suggest += pwt.getPoseWrongTTS(cardView).getWrongTTS().get((i-6)*2) + "\n";
                    }
                    else if (i-6 == 22){
                        suggest += pwt.getPoseWrongTTS(cardView).getWrongTTS().get(44) + "\n";
                    }
                    else {
                        suggest += pwt.getPoseWrongTTS(cardView).getWrongTTS().get(45) + "\n";
                    }
                }
                arrayList.add(hashMap);
            }
        }
        suggestTextView.setText(suggest);
    }

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder{

            private final TextView poseStandardName,jointComplete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                poseStandardName = itemView.findViewById(R.id.poseStandardName);
                jointComplete = itemView.findViewById(R.id.jointComplete);
            }
        }

        @NonNull
        @Override
        public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.result_list_layout,parent,false);
            return new MyListAdapter.ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            float jointComplete = Float.parseFloat(Objects.requireNonNull(arrayList.get(position).get("jointComplete")));
            if (jointComplete < 30){
                holder.poseStandardName.setTextColor(Color.RED);
                holder.jointComplete.setTextColor(Color.RED);
            } else if (jointComplete < 60){
                holder.poseStandardName.setTextColor(Color.rgb(255,152,0));
                holder.jointComplete.setTextColor(Color.rgb(255,152,0));
            }
            holder.poseStandardName.setText(arrayList.get(position).get("poseStandardName"));
            holder.jointComplete.setText(arrayList.get(position).get("jointComplete") + "%");
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

    }
}