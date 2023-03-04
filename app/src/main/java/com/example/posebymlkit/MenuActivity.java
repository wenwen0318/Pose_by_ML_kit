package com.example.posebymlkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.posebymlkit.database.TrainMenu;
import com.example.posebymlkit.database.TrainMenuDBHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;

    String menuName;

    TextView menuNameTextView;
    TextView menuIll;

    RecyclerView menuRecyclerView;
    MyListAdapter menuListAdapter;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    Dialog dialog;
    View viewDialog;

    RadioButton btn_easy,btn_hard;
    Button btn_cancel,btn_check;

    TrainMenuDBHandler tm = new TrainMenuDBHandler(this);

    int userLevel = 3;
    int[] menu;
    int menuLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pose);


        intent = new Intent();
        bundle = getIntent().getExtras();
        menuName = bundle.getString("menuName");

        menuNameTextView = findViewById(R.id.menuNameTextView);
        menuNameTextView.setText(menuName);

        TrainMenu trainMenu = tm.getMenu(menuName);
        for (int i = 1;i<=20;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            if (trainMenu.getPose(i) != null){
                hashMap.put("num",String.format("%02d",i));
                hashMap.put("poseName",trainMenu.getPose(i));
                hashMap.put("poseTime",trainMenu.getTime(i) + "s");
                System.out.println(hashMap);
                arrayList.add(hashMap);
            }
        }

        menuRecyclerView = findViewById(R.id.menuRecycleView);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        menuListAdapter = new MyListAdapter();
        menuRecyclerView.setAdapter(menuListAdapter);

        Button btn_startPractice = findViewById(R.id.btn_startPractice_menu);

        //開始練習
        btn_startPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog();
            }
        });
    }

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder{

            private final TextView poseNum,poseName,poseTime;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                poseNum = itemView.findViewById(R.id.poseNum);
                poseName = itemView.findViewById(R.id.poseName);
                poseTime = itemView.findViewById(R.id.poseTime);
            }
        }

        @NonNull
        @Override
        public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_pose_layout,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, int position) {

            holder.poseNum.setText(arrayList.get(position).get("num"));
            holder.poseName.setText(arrayList.get(position).get("poseName"));
            holder.poseTime.setText(arrayList.get(position).get("poseTime"));

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }

    private void getDialog() {
        dialog = new Dialog(MenuActivity.this);

        viewDialog = getLayoutInflater().inflate(R.layout.menu_dialog_layout , null);
        dialog.setContentView(viewDialog);

        btn_easy = viewDialog.findViewById(R.id.btn_easy);
        btn_hard = viewDialog.findViewById(R.id.btn_hard);
        btn_cancel = viewDialog.findViewById(R.id.cancel);
        btn_check  = viewDialog.findViewById(R.id.check);
        bundle = getIntent().getExtras();
        menuLength = bundle.getInt("menuLength");
        menu = bundle.getIntArray("myMenu");
        for(int i=0;i<menuLength;i++){
            Log.i("MenuActivity", ""+menu[i]);
        }
        System.out.println("menuIn : "+menuLength);

        dialog.show();
        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLevel = 3;
            }
        });
        btn_hard.setOnClickListener(new View.OnClickListener() {
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
                for(int i=2;i<menuLength;i+=3){
                    menu[i] = userLevel;
                }
                intent.setClass(MenuActivity.this, LivePreviewActivity.class);
                bundle.putIntArray("myMenu", menu);
                bundle.putInt("userLevel", userLevel);
                bundle.putInt("menuLength", menuLength);
                intent.putExtras(bundle);
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }
}
