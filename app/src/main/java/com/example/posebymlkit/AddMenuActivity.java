package com.example.posebymlkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.posebymlkit.database.TrainMenu;
import com.example.posebymlkit.database.TrainMenuDBHandler;

import java.util.ArrayList;

public class AddMenuActivity extends AppCompatActivity {

    String menuName;

    EditText editMenuName,editMenuIll;
    Button btn_addPose_cancel,btn_addPose_check;

    TrainMenuDBHandler tm = new TrainMenuDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);


        editMenuName = findViewById(R.id.editMenuName);
        editMenuIll = findViewById(R.id.editMenuIll);
        btn_addPose_cancel = findViewById(R.id.cancel);
        btn_addPose_check  = findViewById(R.id.check);

        btn_addPose_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_addPose_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add pose to recycleView and DB
                menuName = editMenuName.getText().toString();
                if (menuName.equals("")){
                    Toast.makeText(AddMenuActivity.this,"請輸入清單名稱",Toast.LENGTH_LONG).show();
                }
                else {
                    ArrayList<String> menuNames = tm.getAllTrainMenuName();
                    if (menuNames.contains(menuName)){
                        Toast.makeText(AddMenuActivity.this,"清單名已存在，請重新命名",Toast.LENGTH_LONG).show();
                    }
                    else{
                        tm.addTrainMenu(new TrainMenu(menuName,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0,
                            null,0
                        ));
                        finish();
                    }

                }
            }
        });
    }
}