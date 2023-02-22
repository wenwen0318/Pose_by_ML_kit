package com.example.posebymlkit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.example.posebymlkit.database.PoseWrongTTS;
import com.example.posebymlkit.database.PoseWrongTTSDBHandler;
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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/** Live preview demo for ML Kit APIs. */
@KeepName
public class LivePreviewActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener{

    private static final String POSE_DETECTION = "Pose Detection";
    private static final String OBJECT_DETECTION = "Object Detection";

    private String model = OBJECT_DETECTION;

    private static final String TAG = "LivePreviewActivity";

    private CameraSource cameraSource = null;
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private MediaRecorder mediaRecorder;
    private SurfaceHolder surfaceHolder;

    private boolean isRecording = false;
    private boolean isSurfaceCreated = false;

    private TextToSpeech tts = null;
    private Handler handler = new Handler();
    PoseDetectorProcessor pdp;
    ObjectDetectorProcessor odp;

    HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(this);
    PoseWrongTTSDBHandler pwt = new PoseWrongTTSDBHandler(this);

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    String cardView;
    int userLevel;
    int time;
    String date;

    String label = "";
    int[] wrongHint;
    float overallCompleteness;
    String[] jointCompleteness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_vision_live_preview);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
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

        ToggleButton facingSwitch = findViewById(R.id.facing_switch);
        facingSwitch.setOnCheckedChangeListener(this);

        bundle = getIntent().getExtras();
        cardView = bundle.getString("cardView");
        userLevel = bundle.getInt("userLevel");
        time = bundle.getInt("time")*1000;

        TextView poseName = findViewById(R.id.poseNameView);
        poseName.setText(cardView);

        System.out.println("pose:"+ cardView+ " userLevel:"+ userLevel+ " time:"+ time);

        createCameraSource(OBJECT_DETECTION);

        surfaceHolder = preview.getSurfaceView().getHolder();
        surfaceHolder.addCallback(surfaceCallback);

        createTTSSource();

        handler.postDelayed(personDetection,100);

        //handler.postDelayed(runnable, 5000);
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

    private void createCameraSource(String model) {
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = new CameraSource(this, graphicOverlay);
        }

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

    private void TTS(){
        List<String> poseWrongTTSList = pwt.getPoseWrongTTS(cardView).getWrongTTS();
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
                    wrongStr += "右膝勿超過右腳腳尖";
                    break;
                case 12 :
                    wrongStr += "右大腿與地板平行";
                    break;
                case 15 :
                    if(wrongHint[1] == 1){
                        wrongStr += "左腳張開往下坐";
                        break;
                    }
                    else if(wrongHint[1] == 2){
                        wrongStr += "左腳張太開";
                        break;
                    }
                case 21 :
                    wrongStr += "身體與地板保持垂直";
                    break;
            }
        }
        else if(cardView.equals("Plank")){
            switch (wrongHint[0]){
                case 1 :
                    wrongStr += "臀部與身體呈直線";
                    break;
                case 3 :
                    wrongStr += "膝蓋與身體呈直線";
                    break;
                case 5 :
                    wrongStr += "手臂伸直";
                    break;
                case 17 :
                    if(wrongHint[1] == 1){
                        wrongStr += "手臂伸太出去，收一點";
                        break;
                    }
                    else if(wrongHint[1] == 2){
                        wrongStr += "手臂太進去，出來點";
                        break;
                    }
            }
        }
        else if(cardView.equals("Goddess")){
            switch (wrongHint[0]){
                case 2 :
                    if(wrongHint[1] == 1){
                        wrongStr += "右膝蹲太下去";
                        break;
                    }
                    else if(wrongHint[1] == 2){
                        wrongStr += "右膝蹲不夠下去";
                        break;
                    }
                case 3 :
                    if(wrongHint[1] == 1){
                        wrongStr += "左膝蹲太下去";
                        break;
                    }
                    else if(wrongHint[1] == 2){
                        wrongStr += "左膝蹲不夠下去";
                        break;
                    }
                case 4 :
                    if(wrongHint[1] == 1){
                        wrongStr += "右手肘張不夠開";
                        break;
                    }
                    else if(wrongHint[1] == 2){
                        wrongStr += "右手肘張太開";
                        break;
                    }
                case 5 :
                    if(wrongHint[1] == 1){
                        wrongStr += "左手肘張不夠開";
                        break;
                    }
                    else if(wrongHint[1] == 2){
                        wrongStr += "右手肘張太開";
                        break;
                    }
                case 8 :
                    wrongStr += "右手與地板平行";
                    break;
                case 9 :
                    wrongStr += "左手與地板平行";
                    break;
                case 10 :
                    wrongStr += "右膝不超過右腳尖";
                    break;
                case 11 :
                    wrongStr += "左膝不超過左腳尖";
                    break;
                case 12 :
                    wrongStr += "右大腿與地板平行";
                    break;
                case 13 :
                    wrongStr += "左大腿與地板平行";
                    break;
                case 21 :
                    wrongStr += "身體與地板保持垂直";
                    break;
            }
        }
        else if(cardView.equals("Chair")){
            switch (wrongHint[0]) {
                case 2:
                    if (wrongHint[1] == 1) {
                        wrongStr += "膝蓋蹲太低";
                        break;
                    } else if (wrongHint[1] == 2) {
                        wrongStr += "膝蓋蹲不夠低";
                        break;
                    }
                case 4:
                    wrongStr += "手肘伸直";
                    break;
                case 6:
                    wrongStr += "身體與大腿垂直";
                    break;
                case 10:
                    wrongStr += "膝蓋不超過腳尖";
                    break;
                case 18:
                    if (wrongHint[1] == 1) {
                        wrongStr += "手臂舉高點";
                        break;
                    } else if (wrongHint[1] == 2) {
                        wrongStr += "手臂舉太高";
                        break;
                    }
            }

        }
        else if(cardView.equals("DownDog")){
            switch (wrongHint[0]) {
                case 2:
                    wrongStr += "膝蓋挺直";
                    break;
                case 4:
                    wrongStr += "手肘伸直";
                    break;
                case 6:
                    wrongStr += "手臂往前伸";
                    break;
                case 20:
                    wrongStr += "腳跟卓第";
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
                finish();
            }
        });
        builder.setPositiveButton("詳細數據", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intent.setClass(LivePreviewActivity.this, PracticeResultActivity.class);
                bundle.putString("poseName",cardView);
                bundle.putString("date",null);
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
            System.out.print("wrongHint : ");
            for (int a: wrongHint) {
                System.out.print(a+" ");
            }
            TTS();
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

            hr.addHistoricalRecord(new HistoricalRecord(
                    cardView,
                    date,
                    userLevel,
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
            if (cameraSource != null) {
                cameraSource.release();
            }
            handler.removeCallbacks(personDetection);
            handler.removeCallbacks(TTSWrongHint);
            handler.removeCallbacks(timeCountdown);
            getResultDialog();
        }
    };

    private final SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            isSurfaceCreated = false;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            isSurfaceCreated = true;
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            startCameraSource();
        }
    };

    private void initMediaRecorder() {
        mediaRecorder = new MediaRecorder();//例項化

        //mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC); // 設定從麥克風採集聲音
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA); // 設定從攝像頭採集影象
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); // 設定視訊的輸出格式 為MP4
        //mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT); // 設定音訊的編碼格式
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264); // 設定視訊的編碼格式
        mediaRecorder.setVideoSize(352, 288); // 設定視訊大小
        mediaRecorder.setVideoFrameRate(20); // 設定幀率
        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());

        //設定視訊儲存路徑0
        File file = new File(ContextCompat.getExternalFilesDirs(this,Environment.DIRECTORY_DCIM)[0].getAbsolutePath()+File.separator + "VideoRecorder");;
        if (!file.exists()) {
            //多級目錄的建立
            boolean mkdir = file.mkdir();
            if (!mkdir) {
                Log.e(TAG, file.getPath() + "Directory creation failed.");
            }
        }
        Log.d("filePath",file.toString());
        String fileName = file.getPath() + File.separator + "VID_" + cardView + "_" + date + ".mp4";
        Log.d("fileName",fileName);
        mediaRecorder.setOutputFile(fileName);
    }

    private void startRecording() {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (Exception e) {
                isRecording = false;
                Log.e(TAG, e.getMessage());
            }
        }
    }

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