package com.example.posebymlkit.objectdetector;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.example.posebymlkit.CameraSource;
import com.example.posebymlkit.CameraSourcePreview;
import com.example.posebymlkit.GraphicOverlay;
import com.example.posebymlkit.preference.PreferenceUtils;
import com.example.posebymlkit.LivePreviewActivity;
import com.example.posebymlkit.objectdetector.ObjectDetectorProcessor;
import com.example.posebymlkit.objectdetector.ObjectGraphic;
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;
import com.example.posebymlkit.posedetector.PoseDetectorProcessor;
import com.example.posebymlkit.posedetector.PoseGraphic;
import java.io.IOException;

public class ObjectDetectorToPoseDetector extends LivePreviewActivity
        implements OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    private static final String POSE_DETECTION = "Pose Detection";

    public CameraSource cameraSource = null;
    CameraSourcePreview preview;
    GraphicOverlay graphicOverlay;
    Context context;
    String cardView;
    int userLevel;
    private static final String TAG = "Transform";
<<<<<<< HEAD
=======
    String s;
>>>>>>> a1a9df0 (test)

    public ObjectDetectorToPoseDetector(Context context, CameraSource cameraSource, GraphicOverlay graphicOverlay, CameraSourcePreview preview, String cardView, int userLevel){

        this.context = context;
        this.cameraSource = cameraSource;
        this.graphicOverlay = graphicOverlay;
        this.preview = preview;
        this.cardView = cardView;
        this.userLevel = userLevel;

        createCameraSource();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(TAG, "Set facing");
        if (cameraSource != null) {
            if (isChecked) {
                cameraSource.setFacing(CameraSource.CAMERA_FACING_FRONT);
            } else {
                cameraSource.setFacing(CameraSource.CAMERA_FACING_BACK);
            }
        }
        preview.stop();
        startCameraSource();
    }

    private void createCameraSource(){
        if (cameraSource == null) {
            cameraSource = new CameraSource(this, graphicOverlay);
        }
        try{
            PoseDetectorOptionsBase poseDetectorOptions =
                    PreferenceUtils.getPoseDetectorOptionsForLivePreview(context);
            Log.i(TAG, "Using Pose Detector with options " + poseDetectorOptions);
            boolean shouldShowInFrameLikelihood =
                    PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(context);
            boolean visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(context);
            boolean rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(context);
            boolean runClassification = PreferenceUtils.shouldPoseDetectionRunClassification(context);
            cameraSource.setMachineLearningFrameProcessor(
                    new PoseDetectorProcessor(
                            context,
                            poseDetectorOptions,
                            shouldShowInFrameLikelihood,
                            visualizeZ,
                            rescaleZ,
                            runClassification,
                            /* isStreamMode = */ true,
                            cardView,
                            userLevel));
        } catch (RuntimeException e) {
            Log.e(TAG, "Can not create image processor: " , e);
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

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        createCameraSource();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
