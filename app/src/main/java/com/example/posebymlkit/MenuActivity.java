package com.example.posebymlkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.posebymlkit.database.TrainMenu;
import com.example.posebymlkit.database.TrainMenuDBHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;

    String menuName;

    TextView menuNameTextView;
    TextView menuIllTextView;

    RecyclerView poseRecyclerView;
    MyListAdapter poseListAdapter;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    FloatingActionButton fab;

    Dialog startDialog;
    View viewStartDialog;
    RadioButton btn_easy,btn_hard;
    Button btn_cancel,btn_check;

    Dialog setPoseDialog;
    View viewSetPoseDialog;
    Spinner poseSpinner;
    TextView setPoseMode,timeSet;
    Button btn_timeSub,btn_timeAdd, btn_setPose_cancel, btn_setPose_check;

    TrainMenu trainMenu;
    TrainMenuDBHandler tm;

    int userLevel = 3;
    int[] menu;
    int menuLength = 0;
    String MODE = "menu";
    int cameraFacing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pose);

        intent = new Intent();
        bundle = getIntent().getExtras();
        menuName = bundle.getString("menuName");
        cameraFacing = bundle.getInt("camera_facing");

        menuNameTextView = findViewById(R.id.menuNameTextView);
        menuNameTextView.setText(menuName);

        tm = new TrainMenuDBHandler(this);
        trainMenu = tm.getMenu(menuName);

        for (int i = 1;i<=20;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            if (trainMenu.getPose(i) == null){
                break;
            }
            hashMap.put("num",String.format("%02d",i));
            hashMap.put("poseName",trainMenu.getPose(i));
            hashMap.put("poseTime",trainMenu.getTime(i) + getResources().getString(R.string.second));
            arrayList.add(hashMap);
            menuLength = i;
        }

        menuIllTextView = findViewById(R.id.menuIllTextView);
        menuIllTextView.setText(trainMenu.getIll());

        poseRecyclerView = findViewById(R.id.poseRecyclerView);
        poseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        poseRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        poseListAdapter = new MyListAdapter();
        poseRecyclerView.setAdapter(poseListAdapter);
        recyclerViewAction(poseRecyclerView, poseListAdapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuLength < 20){
                    getSetPoseDialog("Add",null,30 + getResources().getString(R.string.second),menuLength);
                }
                else {
                    Toast.makeText(MenuActivity.this,R.string.list_full,Toast.LENGTH_LONG).show();
                }
            }
        });

        Button btn_startPractice = findViewById(R.id.btn_startPractice_menu);

        btn_startPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuLength == 0){
                    Toast.makeText(MenuActivity.this,"清單為空，無法訓練",Toast.LENGTH_LONG).show();
                }
                else {
                    getStartDialog();
                }
            }
        });
    }

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder{

            private final TextView poseNum,poseName,poseTime;
            ImageView poseImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                poseNum = itemView.findViewById(R.id.poseNum);
                poseName = itemView.findViewById(R.id.poseName);
                poseTime = itemView.findViewById(R.id.poseTime);
                poseImage = itemView.findViewById(R.id.poseImg);
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
        public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            String poseName = arrayList.get(position).get("poseName");
            if (poseName != null){
                int resId = getApplicationContext().getResources().getIdentifier(
                        arrayList.get(position).get("poseName").toLowerCase(),
                        "drawable",
                        getPackageName());
                holder.poseNum.setText(arrayList.get(position).get("num"));
                holder.poseName.setText(arrayList.get(position).get("poseName"));
                holder.poseTime.setText(arrayList.get(position).get("poseTime"));
                holder.poseImage.setImageResource(resId);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!arrayList.get(position).get("poseName").equals("Rest")){
                        intent.setClass(MenuActivity.this, uniPoseInMenuIllActivity.class);
                        bundle.putString("poseName", arrayList.get(position).get("poseName"));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    getSetPoseDialog("Edit",
                            arrayList.get(position).get("poseName"),
                            arrayList.get(position).get("poseTime"),
                            position);
                    return false;
                }
            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

    }

    private void recyclerViewAction(RecyclerView recyclerView, final MyListAdapter myAdapter){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT); //RecyclerView操作類型
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //管理滑動情形
                int position = viewHolder.getAdapterPosition();
                switch (direction) {
                    case ItemTouchHelper.LEFT:
                    case ItemTouchHelper.RIGHT:
                        trainMenu.remove(position);
                        tm.updateTrainMenu(trainMenu);
                        arrayList.clear();
                        for (int i = 1;i<=20;i++){
                            HashMap<String,String> hashMap = new HashMap<>();
                            if (trainMenu.getPose(i) == null){
                                break;
                            }
                            hashMap.put("num",String.format("%02d",i));
                            hashMap.put("poseName",trainMenu.getPose(i));
                            hashMap.put("poseTime",trainMenu.getTime(i) + getResources().getString(R.string.second));
                            arrayList.add(hashMap);
                            menuLength = i - 1;
                        }
                        myAdapter.notifyItemRemoved(position);
                        myAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }

    private void getStartDialog() {
        startDialog = new Dialog(MenuActivity.this);

        viewStartDialog = getLayoutInflater().inflate(R.layout.menu_dialog_layout , null);
        startDialog.setContentView(viewStartDialog);

        btn_easy = viewStartDialog.findViewById(R.id.btn_easy);
        btn_hard = viewStartDialog.findViewById(R.id.btn_hard);
        btn_cancel = viewStartDialog.findViewById(R.id.cancel);
        btn_check  = viewStartDialog.findViewById(R.id.check);
        bundle = getIntent().getExtras();
        menuLength = bundle.getInt("menuLength");
        menu = bundle.getIntArray("myMenu");
        for(int i=0;i<menuLength;i++){
            Log.i("MenuActivity", ""+menu[i]);
        }

        startDialog.show();
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
                startDialog.dismiss();
            }
        });
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(MenuActivity.this, LivePreviewActivity.class);
                bundle.putString("menuName", menuName);
                bundle.putInt("userLevel", userLevel);
                bundle.putString("mode", MODE);
                bundle.putInt("camera_facing", cameraFacing);
                intent.putExtras(bundle);
                startActivity(intent);
                startDialog.dismiss();
            }
        });
    }

    private void getSetPoseDialog(String mode,String pose,String time,int position) {
        setPoseDialog = new Dialog(MenuActivity.this);

        String[] poses = {"Warrior2", "Plank", "Goddess", "Chair", "Downdog", "Four_Limbed_staff",
                "Boat", "Rejuvenation", "Star", "Tree", "Rest"};
        String[] selectedPose = new String[1];

        viewSetPoseDialog = getLayoutInflater().inflate(R.layout.add_pose_dialog_layout, null);
        setPoseDialog.setContentView(viewSetPoseDialog);

        setPoseMode = viewSetPoseDialog.findViewById(R.id.setPoseMode);
        poseSpinner = viewSetPoseDialog.findViewById(R.id.poseSpinner);
        btn_timeAdd = viewSetPoseDialog.findViewById(R.id.btn_timeAdd);
        timeSet = viewSetPoseDialog.findViewById(R.id.timeSet);
        btn_timeSub = viewSetPoseDialog.findViewById(R.id.btn_timeSub);
        btn_setPose_cancel = viewSetPoseDialog.findViewById(R.id.cancel);
        btn_setPose_check = viewSetPoseDialog.findViewById(R.id.check);

        timeSet.setText(time);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, poses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        poseSpinner.setAdapter(adapter);

        int spinnerPosition = adapter.getPosition(pose);
        poseSpinner.setSelection(spinnerPosition);

        switch (mode){
            case "Add" : {
                setPoseMode.setText("添加姿勢");
                break;
            }
            case "Edit" : {
                setPoseMode.setText("編輯姿勢");
                break;
            }
        }

        setPoseDialog.show();

        poseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                selectedPose[0] = parent.getSelectedItem().toString();
            }
        });
        btn_timeSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = Integer.parseInt(timeSet.getText().toString().substring(0,2));
                time -= 10;
                if (time < 10) time = 10;
                String timeStr = time + getResources().getString(R.string.second);
                timeSet.setText(timeStr);
            }
        });
        btn_timeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = Integer.parseInt(timeSet.getText().toString().substring(0,2));
                time += 10;
                if (time > 60) time = 60;
                String timeStr = time + getResources().getString(R.string.second);
                timeSet.setText(timeStr);
            }
        });

        btn_setPose_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPoseDialog.dismiss();
            }
        });
        btn_setPose_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add pose to recycleView and DB
                trainMenu.setPose(position+1, selectedPose[0]);
                trainMenu.setTime(position+1, Integer.parseInt(timeSet.getText().toString().substring(0,2)));
                tm.updateTrainMenu(trainMenu);
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("num",String.format("%02d",position+1));
                hashMap.put("poseName",trainMenu.getPose(position+1));
                hashMap.put("poseTime",trainMenu.getTime(position+1) + getResources().getString(R.string.second));
                if (arrayList.size() == position){
                    arrayList.add(hashMap);
                    menuLength += 1;
                }
                else {
                    arrayList.set(position,hashMap);
                }
                poseListAdapter.notifyItemChanged(position);
                setPoseDialog.dismiss();
            }
        });
    }
}
