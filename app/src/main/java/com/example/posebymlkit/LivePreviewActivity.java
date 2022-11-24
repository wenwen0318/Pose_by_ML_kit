package com.example.posebymlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.annotation.KeepName;
import com.example.posebymlkit.preference.PreferenceUtils;

import com.example.posebymlkit.posedetector.PoseDetectorProcessor;

import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;
import java.io.IOException;
import java.util.Locale;

/** Live preview demo for ML Kit APIs. */
@KeepName
public final class LivePreviewActivity extends AppCompatActivity {

    private static final String POSE_DETECTION = "Pose Detection";

    private static final String TAG = "LivePreviewActivity";

    private CameraSource cameraSource = null;
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private String selectedModel = POSE_DETECTION;

    private TextToSpeech tts = null;
    private Handler handler = new Handler();
    PoseDetectorProcessor PoseDetector;

    Bundle bundle;
    String cardView;
    int userLevel;
    int time;
    int[] wrongHint = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

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
        time = bundle.getInt("time");

        System.out.println("pose:"+ cardView+ " userLevel:"+ userLevel+ " time:"+ time);

        createCameraSource(selectedModel);

        createTTSSource();

        handler.postDelayed(runnable, 5000);
    }

    private void createCameraSource(String model) {
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = new CameraSource(this, graphicOverlay);
        }
        cameraSource.setFacing(CameraSource.CAMERA_FACING_BACK);
        try {
            PoseDetectorOptionsBase poseDetectorOptions =
                    PreferenceUtils.getPoseDetectorOptionsForLivePreview(this);
            Log.i(TAG, "Using Pose Detector with options " + poseDetectorOptions);
            boolean shouldShowInFrameLikelihood =
                    PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(this);
            boolean visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(this);
            boolean rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(this);
            boolean runClassification = PreferenceUtils.shouldPoseDetectionRunClassification(this);
//            String poseName = "";
            cameraSource.setMachineLearningFrameProcessor(
                    PoseDetector = new PoseDetectorProcessor(
                            this,
                            poseDetectorOptions,
                            shouldShowInFrameLikelihood,
                            visualizeZ,
                            rescaleZ,
                            runClassification,
                            /* isStreamMode = */ true,
                            cardView,
                            userLevel));
        } catch (RuntimeException e) {
            Log.e(TAG, "Can not create image processor: " + model, e);
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
                    tts.setLanguage(Locale.CHINESE);
                    System.out.println("TTS success");
                }
            });
        }
    }

    private void startTTS() {
        String wrongStr = "";
        switch(wrongHint[0]){
            case 1 :
                wrongStr += "右身太下去";
                break;
            case 2 :
                wrongStr += "右身不夠下去";
                break;
        }
        switch(wrongHint[1]){
            case 1 :
                wrongStr += "左身太下去";
                break;
            case 2 :
                wrongStr += "左身不夠下去";
                break;
        }
        tts.speak(wrongStr,TextToSpeech.QUEUE_ADD,null,null);
        switch(wrongHint[2]){
            case 1 :
                tts.speak("右膝蹲太低", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 2 :
                tts.speak("右膝不夠低", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 3 :
                tts.speak("右膝伸直", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
        switch(wrongHint[3]){
            case 1 :
                tts.speak("左膝蹲太低", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 2 :
                tts.speak("左膝不夠低", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 3 :
                tts.speak("左膝伸直", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
        switch(wrongHint[4]){
            case 1 :
                tts.speak("", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 2 :
                tts.speak(" ", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 3 :
                tts.speak("右臂伸直", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
        switch(wrongHint[5]){
            case 1 :
                tts.speak("", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 2 :
                tts.speak(" ", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 3 :
                tts.speak("左臂伸直", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
        switch(wrongHint[6]){
            case 1 :
                tts.speak("右腋下太收", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 2 :
                tts.speak("右腋下太開", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 3 :
                tts.speak("右手不夠高", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
        switch(wrongHint[7]){
            case 1 :
                tts.speak("左腋下太收", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 2 :
                tts.speak("左腋下太開", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 3 :
                tts.speak("左手不夠高", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
        switch(wrongHint[8]){
            case 1 :
                tts.speak("", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 2 :
                tts.speak(" ", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 3 :
                tts.speak("右肩伸直", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
        switch(wrongHint[9]){
            case 1 :
                tts.speak("", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 2 :
                tts.speak(" ", TextToSpeech.QUEUE_ADD, null,null);
                break;
            case 3 :
                tts.speak("左肩伸直", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
        switch(wrongHint[10]){
            case 1 :
                tts.speak("身體保持垂直", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
        switch(wrongHint[11]){
            case 1 :
                tts.speak("右蹲不夠下去", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
        switch(wrongHint[12]){
            case 1 :
                tts.speak("左蹲不夠下去", TextToSpeech.QUEUE_ADD, null,null);
                break;
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 5000);
            wrongHint = PoseDetector.wrong();
            System.out.print("status in Activity:" );
            if (wrongHint == null) System.out.println("null");
            else {
                for (int status:wrongHint){
                    System.out.print(status);
                }
                startTTS();
            }
            System.out.println();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        createCameraSource(selectedModel);
        startCameraSource();
        createTTSSource();
    }

    /** Stops the camera. */
    @Override
    protected void onPause() {
        super.onPause();
        preview.stop();
        handler.removeCallbacks(runnable);
        tts.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cameraSource != null) {
            cameraSource.release();
        }
        handler.removeCallbacks(runnable);
        tts.shutdown();
    }
}