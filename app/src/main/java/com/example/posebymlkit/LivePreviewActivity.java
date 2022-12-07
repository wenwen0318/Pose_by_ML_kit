package com.example.posebymlkit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;


import com.google.android.gms.common.annotation.KeepName;
import com.example.posebymlkit.preference.PreferenceUtils;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ToggleButton;
import com.google.mlkit.common.model.LocalModel;
import com.example.posebymlkit.CameraSource;
import com.example.posebymlkit.CameraSourcePreview;
import com.example.posebymlkit.GraphicOverlay;
import com.example.posebymlkit.R;
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions;
import com.example.posebymlkit.objectdetector.ObjectDetectorProcessor;

import com.example.posebymlkit.posedetector.PoseDetectorProcessor;

import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;
import java.io.IOException;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

/** Live preview demo for ML Kit APIs. */
@KeepName
public class LivePreviewActivity extends AppCompatActivity {

    private static final String POSE_DETECTION = "Pose Detection";
    private static final String OBJECT_DETECTION = "Object Detection";

    private String model = OBJECT_DETECTION;

    private static final String TAG = "LivePreviewActivity";

    private CameraSource cameraSource = null;
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;

    private TextToSpeech tts = null;
    private Handler handler = new Handler();
    PoseDetectorProcessor pdp;
    ObjectDetectorProcessor odp;

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    String cardView;
    int userLevel;
    int time;

    String label = "";
    int[][] wrongHint = {{0, 0}};
    int overallCompleteness;
    int[] jointCompleteness;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_vision_live_preview);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        preview = findViewById(R.id.preview_view);
        if (preview == null) {
            Log.d(TAG, "Preview is null");
        }
        graphicOverlay = findViewById(R.id.graphic_overlay);
        if (graphicOverlay == null) {
            Log.d(TAG, "graphicOverlay is null");
        }

        bundle = getIntent().getExtras();
        cardView = bundle.getString("cardView");
        userLevel = bundle.getInt("userLevel");
        time = bundle.getInt("time")*1000;

        System.out.println("pose:"+ cardView+ " userLevel:"+ userLevel+ " time:"+ time);

        createCameraSource(OBJECT_DETECTION);

        createTTSSource();

        handler.postDelayed(personDetection,100);

        //handler.postDelayed(runnable, 5000);
    }
    private void createCameraSource(String model) {
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = new CameraSource(this, graphicOverlay);
        }
        cameraSource.setFacing(CameraSource.CAMERA_FACING_BACK);

        try {
            switch (model) {
                case OBJECT_DETECTION:
                    LocalModel localModel =
                            new LocalModel.Builder()
                                    .setAssetFilePath("custom_models/object_labeler.tflite")
                                    .build();
                    CustomObjectDetectorOptions customObjectDetectorOptions =
                            PreferenceUtils.getCustomObjectDetectorOptionsForLivePreview(this, localModel);
                    cameraSource.setMachineLearningFrameProcessor(
                            odp = new ObjectDetectorProcessor(this, customObjectDetectorOptions));
                    handler.postDelayed(personDetection,100);
                    break;
                case POSE_DETECTION:
                    PoseDetectorOptionsBase poseDetectorOptions =
                            PreferenceUtils.getPoseDetectorOptionsForLivePreview(this);
                    Log.i(TAG, "Using Pose Detector with options " + poseDetectorOptions);
                    boolean shouldShowInFrameLikelihood =
                            PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(this);
                    boolean visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(this);
                    boolean rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(this);
                    boolean runClassification = PreferenceUtils.shouldPoseDetectionRunClassification(this);
                    cameraSource.setMachineLearningFrameProcessor(
                            pdp = new PoseDetectorProcessor(
                                    this,
                                    poseDetectorOptions,
                                    shouldShowInFrameLikelihood,
                                    visualizeZ,
                                    rescaleZ,
                                    runClassification,
                                    /* isStreamMode = */ true,
                                    cardView,
                                    userLevel));
                    handler.postDelayed(TTSWrongHint,5000);
                    handler.postDelayed(timeCountdown,time);
                    break;
            }
        } catch (RuntimeException e) {
            Toast.makeText(
                            getApplicationContext(),
                            "Can not create image processor: " + e.getMessage(),
                            Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void startCameraSource() {
        if (cameraSource != null) {
            try {
                if (preview == null) {
                    Log.d(TAG, "resume: Preview is null");
                }
                if (graphicOverlay == null) {
                    Log.d(TAG, "resume: graphOverlay is null");
                }
                preview.start(cameraSource, graphicOverlay);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source.", e);
                cameraSource.release();
                cameraSource = null;
            }
        }
    }

    private void createTTSSource() {
        if( tts == null ) {
            tts = new TextToSpeech(this, arg0 -> {
                // TTS 初始化成功
                if( arg0 == TextToSpeech.SUCCESS ) {
                    switch (getString(R.string.language)){
                        case "English":
                            tts.setLanguage(Locale.ENGLISH);
                            tts.speak("Hello",TextToSpeech.QUEUE_ADD,null,null);
                            break;
                        case "简体中文":
                            tts.setLanguage(Locale.CHINA);
                            break;
                        case "繁體中文":
                            tts.setLanguage(Locale.CHINESE);
                            tts.speak("你好",TextToSpeech.QUEUE_ADD,null,null);
                            break;
                        case "Deutsch":
                            tts.setLanguage(Locale.GERMAN);
                            tts.speak("Guten Tag",TextToSpeech.QUEUE_ADD,null,null);
                            break;
                        case "日本語":
                            tts.setLanguage(Locale.JAPANESE);
                            tts.speak("こにちは",TextToSpeech.QUEUE_ADD,null,null);
                            break;
                        default :
                            tts.setLanguage(Locale.ENGLISH);
                            tts.speak("Hi",TextToSpeech.QUEUE_ADD,null,null);
                            break;
                    }
                }
            });
        }
    }

    private void startTTS() {
        String wrongStr = "";
        if(wrongHint[0][0] == 0){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr += "右身太下去";
                    break;
                case 2 :
                    wrongStr += "右身不夠下去";
                    break;
            }
        }
        else if(wrongHint[0][0] == 1){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr += "左身太下去";
                    break;
                case 2 :
                    wrongStr += "左身不夠下去";
                    break;
            }
        }
        else if(wrongHint[0][0] == 2){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "右膝蹲太低";
                    break;
                case 2 :
                    wrongStr = "右膝不夠低";
                    break;
                case 3 :
                    wrongStr = "右膝伸直";
                    break;
            }
        }
        else if(wrongHint[0][0] == 3){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "左膝蹲太低";
                    break;
                case 2 :
                    wrongStr = "左膝不夠低";
                    break;
                case 3 :
                    wrongStr = "左膝伸直";
                    break;
            }
        }
        else if(wrongHint[0][0] == 4){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "";
                    break;
                case 2 :
                    wrongStr = "";
                    break;
                case 3 :
                    wrongStr = "右臂伸直";
                    break;
            }
        }
        else if(wrongHint[0][0] == 5){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "";
                    break;
                case 2 :
                    wrongStr = "";
                    break;
                case 3 :
                    wrongStr = "左臂伸直";
                    break;
            }
        }
        else if(wrongHint[0][0] == 6){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "右腋下太收";
                    break;
                case 2 :
                    wrongStr = "右腋下太開";
                    break;
                case 3 :
                    wrongStr = "右手不夠高";
                    break;
            }
        }
        else if(wrongHint[0][0] == 7){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "左腋下太收";
                    break;
                case 2 :
                    wrongStr = "左腋下太開";
                    break;
                case 3 :
                    wrongStr = "左手不夠高";
                    break;
            }
        }
        else if(wrongHint[0][0] == 8){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "";
                    break;
                case 2 :
                    wrongStr = "";
                    break;
                case 3 :
                    wrongStr = "右肩伸直";
                    break;
            }
        }
        else if(wrongHint[0][0] == 9){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "";
                    break;
                case 2 :
                    wrongStr = " ";
                    break;
                case 3 :
                    wrongStr = "左肩伸直";
                    break;
            }
        }
        else if(wrongHint[0][0] == 10){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "身體保持垂直";
                    break;
            }
        }
        else if(wrongHint[0][0] == 11){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "右蹲不夠下去";
                    break;
            }
        }
        else if(wrongHint[0][0] == 12){
            switch(wrongHint[0][1]){
                case 1 :
                    wrongStr = "左蹲不夠下去";
                    break;
            }
        }
        tts.speak(wrongStr,TextToSpeech.QUEUE_ADD,null,null);
    }

    private void getResultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LivePreviewActivity.this);
        builder.setCancelable(false);
        builder.setTitle("練習結束");
        builder.setMessage("總體正確率： "+overallCompleteness+"%");
        builder.setNegativeButton("回主頁面", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("詳細數據", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intent.setClass(LivePreviewActivity.this, PracticeResultActivity.class);
                bundle.putIntArray("jointResult",jointCompleteness);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        builder.create().show();
    }

    Runnable personDetection = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 100);
            label = odp.getLabel();
            if (label != null){
                System.out.println("Label:" + label);
                if (label.equals("Person")){
                    model = POSE_DETECTION;
                    createCameraSource(model);
                    handler.removeCallbacks(personDetection);
                }
            }
        }
    };

    Runnable TTSWrongHint = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 5000);
            wrongHint = pdp.wrong();
            startTTS();
            pdp.clear();
        }
    };

    Runnable timeCountdown = new Runnable() {
        @Override
        public void run() {
            tts.speak("練習結束",TextToSpeech.QUEUE_ADD,null,null);
            handler.postDelayed(this, time);
            overallCompleteness = pdp.getOverallCompleteness();
            jointCompleteness = pdp.getJointsCompleteness();
            System.out.print("jointCompleteness : ");
            for(int num : jointCompleteness){
                System.out.print(" "+num+"%");
            }
            System.out.println();
            if (cameraSource != null) {
                cameraSource.release();
            }
            handler.removeCallbacks(personDetection);
            handler.removeCallbacks(TTSWrongHint);
            handler.removeCallbacks(timeCountdown);
            getResultDialog();
        }
    };

    Runnable poseClassify = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 1000);
//            cameraSource.setMachineLearningFrameProcessor(
//                    pdp = new PoseDetectorProcessor(
//                            this,
//                            poseDetectorOptions,
//                            shouldShowInFrameLikelihood,
//                            visualizeZ,
//                            rescaleZ,
//                            runClassification,
//                            /* isStreamMode = */ true,
//                            cardView,
//                            userLevel));
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        createCameraSource(model);
        startCameraSource();
        createTTSSource();
    }

    /** Stops the camera. */
    @Override
    protected void onPause() {
        super.onPause();
        preview.stop();
        tts.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cameraSource != null) {
            cameraSource.release();
        }
        tts.shutdown();
        handler.removeCallbacks(personDetection);
        handler.removeCallbacks(TTSWrongHint);
        handler.removeCallbacks(timeCountdown);
    }
}