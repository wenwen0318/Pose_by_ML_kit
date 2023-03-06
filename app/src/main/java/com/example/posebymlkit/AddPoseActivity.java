package com.example.posebymlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.posebymlkit.database.TrainMenu;
import com.example.posebymlkit.database.TrainMenuDBHandler;

public class AddPoseActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;

    String menuName;
    int menuLength;

    Spinner poseSpinner;
    TextView timeSet;
    Button btn_timeSub,btn_timeAdd,btn_addPose_cancel,btn_addPose_check;
    final String[] pose = new String[1];

    TrainMenu trainMenu;
    TrainMenuDBHandler tm = new TrainMenuDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pose);

        intent = new Intent();
        bundle = getIntent().getExtras();
        menuName = bundle.getString("menuName");
        menuLength = bundle.getInt("menuLength");

        trainMenu = tm.getMenu(menuName);

        poseSpinner = findViewById(R.id.poseSpinner);
        btn_timeAdd = findViewById(R.id.btn_timeAdd);
        timeSet = findViewById(R.id.timeSet);
        btn_timeSub = findViewById(R.id.btn_timeSub);
        btn_addPose_cancel = findViewById(R.id.cancel);
        btn_addPose_check  = findViewById(R.id.check);

        poseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                pose[0] = parent.getSelectedItem().toString();
            }
        });

        btn_timeSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = Integer.parseInt(timeSet.getText().toString());
                time-=10;
                if (time<10) time = 10;
                timeSet.setText(String.valueOf(time));
            }
        });
        btn_timeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = Integer.parseInt(timeSet.getText().toString());
                time+=10;
                if (time>60) time = 60;
                timeSet.setText(String.valueOf(time));
            }
        });

        btn_addPose_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(AddPoseActivity.this, MenuActivity.class);
                bundle.putString("menuName", menuName);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        btn_addPose_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add pose to recycleView and DB
                trainMenu.setPose(menuLength,pose[0]);
                trainMenu.setTime(menuLength,Integer.parseInt(timeSet.getText().toString()));
                tm.updateTrainMenu(trainMenu);
                intent.setClass(AddPoseActivity.this, MenuActivity.class);
                bundle.putString("menuName", menuName);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });
    }
}