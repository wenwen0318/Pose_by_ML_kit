package com.example.posebymlkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new PoseListFragment()).commit();

        tvRes = findViewById(R.id.textView_PermissionResult);

        boolean cameraHasGone = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            cameraHasGone = checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED;
        }

        boolean externalHasGone = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            externalHasGone = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions;
            if (!cameraHasGone && !externalHasGone) {//如果兩個權限都未取得
                permissions = new String[2];
                permissions[0] = Manifest.permission.CAMERA;
                permissions[1] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            } else if (!cameraHasGone) {//如果只有相機權限未取得
                permissions = new String[1];
                permissions[0] = Manifest.permission.CAMERA;
            } else if (!externalHasGone) {//如果只有存取權限未取得
                permissions = new String[1];
                permissions[0] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            } else {
                tvRes.setText("相機權限已取得\n儲存權限已取得");
                return;
            }
            requestPermissions(permissions, 100);
        }

        PoseStandardDBHandler db = new PoseStandardDBHandler(this);
        db.addPoseStandard(new PoseStandard("Warrior2",
                null,null,
                null,"170",
                "170","170",
                null,null,
                "170","170",
                "180",
                "90",null,
                "185",
                "55",
                null,null));
        db.addPoseStandard(new PoseStandard("Plank",
                null,"170",
                null,"170",
                null,"170",
                null,null,
                null,null,
                null,
                null,null,
                null,
                null,
                "90",null));
        db.addPoseStandard(new PoseStandard("Goddess",
                null,null,
                "90","90",
                "90","90",
                null,null,
                "170","170",
                "180",
                "90","90",
                "185",
                null,
                null,"185"));
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        StringBuffer word = new StringBuffer();
        switch (permissions.length) {
            case 1:
                if (permissions[0].equals(Manifest.permission.CAMERA)) word.append("相機權限");
                else word.append("儲存權限");
                if (grantResults[0] == 0) word.append("已取得");
                else word.append("未取得");
                word.append("\n");
                if (permissions[0].equals(Manifest.permission.CAMERA)) word.append("儲存權限");
                else word.append("相機權限");
                word.append("已取得");

                break;
            case 2:
                for (int i = 0; i < permissions.length; i++) {
                    if (permissions[i].equals(Manifest.permission.CAMERA)) word.append("相機權限");
                    else word.append("儲存權限");
                    if (grantResults[i] == 0) word.append("已取得");
                    else word.append("未取得");
                    if (i < permissions.length - 1) word.append("\n");
                }
                break;
        }
        tvRes.setText(word.toString());
    }
}