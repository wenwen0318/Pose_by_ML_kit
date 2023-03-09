package com.example.posebymlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    TextView poseIllustrate;
    VideoView videoView;
    MediaController mediaController;
    String cardView;
    int userLevel = 3,time;

    Dialog dialog;
    View viewDialog;
    TextView timeSet;
    RadioButton btn_easy,btn_hard;
    Button btn_timeSub,btn_timeAdd,btn_cancel,btn_check;

    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Button btn_backToHome = findViewById(R.id.btn_backToHome);
        Button btn_startPractice = findViewById(R.id.btn_startPractice);

        intent = new Intent();
        bundle = getIntent().getExtras();
        cardView = bundle.getString("cardView");

        poseIllustrate = findViewById(R.id.poseIllustrate);
        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);

        getIllustrate(cardView);
        poseIllustrate.setMovementMethod(new ScrollingMovementMethod());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        getVideo(cardView);

        //切換至練習頁面
        btn_startPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.pause();
                getDialog();
            }
        });
        btn_backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //回到主頁面
                finish();
            }
        });

    }

    private void getVideo(String poseName){
        String videoPath = "android.resource://" + getPackageName() + "/";
        int videoID = getApplicationContext().getResources().getIdentifier(
                poseName.toLowerCase(),
                "raw",
                getPackageName());
        videoPath += videoID;
        System.out.println("videoPath:" + videoPath);
        videoView.setVideoURI(Uri.parse(videoPath));
    }

    private void getIllustrate(String poseName){
        int stringID = getApplicationContext().getResources().getIdentifier(
                poseName.toLowerCase() + "_ill",
                "string",
                getPackageName());
        System.out.println("String ID:" + stringID);
        if (stringID != 0){
            poseIllustrate.setText(stringID);
        }
    }

    private void getDialog() {
        dialog = new Dialog(VideoActivity.this);

        viewDialog = getLayoutInflater().inflate(R.layout.practice_dialog_layout , null);
        dialog.setContentView(viewDialog);

        btn_timeSub = viewDialog.findViewById(R.id.btn_timeSub);
        timeSet = viewDialog.findViewById(R.id.timeSet);
        btn_timeAdd = viewDialog.findViewById(R.id.btn_timeAdd);
        btn_easy = viewDialog.findViewById(R.id.btn_easy);
        btn_hard = viewDialog.findViewById(R.id.btn_hard);
        btn_cancel = viewDialog.findViewById(R.id.cancel);
        btn_check  = viewDialog.findViewById(R.id.check);

        dialog.show();
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
        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLevel = 3;
            }
        });
        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLevel = 2;
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = Integer.parseInt(timeSet.getText().toString());
                intent.setClass(VideoActivity.this, LivePreviewActivity.class);
                bundle.putString("cardView", cardView);
                bundle.putInt("userLevel", userLevel);
                bundle.putInt("time", time);
                intent.putExtras(bundle);
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        });
    }
}