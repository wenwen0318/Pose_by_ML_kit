package com.example.posebymlkit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.icu.text.DecimalFormat;
import android.media.MediaRecorder;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.example.posebymlkit.database.MenuHistory;
import com.example.posebymlkit.database.MenuHistoryDBHandler;
import com.example.posebymlkit.database.PoseWrongTTSDBHandler;
import com.example.posebymlkit.database.TrainMenu;
import com.example.posebymlkit.database.TrainMenuDBHandler;
import com.google.android.gms.common.annotation.KeepName;
import com.example.posebymlkit.preference.PreferenceUtils;

import android.content.Intent;
import android.widget.ToggleButton;

import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions;
import com.example.posebymlkit.objectdetector.ObjectDetectorProcessor;

import com.example.posebymlkit.posedetector.PoseDetectorProcessor;

import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/** Live preview demo for ML Kit APIs. */
@KeepName
public class LivePreviewActivity extends AppCompatActivity {

    private static final String POSE_DETECTION = "Pose Detection";
    private static final String OBJECT_DETECTION = "Object Detection";

    private String model = POSE_DETECTION;

    private static final String TAG = "LivePreviewActivity";

    private CameraSource cameraSource = null;
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;

    private TextToSpeech tts = null;
    private Handler handler = new Handler();
    PoseDetectorProcessor pdp;
    ObjectDetectorProcessor odp;

    HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(this);
    PoseWrongTTSDBHandler pwt = new PoseWrongTTSDBHandler(this);
    MenuHistoryDBHandler mh = new MenuHistoryDBHandler(this);

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    String poseName;
    int time;
    int userLevel;
    String date;
    TextView currentPoseName;

    String label = "";
    int[] wrongHint;
    float overallCompleteness;
    String[] jointCompleteness;

    String MODE;
    String menuName;
    int camera_facing;
    ArrayList<String> poseList = new ArrayList<String>();
    ArrayList<Integer> timeList = new ArrayList<Integer>();
    TrainMenu trainMenu;
    MenuHistory menuHistory;
    int index;

    SensorManager sensorManager;
    Sensor accelerometer;
    Sensor magnetometer;

    boolean isCalc = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_vision_live_preview);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss");
        date = dateFormat.format(calendar.getTime());

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

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListener, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);

        bundle = getIntent().getExtras();
        MODE = bundle.getString("mode");
        userLevel = bundle.getInt("userLevel");
        camera_facing = bundle.getInt("camera_facing");
        if(MODE.equals("pose")){
            poseList.add(bundle.getString("cardView"));
            timeList.add(bundle.getInt("time"));
        }
        else if(MODE.equals("menu")){
            menuName = bundle.getString("menuName");
            TrainMenuDBHandler db = new TrainMenuDBHandler(LivePreviewActivity.this);
            trainMenu = db.getMenu(menuName);
            poseList = trainMenu.getAllPose();
            timeList = trainMenu.getAllTime();
            menuHistory = new MenuHistory();
            menuHistory.set(1, menuName);
            menuHistory.set(2, date);
            index = 3;
        }
        System.out.println("poseList:" + poseList);
        currentPoseName = findViewById(R.id.poseNameView);
        currentPoseName.setText("");
        createTTSSource();
        handler.postDelayed(remindPose, 3000);
        handler.postDelayed(readyTime, 10000);

//        createCameraSource(OBJECT_DETECTION);
//        handler.postDelayed(personDetection,100);
        //handler.postDelayed(runnable, 5000);
    }

    private void createCameraSource(String model) {
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = new CameraSource(this, graphicOverlay);
            cameraSource.setFacing(camera_facing);
        }

        try {
            switch (model) {
                case POSE_DETECTION:
                    PoseDetectorOptionsBase poseDetectorOptions =
                            PreferenceUtils.getPoseDetectorOptionsForLivePreview(this);
                    Log.i(TAG, "Using Pose Detector with options " + poseDetectorOptions);
                    boolean shouldShowInFrameLikelihood =
                            PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(this);
                    boolean visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(this);
                    boolean rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(this);
                    boolean runClassification = PreferenceUtils.shouldPoseDetectionRunClassification(this);
                    if(poseList.get(0).equals("Rest")){
                        handler.postDelayed(restRemind,10000);
                    }
                    else{
                        cameraSource.setMachineLearningFrameProcessor(
                                pdp = new PoseDetectorProcessor(
                                        this,
                                        poseDetectorOptions,
                                        isCalc,
                                        shouldShowInFrameLikelihood,
                                        visualizeZ,
                                        rescaleZ,
                                        runClassification,
                                        /* isStreamMode = */ false,
                                        poseList.get(0),
                                        userLevel));
                        handler.postDelayed(TTSWrongHint,5000);
                    }
                    handler.postDelayed(timeCountdown, timeList.get(0)*1000);
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
                cameraSource.setFacing(camera_facing);
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
                        case "简体中文":
                            tts.setLanguage(Locale.CHINA);
                            break;
                        case "繁體中文":
                            tts.setLanguage(Locale.CHINESE);
                            break;
                        case "Deutsch":
                            tts.setLanguage(Locale.GERMAN);
                            break;
                        case "日本語":
                            tts.setLanguage(Locale.JAPANESE);
                            break;
                        default :
                            tts.setLanguage(Locale.ENGLISH);
                            break;
                    }
                }
            });
        }
    }

    private void TTS(){
        List<String> poseWrongTTSList = pwt.getPoseWrongTTS(poseList.get(0)).getWrongTTS();
        String wrongStr = "";
        for(int i=0;i<poseWrongTTSList.size();i+=2){
            if(wrongHint[0] == (poseWrongTTSList.size()/2)){
                wrongStr += poseWrongTTSList.get(poseWrongTTSList.size()-1);
                wrongHint[0] = 0;
                wrongHint[1] = 0;
            }
            else if(wrongHint[0] == (i/2) && wrongHint[1]==1){
                wrongStr += poseWrongTTSList.get(i);
                wrongHint[0] = 0;
                wrongHint[1] = 0;
            }
            else if(wrongHint[0] == (i/2) && wrongHint[1]==2){
                wrongStr += poseWrongTTSList.get(i+1);
                wrongHint[0] = 0;
                wrongHint[1] = 0;
            }
        }
        tts.speak(wrongStr,TextToSpeech.QUEUE_ADD,null,null);
    }

    private void getHistoricalRecord(){
        if(poseName.equals("Rest")){
            jointCompleteness = new String[23];
            overallCompleteness = 100;
            for(int i=0;i<23;i++){
                jointCompleteness[i] = "100";
            }
        }
        else{
            overallCompleteness = pdp.getOverallCompleteness();
            jointCompleteness = pdp.getJointsCompleteness();
        }
        hr.addHistoricalRecord(new HistoricalRecord(
                MODE,
                poseName,
                date,
                userLevel,
                time,
                overallCompleteness,
                jointCompleteness[0], jointCompleteness[1],
                jointCompleteness[2], jointCompleteness[3],
                jointCompleteness[4], jointCompleteness[5],
                jointCompleteness[6], jointCompleteness[7],
                jointCompleteness[8], jointCompleteness[9],
                jointCompleteness[10], jointCompleteness[11],
                jointCompleteness[12], jointCompleteness[13],
                jointCompleteness[14], jointCompleteness[15],
                jointCompleteness[16], jointCompleteness[17],
                jointCompleteness[18], jointCompleteness[19],
                jointCompleteness[20], jointCompleteness[21],
                jointCompleteness[22]));
        handler.removeCallbacks(timeCountdown);
    }

    private void getResultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LivePreviewActivity.this);
        builder.setCancelable(false);
        if(MODE.equals("pose")){
            builder.setTitle("練習結束");
            builder.setMessage("總體正確率： "+overallCompleteness+"%");
            builder.setPositiveButton("詳細數據", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    intent.setClass(LivePreviewActivity.this, PracticeResultActivity.class);
                    bundle.putString("poseName", poseName);
                    bundle.putString("date",date);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else{
            mh.addMenuHistory(menuHistory);
            builder.setTitle("完成清單訓練");
            builder.setPositiveButton("詳細數據", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    intent.setClass(LivePreviewActivity.this, MenuResultActivity.class);
                    bundle.putString("menuName", menuName);
                    bundle.putString("menuDate", menuHistory.get(2));
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });
        }
        builder.setNegativeButton("回主頁面", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
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

    Runnable restRemind = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 10000);
            timeList.set(0,timeList.get(0)-10);
            if(timeList.get(0) != 0){
                tts.speak(getResources().getString(R.string.rest_time_remaining)+(timeList.get(0))+ getResources().getString(R.string.seconds), TextToSpeech.QUEUE_ADD,null,null);
            }
        }
    };

    Runnable readyTime = new Runnable() {
        @Override
        public void run() {
            if(!poseList.get(0).equals("Rest")){
                tts.speak(getResources().getString(R.string.start_practice), TextToSpeech.QUEUE_ADD,null,null);
            }

            if(MODE.equals("menu")){
                Calendar calendar= Calendar.getInstance();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
                date = dateFormat.format(calendar.getTime());
                menuHistory.set(index, date);
                index++;
            }

            poseName = poseList.get(0);
            time = timeList.get(0);
            model = POSE_DETECTION;
            isCalc = true;
            createCameraSource(model);
        }
    };

    Runnable TTSWrongHint = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 5000);
            wrongHint = pdp.getWrongForTTS();
            System.out.print("wrongHint : ");
            for (int a: wrongHint) {
                System.out.print(a+" ");
            }
            TTS();
            pdp.clearWrongTem();
        }
    };

    Runnable remindPose = new Runnable() {
        @Override
        public void run() {
            if(poseList.get(0).equals("Warrior2")){
                tts.speak(getResources().getString(R.string.next_pose)+"Warrior"+"two",TextToSpeech.QUEUE_ADD,null,null);
                tts.speak(getResources().getString(R.string.ready),TextToSpeech.QUEUE_ADD,null,null);
            }
            else if(poseList.get(0).equals("Four_Limbed_Staff")){
                tts.speak(getResources().getString(R.string.next_pose)+"Four"+"Limb"+"Staff",TextToSpeech.QUEUE_ADD,null,null);
                tts.speak(getResources().getString(R.string.ready),TextToSpeech.QUEUE_ADD,null,null);
            }
            else if(poseList.get(0).equals("Rest")){
                tts.speak(getResources().getString(R.string.rest_time),TextToSpeech.QUEUE_ADD,null,null);
            }
            else{
                tts.speak(getResources().getString(R.string.next_pose)+poseList.get(0),TextToSpeech.QUEUE_ADD,null,null);
                tts.speak(getResources().getString(R.string.ready),TextToSpeech.QUEUE_ADD,null,null);
            }
            currentPoseName = findViewById(R.id.poseNameView);
            currentPoseName.setText(poseList.get(0));
        }
    };

    Runnable timeCountdown = new Runnable() {
        @Override
        public void run() {
            if (cameraSource != null) {
                cameraSource.release();
            }
            if(poseList.get(0).equals("Rest")){
                handler.removeCallbacks(restRemind);
                tts.speak(getResources().getString(R.string.rest_over), TextToSpeech.QUEUE_ADD, null, null);
            }
            else{
                tts.speak(getResources().getString(R.string.finish_practice), TextToSpeech.QUEUE_ADD, null, null);
            }
            getHistoricalRecord();
            poseList.remove(0);
            timeList.remove(0);
            handler.removeCallbacks(TTSWrongHint);
            handler.removeCallbacks(remindPose);
            handler.removeCallbacks(readyTime);
            handler.removeCallbacks(restRemind);
            if (poseList.isEmpty()) {
                if(MODE.equals("menu")){
                    tts.speak(getResources().getString(R.string.training_completed),TextToSpeech.QUEUE_ADD,null,null);
                }
                getResultDialog();
            }
            else {
                startCameraSource();
                model = POSE_DETECTION;
                createCameraSource(model);
                handler.postDelayed(remindPose, 3000);
                handler.postDelayed(readyTime, 10000);
            }
        }
    };

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        float[] gravity;
        float[] magnetic;

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                gravity = event.values.clone();
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // 計算重力向量
            float norm_Of_g = (float) Math.sqrt(x * x + y * y + z * z);
            x = x / norm_Of_g;
            y = y / norm_Of_g;
            z = z / norm_Of_g;

            // 將重力向量保存到一個陣列中
            float[] gravity = new float[3];
            gravity[0] = x;
            gravity[1] = y;
            gravity[2] = z;

            //System.out.println("gravity:" + gravity[0] + gravity[1] + gravity[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

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
        handler.removeCallbacks(remindPose);
        handler.removeCallbacks(restRemind);
        handler.removeCallbacks(readyTime);
    }

}