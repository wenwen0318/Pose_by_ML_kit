package com.example.posebymlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.android.gms.common.annotation.KeepName;
import com.google.mlkit.common.model.LocalModel;
import com.example.posebymlkit.CameraSource;
import com.example.posebymlkit.CameraSourcePreview;
import com.example.posebymlkit.GraphicOverlay;
import com.example.posebymlkit.preference.PreferenceUtils;
import com.example.posebymlkit.R;
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions;
import com.example.posebymlkit.objectdetector.ObjectDetectorProcessor;

import com.example.posebymlkit.posedetector.PoseDetectorProcessor;

import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Live preview demo for ML Kit APIs. */
@KeepName
public class LivePreviewActivity extends AppCompatActivity {

    private static final String POSE_DETECTION = "Pose Detection";

    private static final String TAG = "LivePreviewActivity";

    private CameraSource cameraSource = null;
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private String selectedModel = POSE_DETECTION;

    Bundle bundle;
    String cardView;
    int userLevel;
    int time;

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
    }
    private void createCameraSource(String model) {
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = new CameraSource(this, graphicOverlay);
        }
        cameraSource.setFacing(CameraSource.CAMERA_FACING_FRONT);
        try {
            // object detection
            Log.i(TAG, "Using Custom Object Detector Processor");
            LocalModel localModel =
                    new LocalModel.Builder()
                            .setAssetFilePath("custom_models/object_labeler.tflite")
                            .build();
            CustomObjectDetectorOptions customObjectDetectorOptions =
                    PreferenceUtils.getCustomObjectDetectorOptionsForLivePreview(this, localModel);
            cameraSource.setMachineLearningFrameProcessor(
                    new ObjectDetectorProcessor(this, customObjectDetectorOptions,cameraSource, graphicOverlay, preview, cardView, userLevel));
            // pose detection
//            PoseDetectorOptionsBase poseDetectorOptions =
//                    PreferenceUtils.getPoseDetectorOptionsForLivePreview(this);
//            Log.i(TAG, "Using Pose Detector with options " + poseDetectorOptions);
//            boolean shouldShowInFrameLikelihood =
//                    PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(this);
//            boolean visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(this);
//            boolean rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(this);
//            boolean runClassification = PreferenceUtils.shouldPoseDetectionRunClassification(this);
////            String poseName = "";
//            cameraSource.setMachineLearningFrameProcessor(
//                    new PoseDetectorProcessor(
//                            this,
//                            poseDetectorOptions,
//                            shouldShowInFrameLikelihood,
//                            visualizeZ,
//                            rescaleZ,
//                            runClassification,
//                            /* isStreamMode = */ true,
//                            cardView,
//                            userLevel));
        } catch (RuntimeException e) {
            Log.e(TAG, "Can not create image processor: " + model, e);
            Toast.makeText(
                            getApplicationContext(),
                            "Can not create image processor: " + e.getMessage(),
                            Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * Starts or restarts the camera source, if it exists. If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
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

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        createCameraSource(selectedModel);
        startCameraSource();
    }

    /** Stops the camera. */
    @Override
    protected void onPause() {
        super.onPause();
        preview.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cameraSource != null) {
            cameraSource.release();
        }
    }
}