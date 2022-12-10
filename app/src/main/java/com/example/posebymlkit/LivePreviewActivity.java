package com.example.posebymlkit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;


import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.google.android.gms.common.annotation.KeepName;
import com.example.posebymlkit.preference.PreferenceUtils;

import android.content.Intent;

import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions;
import com.example.posebymlkit.objectdetector.ObjectDetectorProcessor;

import com.example.posebymlkit.posedetector.PoseDetectorProcessor;

import com.google.mlkit.vision.pose.PoseDetectorOptionsBase;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

    HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(this);

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    String cardView;
    int userLevel;
    int time;

    String label = "";
    int[] wrongHint;
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
        if(cardView.equals("Warrior2")){
            switch (wrongHint[0]){
                case 3 :
                    wrongStr += "左膝伸直";
                    break;
                case 4 :
                    wrongStr += "右手伸直";
                    break;
                case 5 :
                    wrongStr += "左手伸直";
                    break;
                case 8 :
                    wrongStr += "右肩保持平坦";
                    break;
                case 9 :
                    wrongStr += "左肩保持平坦";
                    break;
                case 10 :
                    wrongStr += "身體與地板保持垂直";
                    break;
                case 11 :
                    wrongStr += "右膝勿超過右腳腳尖";
                    break;
                case 13 :
                    wrongStr += "右大腿與地板平行";
                    break;
                case 14 :
                    if(wrongHint[1] == 1){
                        wrongStr += "左腳張開往下坐";
                        break;
                    }
                    else if(wrongHint[1] == 2){
                        wrongStr += "左腳張太開";
                        break;
                    }
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

    Runnable TTSWrongHint = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 5000);
            wrongHint = pdp.getWrongForTTS();
            startTTS();
            pdp.clearWrongTem();
        }
    };

    Runnable timeCountdown = new Runnable() {
        @Override
        public void run() {
            tts.speak("練習結束",TextToSpeech.QUEUE_ADD,null,null);
            handler.postDelayed(this, time);
            overallCompleteness = pdp.getOverallCompleteness();
            jointCompleteness = pdp.getJointsCompleteness();

            Calendar calendar= Calendar.getInstance();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println(dateFormat.format(calendar.getTime()));
            hr.addHistoricalRecord(new HistoricalRecord(
                    cardView,
                    dateFormat.format(calendar.getTime()),
                    userLevel,
                    overallCompleteness,
                    Integer.toString(jointCompleteness[0]),Integer.toString(jointCompleteness[1]),
                    Integer.toString(jointCompleteness[2]),Integer.toString(jointCompleteness[3]),
                    Integer.toString(jointCompleteness[4]),Integer.toString(jointCompleteness[5]),
                    Integer.toString(jointCompleteness[6]),Integer.toString(jointCompleteness[7]),
                    Integer.toString(jointCompleteness[8]),Integer.toString(jointCompleteness[9]),
                    Integer.toString(jointCompleteness[10]),
                    Integer.toString(jointCompleteness[11]),Integer.toString(jointCompleteness[12]),
                    Integer.toString(jointCompleteness[13]),
                    Integer.toString(jointCompleteness[14])));
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