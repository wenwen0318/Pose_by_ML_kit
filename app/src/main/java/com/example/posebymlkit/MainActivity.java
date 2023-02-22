package com.example.posebymlkit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.example.posebymlkit.database.PoseStandardDBHandler;
import com.example.posebymlkit.database.PoseStandard;
import com.example.posebymlkit.database.PoseWrongTTSDBHandler;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCameraPermission();
        getWriteStoragePermission();
        getReadStoragePermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getStoragePermission();
        }

        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new PoseListFragment()).commit();

        PoseStandardDBHandler db = new PoseStandardDBHandler(this);
        db.poseStandardInit();
        PoseWrongTTSDBHandler pwt = new PoseWrongTTSDBHandler(this);
        pwt.poseWrongTTSInit();
    }

    private final BottomNavigationView.OnItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.home) {
            selectedFragment = new PoseListFragment();
        } else if (itemId == R.id.userData) {
            selectedFragment = new TrainMenuFragment();
        } else if (itemId == R.id.set) {
            selectedFragment = new SettingFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
        }
        return true;
    };

    public void getCameraPermission(){
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
        }
    }

    public void getWriteStoragePermission(){
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    public void getReadStoragePermission(){
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void getStoragePermission(){
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},1);
        }
    }
}