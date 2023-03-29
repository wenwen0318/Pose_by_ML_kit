package com.example.posebymlkit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.posebymlkit.database.TrainMenu;
import com.example.posebymlkit.database.TrainMenuDBHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AddMenuActivity extends AppCompatActivity {

    String imageUri;

    ImageView menuImageView;
    EditText editMenuName, editMenuIll;
    Button btn_addMenu_cancel, btn_addMenu_check;

    TrainMenuDBHandler tm = new TrainMenuDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        menuImageView = findViewById(R.id.menuImageView);
        editMenuName = findViewById(R.id.editMenuName);
        editMenuIll = findViewById(R.id.editMenuIll);
        btn_addMenu_cancel = findViewById(R.id.cancel);
        btn_addMenu_check = findViewById(R.id.check);

        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,false);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);
                //activityResultLaunch.launch(galleryIntent);
            }

        });

        btn_addMenu_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_addMenu_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add pose to recycleView and DB
                String menuName = editMenuName.getText().toString();
                String menuIll = editMenuIll.getText().toString();
                if (menuName.equals("")) {
                    Toast.makeText(AddMenuActivity.this, "請輸入清單名稱", Toast.LENGTH_LONG).show();
                } else {
                    ArrayList<String> menuNames = tm.getAllTrainMenuName();
                    if (menuNames.contains(menuName)) {
                        Toast.makeText(AddMenuActivity.this, "清單名已存在，請重新命名", Toast.LENGTH_LONG).show();
                    } else {
                        tm.addTrainMenu(new TrainMenu(menuName,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                null, 0,
                                menuIll
                        ));
                        try{
                            InputStream input = getContentResolver().openInputStream(Uri.parse(imageUri));
                            File file = new File(getFilesDir(), menuName+".jpg");
                            OutputStream output = new FileOutputStream(file);

                            byte[] buffer = new byte[4 * 1024]; // or other buffer size
                            int read;

                            while ((read = input.read(buffer)) != -1) {
                                output.write(buffer, 0, read);
                            }

                            output.flush();
                            output.close();
                            input.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }

                }
            }
        });
    }

    ActivityResultLauncher<Intent> activityResultLaunch =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    System.out.println("RESULT_DATA:" + result.getData().getData());
                    if (result.getResultCode() == RESULT_OK) {
                        imageUri = result.getData().getData().toString();
                        menuImageView.setImageURI(Uri.parse(imageUri));
                    }
                }
            });

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            imageUri = data.getData().toString();
            menuImageView.setImageURI(Uri.parse(imageUri));
        }
    }
}