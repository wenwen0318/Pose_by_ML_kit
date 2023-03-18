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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
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

    TrainMenu trainMenu;
    TrainMenuDBHandler tm = new TrainMenuDBHandler(this);

    int userLevel = 3;
    int[] menu;
    int menuLength;
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

        getPoseToList();

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
                    intent.setClass(MenuActivity.this, AddPoseActivity.class);
                    bundle.putString("menuName", menuName);
                    bundle.putInt("menuLength", menuLength+1);
                    intent.putExtras(bundle);
                    startActivity(intent);
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
                getStartDialog();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        getPoseToList();
        poseListAdapter.notifyDataSetChanged();
    }

    private void getPoseToList(){
        menuLength = 0;
        trainMenu = tm.getMenu(menuName);
        arrayList.clear();
        for (int i = 1;i<=20;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            if (trainMenu.getPose(i) == null){
                break;
            }
            hashMap.put("num",String.format("%02d",i));
            hashMap.put("poseName",trainMenu.getPose(i));
            hashMap.put("poseTime",trainMenu.getTime(i) + "s");
            arrayList.add(hashMap);
            menuLength = i;
        }
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

            int resId = getApplicationContext().getResources().getIdentifier(
                            arrayList.get(position).get("poseName").toLowerCase(),
                            "drawable",
                            getPackageName());
            holder.poseNum.setText(arrayList.get(position).get("num"));
            holder.poseName.setText(arrayList.get(position).get("poseName"));
            holder.poseTime.setText(arrayList.get(position).get("poseTime"));
            holder.poseImage.setImageResource(resId);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    intent.setClass(MenuActivity.this, AddPoseActivity.class);
                    bundle.putString("menuName", menuName);
                    bundle.putInt("menuLength", position+1);
                    intent.putExtras(bundle);
                    startActivity(intent);
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
                        getPoseToList();
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

}
