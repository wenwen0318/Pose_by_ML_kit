package com.example.posebymlkit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class appIllActivity extends AppCompatActivity {

    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_uni_ill);

        Button button2 = findViewById(R.id.toMenuIllBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenuIll();
            }
        });

        Button button3 = findViewById(R.id.toSetIllBtn);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSetIll();
            }
        });

        toUniIll();
    }

    public void toUniIll(){
        setContentView(R.layout.layout_uni_ill);

        Button button2 = findViewById(R.id.toMenuIllBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenuIll();
            }
        });

        Button button3 = findViewById(R.id.toSetIllBtn);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSetIll();
            }
        });
    }

    public void toMenuIll(){
        setContentView(R.layout.layout_menu_ill);

        Button button1 = findViewById(R.id.toUniIllBtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toUniIll();
            }
        });

        Button button3 = findViewById(R.id.toSetIllBtn);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSetIll();
            }
        });

    }

    public void toSetIll(){
        setContentView(R.layout.layout_setting_ill);

        Button button1 = findViewById(R.id.toUniIllBtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toUniIll();
            }
        });

        Button button2 = findViewById(R.id.toMenuIllBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenuIll();
            }
        });

    }
}
