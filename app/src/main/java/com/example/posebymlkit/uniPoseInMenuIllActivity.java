package com.example.posebymlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.VideoView;

public class uniPoseInMenuIllActivity extends AppCompatActivity {

    TextView poseIllustrate;
    VideoView videoView;
    MediaController mediaController;
    String poseName;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_pose_ill);

        intent = new Intent();
        bundle = getIntent().getExtras();
        poseName = bundle.getString("poseName");

        poseIllustrate = findViewById(R.id.poseIllustrate);
        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);

        getIllustrate(poseName);
        poseIllustrate.setMovementMethod(new ScrollingMovementMethod());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        getVideo(poseName);

    }

    private void getVideo(String poseName) {
        String videoPath = "android.resource://" + getPackageName() + "/";
        int videoID = getApplicationContext().getResources().getIdentifier(
                poseName.toLowerCase(),
                "raw",
                getPackageName());
        videoPath += videoID;
        System.out.println("videoPath:" + videoPath);
        videoView.setVideoURI(Uri.parse(videoPath));
    }

    private void getIllustrate(String poseName) {
        int stringID = getApplicationContext().getResources().getIdentifier(
                poseName.toLowerCase() + "_ill",
                "string",
                getPackageName());
        System.out.println("String ID:" + stringID);
        if (stringID != 0) {
            poseIllustrate.setText(stringID);
        }
    }
}