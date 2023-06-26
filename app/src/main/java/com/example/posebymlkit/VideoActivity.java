package com.example.posebymlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Locale;

public class VideoActivity extends AppCompatActivity {

    TextView poseIllustrate;
    VideoView videoView;
    ImageView videoStatusView;
    String cardView;
    int userLevel = 3;
    int time;
    String MODE = "pose";
    int camera_facing;

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
        camera_facing = bundle.getInt("camera_facing");

        poseIllustrate = findViewById(R.id.poseIllustrate);
        videoView = findViewById(R.id.videoView);
        videoStatusView = findViewById(R.id.videoStatusView);

        getIllustrate(cardView);
        poseIllustrate.setMovementMethod(new ScrollingMovementMethod());
        getVideo(cardView);

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(videoStatusView.getVisibility() == View.GONE){
                    videoStatusView.setVisibility(View.VISIBLE);
                }
                else {
                    videoStatusView.setVisibility(View.GONE);
                }
            }
        });

        videoStatusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    videoStatusView.setImageResource(android.R.drawable.ic_media_play);
                    videoView.pause();
                }
                else {
                    videoStatusView.setImageResource(android.R.drawable.ic_media_pause);
                    videoView.start();
                }
            }
        });

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

        String timeStr = timeSet.getText().toString() + getResources().getString(R.string.second);
        timeSet.setText(timeStr);

        dialog.show();
        btn_timeSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = Integer.parseInt(timeSet.getText().toString().substring(0,2));
                time -= 10;
                if (time < 10) time = 10;
                String timeStr = time + getResources().getString(R.string.second);
                timeSet.setText(timeStr);
            }
        });
        btn_timeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = Integer.parseInt(timeSet.getText().toString().substring(0,2));
                time += 10;
                if (time > 60) time = 60;
                String timeStr = time + getResources().getString(R.string.second);
                timeSet.setText(timeStr);
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
                time = Integer.parseInt(timeSet.getText().toString().substring(0,2));
                intent.setClass(VideoActivity.this, LivePreviewActivity.class);
                bundle.putString("cardView", cardView);
                bundle.putInt("userLevel", userLevel);
                bundle.putInt("time", time);
                bundle.putString("mode", MODE);
                bundle.putInt("camera_facing", camera_facing);
                intent.putExtras(bundle);
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        });
    }
}