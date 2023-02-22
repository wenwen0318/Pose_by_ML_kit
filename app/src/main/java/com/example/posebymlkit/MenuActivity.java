package com.example.posebymlkit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;
    Dialog dialog;
    View viewDialog;
    RadioButton btn_easy,btn_hard;
    Button btn_cancel,btn_check;
    int userLevel = 3;
    int[] menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pose);

        Button btn_startPractice = findViewById(R.id.btn_startPractice_menu);
        intent = new Intent();
        bundle = new Bundle();

        //開始練習
        btn_startPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog();
            }
        });
    }

    private void getDialog() {
        dialog = new Dialog(MenuActivity.this);

        viewDialog = getLayoutInflater().inflate(R.layout.menu_dialog_layout , null);
        dialog.setContentView(viewDialog);

        btn_easy = viewDialog.findViewById(R.id.btn_easy);
        btn_hard = viewDialog.findViewById(R.id.btn_hard);
        btn_cancel = viewDialog.findViewById(R.id.cancel);
        btn_check  = viewDialog.findViewById(R.id.check);
        menu = bundle.getIntArray("myMenu");

        dialog.show();
        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLevel = 3;
            }
        });
        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLevel = 2;
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(MenuActivity.this, LivePreviewActivity.class);
                bundle.putIntArray("myMenu", menu);
                bundle.putInt("userLevel", userLevel);
                intent.putExtras(bundle);
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }
}
