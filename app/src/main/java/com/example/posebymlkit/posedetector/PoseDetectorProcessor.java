package com.example.posebymlkit.posedetector;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import com.example.posebymlkit.GraphicOverlay;
import com.example.posebymlkit.VisionProcessorBase;
//import com.google.mlkit.vision.demo.java.posedetector.classification.PoseClassifierProcessor;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/** A processor to run pose detector. */
public class PoseDetectorProcessor
        extends VisionProcessorBase<PoseDetectorProcessor.GetPose> {
    private static final String TAG = "PoseDetectorProcessor";

    private final PoseDetector detector;

    private final boolean showInFrameLikelihood;
    private final boolean visualizeZ;
    private final boolean rescaleZForVisualization;
    private final boolean runClassification;
    private final boolean isStreamMode;
    private final String cardView;
    private final int userLevel;
    private final Context context;
    private final Executor classificationExecutor;

    int[] wrongHint;
    int[][] wrongFre = {{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},
            {0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},
            {0, 0, 0, 0},{0, 0, 0, 0},{0, 0},{0, 0, 0, 0},
            {0, 0, 0, 0}};

    /** Internal class to hold Pose and classification results. */

    protected static class GetPose {
        private final Pose pose;

        public GetPose(Pose pose) {
            this.pose = pose;
        }

        public Pose getPose() {
            return pose;
        }
    }

    public PoseDetectorProcessor(
            Context context,
            PoseDetectorOptionsBase options,
            boolean showInFrameLikelihood,
            boolean visualizeZ,
            boolean rescaleZForVisualization,
            boolean runClassification,
            boolean isStreamMode,
            String cardView,
            int userLevel) {
        super(context);
        this.showInFrameLikelihood = showInFrameLikelihood;
        this.visualizeZ = visualizeZ;
        this.rescaleZForVisualization = rescaleZForVisualization;
        detector = PoseDetection.getClient(options);
        this.runClassification = runClassification;
        this.isStreamMode = isStreamMode;
        this.cardView = cardView;
        this.userLevel = userLevel;
        this.context = context;
        classificationExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void stop() {
        super.stop();
        detector.close();
    }

    protected Task<GetPose> detectInImage(InputImage image) {
        return detector
                .process(image)
                .continueWith(
                        task -> {
                            Pose pose = task.getResult();
                            return new GetPose(pose);
                        });
    }

    protected Task<GetPose> detectInImage(MlImage image) {
        return detector
                .process(image)
                .continueWith(
                        task -> {
                            Pose pose = task.getResult();
                            return new GetPose(pose);
                        });
    }

    @Override
    protected void onSuccess(
            @NonNull GetPose getPose,
            @NonNull GraphicOverlay graphicOverlay) {
        graphicOverlay.add(
                new PoseGraphic(
                        graphicOverlay,
                        getPose.getPose(),
                        showInFrameLikelihood,
                        visualizeZ,
                        rescaleZForVisualization));
//                        poseWithClassification.classificationResult
        PoseCalculate Calculate = new PoseCalculate(
                getPose.getPose(),
                cardView,
                userLevel);
        wrongHint = Calculate.wrong();
        wrongFrequency(wrongHint);
//        System.out.print("Status in PoseDetectorProcessor:");
//        for (int[] parts:wrongHint){
//            for(int status : parts) {
//                System.out.print("wrongHint:"+parts+" "+status);
//            }
//        }
//        System.out.println();
    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Pose detection failed!", e);
    }

    @Override
    protected boolean isMlImageEnabled(Context context) {
        // Use MlImage in Pose Detection by default, change it to OFF to switch to InputImage.
        return true;
    }

    public int[][] wrong(){
        int[][] finalWrong = {{0, 0}};
        int max = 0;
//        int sec = 0;
//        int thi = 0;
        for(int i=0;i<wrongFre.length;i++){
            for(int j=0;j<wrongFre[i].length;j++){
                if(wrongFre[i][j] > max){
                    max = wrongFre[i][j];
                    finalWrong[0][0] = i;
                    finalWrong[0][1] = j;
                }

            }
        }
        return finalWrong;
    }

    public void wrongFrequency(int[] arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i] != 0){
                switch (arr[i]){
                    case 1: wrongFre[i][1] += 1;break;
                    case 2: wrongFre[i][2] += 1;break;
                    case 3: wrongFre[i][3] += 1;break;
                }
            }
        }
    }

    public void clear(){
        for(int i=0;i<wrongFre.length;i++){
            for(int j=0;j<wrongFre[i].length;j++){
                wrongFre[i][j] = 0;
            }
        }
    }
}