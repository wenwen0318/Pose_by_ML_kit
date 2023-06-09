package com.example.posebymlkit.posedetector;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;

import com.example.posebymlkit.database.PoseStandardDBHandler;
import com.example.posebymlkit.posedetector.classification.PoseClassifierProcessor;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import com.example.posebymlkit.GraphicOverlay;
import com.example.posebymlkit.VisionProcessorBase;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseDetection;
import com.google.mlkit.vision.pose.PoseDetector;
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/** A processor to run pose detector. */
public class PoseDetectorProcessor
        extends VisionProcessorBase<PoseDetectorProcessor.GetPose> {
    private static final String TAG = "PoseDetectorProcessor";

    private final PoseDetector detector;

    private final boolean isCalc;
    private final boolean showInFrameLikelihood;
    private final boolean visualizeZ;
    private final boolean rescaleZForVisualization;
    private final boolean runClassification;
    private final boolean isStreamMode;
    private final Context context;
    private final String cardView;
    private final int userLevel;
    //private final Executor classificationExecutor;

    private PoseClassifierProcessor poseClassifierProcessor;

    private final int angleNum = 24;

    //一幀中所有關節點正確(0)錯誤(1)
    int[] angleStatus;

    //紀錄總幀數及總關節點錯誤數量
    int frameNum = 0;
    int[] wrongSum = new int[angleNum];

    //紀錄5秒幀數及關節點錯誤數量
    int frameNumTemp = 0;
    int[] wrongTemp = new int[angleNum];

    double[] gravity;
    Boolean isGetSkeleton;
    float deviation = 0;
    static ArrayList<String> poseStandard;

    double[] angleArray;

    /** Internal class to hold Pose and classification results. */

    protected static class GetPose {
        private final Pose pose;
        private final String classificationResult;

        public GetPose(Pose pose, String classificationResult) {
            this.pose = pose;
            this.classificationResult = classificationResult;
        }

        public Pose getPose() {
            return pose;
        }

        public String getClassificationResult(){
            return classificationResult;
        }
    }

    public PoseDetectorProcessor(
            Context context,
            PoseDetectorOptionsBase options,
            boolean isCalc,
            boolean showInFrameLikelihood,
            boolean visualizeZ,
            boolean rescaleZForVisualization,
            boolean runClassification,
            boolean isStreamMode,
            double[] gravity,
            String cardView,
            int userLevel) {
        super(context);
        this.isCalc = isCalc;
        this.showInFrameLikelihood = showInFrameLikelihood;
        this.visualizeZ = visualizeZ;
        this.rescaleZForVisualization = rescaleZForVisualization;
        detector = PoseDetection.getClient(options);
        this.runClassification = runClassification;
        this.isStreamMode = isStreamMode;
        this.gravity = gravity;
        this.context = context;
        this.cardView = cardView;
        this.userLevel = userLevel;
//        classificationExecutor = Executors.newSingleThreadExecutor();
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
                            String classificationResult = "";
                            if (runClassification) {
                                if (poseClassifierProcessor == null) {
                                    poseClassifierProcessor = new PoseClassifierProcessor(context, isStreamMode);
                                }
                                classificationResult = poseClassifierProcessor.getPoseResult(pose);
                            }
                            return new GetPose(pose, classificationResult);
                        });
    }

    protected Task<GetPose> detectInImage(MlImage image) {
        return detector
                .process(image)
                .continueWith(
//                        classificationExecutor,
                        task -> {
                            Pose pose = task.getResult();
                            String classificationResult = "";
                            if (runClassification) {
                                System.out.println("hasInRunClassification");
                                if (poseClassifierProcessor == null) {
                                    poseClassifierProcessor = new PoseClassifierProcessor(context, isStreamMode);
                                }
                                classificationResult = poseClassifierProcessor.getPoseResult(pose);
                            }
                            return new GetPose(pose, classificationResult);
                        });
    }

    @Override
    protected void onSuccess(
            @NonNull GetPose getPose,
            @NonNull GraphicOverlay graphicOverlay) {
        frameNum += 1;
        frameNumTemp += 1;
        graphicOverlay.add(
                new PoseGraphic(
                        graphicOverlay,
                        getPose.getPose(),
                        showInFrameLikelihood,
                        visualizeZ,
                        rescaleZForVisualization));
        if (isCalc){
            PoseCalculate Calculate = new PoseCalculate(
                    context,
                    getPose.getPose(),
                    gravity,
                    cardView,
                    userLevel);
            angleStatus = Calculate.getAngleStatus();
            angleArray = Calculate.getAngle();
            isGetSkeleton = Calculate.isGetSkeleton();
            calWrongSum();
            calWrongTemp();
            calDeviation();
            exportAngleLog();
        }
    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Pose detection failed!", e);
    }

    @Override
    protected boolean isMlImageEnabled(Context context) {
        return true;
    }

    public void calDeviation(){
        if(!isGetSkeleton){
            deviation++;
        }
    }

    public void calWrongSum(){
        for(int i = 0; i < angleStatus.length; i++){
            wrongSum[i] += angleStatus[i];
        }
    }

    public void calWrongTemp(){
        for(int i = 0; i < wrongTemp.length; i++){
            wrongTemp[i] += angleStatus[i];
        }
    }

    public void clearTemp(){
        Arrays.fill(wrongTemp, 0);
        frameNumTemp = 0;
    }

    public int getWrongForTTS(){
        int wrongAngleId = -1;
        int[] wrongOver90 = new int[angleNum];
        int index90 = 0;
        int[] wrongOver70 = new int[angleNum];
        int index70 = 0;
        int[] wrongOver20 = new int[angleNum];
        int index20 = 0;
        for (int i = 0; i < wrongTemp.length; i++){
            if (wrongTemp[i] >= frameNumTemp*0.9){
                wrongOver90[index90++] = i;
            }
            if (wrongTemp[i] >= frameNumTemp*0.7){
                wrongOver70[index70++] = i;
            }
            if (wrongTemp[i] >= frameNumTemp*0.2){
                wrongOver20[index20++] = i;
            }
        }
        if (index20 != 0){
            wrongAngleId = wrongOver20[(int)(Math.random() * (index20+1))];
        }
        if (index70 != 0){
            wrongAngleId = wrongOver70[(int)(Math.random() * (index70+1))];
        }
        if (index90 != 0){
            wrongAngleId = wrongOver90[(int)(Math.random() * (index90+1))];
        }
        System.out.println("wrongAngleId" + wrongAngleId);
        return wrongAngleId;
    }

    public float getOverallCompleteness(){
        float allWrong = 0;
        float standardNum = 0;
        float completeness;
        switch (cardView){
            case "Warrior2": standardNum = 9;break;
            case "Plank": standardNum = 4;break;
            case "Goddess": standardNum = 12;break;
            case "Chair": standardNum = 5;break;
            case "DownDog": standardNum = 5;break;
            case "Four_Limbed_Staff": standardNum = 3;break;
            case "Boat": standardNum = 2;break;
            case "Rejuvenation": standardNum = 2;break;
            case "Star": standardNum = 8;break;
            case "Tree": standardNum = 7;break;
        }
        for(float num : wrongSum){
            allWrong += num;
        }
        System.out.println("allWrong :　"+allWrong);
        System.out.println("myFrame : "+frameNum);
        System.out.println(("myDeviation : "+deviation));
        if(frameNum-deviation == 0){
            completeness = 0;
        }
        else{
            float unCompleteness = allWrong/(frameNum*standardNum-deviation);
            completeness = 100 - unCompleteness*100;
            completeness = (float)(Math.round(completeness*100.0)/100.0);
        }
        return completeness;
    }

    public String[] getJointsCompleteness(){
        int jointNum = 24;
        float[] unCompleteness = new float[jointNum];
        float[] completeness = new float[jointNum];
        String[] jointCompleteness = new String[jointNum];
        PoseStandardDBHandler db = new PoseStandardDBHandler(context);
        poseStandard = db.getPoseStandard(cardView).getPoseStandard();
        for(int i=0;i<wrongSum.length;i++){
            if(frameNum-deviation == 0){
                completeness[i] = 0;
            }
            else{
                unCompleteness[i] = (wrongSum[i]/(frameNum-deviation));
                completeness[i] = 100 - unCompleteness[i]*100;
                completeness[i] = (float)(Math.round(completeness[i]*100.0)/100.0);

            }
            if(poseStandard.get(i) == null || poseStandard.get(i).length() == 0){
                jointCompleteness[i] = null;
            }
            else{
                jointCompleteness[i] = Float.toString(completeness[i]);
                jointCompleteness[i] = jointCompleteness[i];
            }
        }
        return jointCompleteness;
    }

    public void exportAngleLog() {
        String fileName = "AngleLog";
        try {
            File fileLocation = new File(context.getFilesDir(), fileName + ".txt");
            fileLocation.createNewFile();
            FileOutputStream fos = new FileOutputStream(fileLocation,true);
            String wr = Arrays.toString(angleArray) + System.getProperty("line.separator");
            fos.write(wr.getBytes());
            fos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}