package com.example.posebymlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    TextView poseIllustrate;
    VideoView videoView;
    MediaController mediaController;
    //String videoPath, illustrate;

    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Button btn_backToHome = findViewById(R.id.btn_backToHome);
        Button btn_startPractise = findViewById(R.id.btn_startPractise);

        intent = new Intent();
        bundle = getIntent().getExtras();
        mediaController = new MediaController(this);

        poseIllustrate = findViewById(R.id.poseIllustrate);
        videoView = findViewById(R.id.videoView);

        //切換至練習頁面
        btn_startPractise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setClass(VideoActivity.this, LivePreviewActivity.class);
                startActivity(intent);
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
}