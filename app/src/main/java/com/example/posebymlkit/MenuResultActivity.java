package com.example.posebymlkit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.example.posebymlkit.database.MenuHistory;
import com.example.posebymlkit.database.MenuHistoryDBHandler;
import com.example.posebymlkit.database.TrainMenu;
import com.example.posebymlkit.database.TrainMenuDBHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuResultActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;
    String menuName;
    String date;
    TextView menuDataNameTextView, menuDataTextView, menuDateTextView;
    int sumTime = 0;
    int poseNum = 0;
    RecyclerView menuHistoryRecyclerView;
    MyListAdapter menuHistoryListAdapter;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    int menuLength;
    MenuHistory menuHistory = new MenuHistory();
    MenuHistoryDBHandler mh = new MenuHistoryDBHandler(MenuResultActivity.this);
    HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(MenuResultActivity.this);
    HistoricalRecord historicalRecord;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_result_layout);

        intent = new Intent();
        bundle = getIntent().getExtras();
        menuName = bundle.getString("menuName");
        date = bundle.getString("menuDate");
        menuHistory = mh.getMenuHistoryByMenuDate(date, 22);
        for(int i=3;i<22;i++){
            if(menuHistory.get(i) == null){
                break;
            }
            historicalRecord = hr.getHistoricalRecordByDate(menuHistory.get(i));
            sumTime += historicalRecord.getTime();
            if(!historicalRecord.getPoseName().equals("Rest")){
                poseNum += 1;
            }
        }

        menuDataNameTextView = findViewById(R.id.menuDataNameTextView);
        menuDataNameTextView.setText(menuName);

        menuDateTextView = findViewById(R.id.menuDateTextView);
        menuDateTextView.setText(date);

        menuDataTextView = findViewById(R.id.menuDataTextView);
        menuDataTextView.setText(sumTime+"秒鐘‧"+poseNum+"運動");

        getMenuToList();
        menuHistoryRecyclerView = findViewById(R.id.menuHistoryRecycleView);
        menuHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuHistoryRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        menuHistoryListAdapter = new MyListAdapter();
        menuHistoryRecyclerView.setAdapter(menuHistoryListAdapter);
//        recyclerViewAction(menuHistoryRecyclerView, menuHistoryListAdapter);
    }

    private void getMenuToList(){
        menuLength = 0;
        arrayList.clear();
        for (int i = 1;i<23;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            historicalRecord = hr.getHistoricalRecordByDate(menuHistory.get(i+2));
            if (menuHistory.get(i+2) == null){
                break;
            }
            hashMap.put("num",String.format("%02d",i));
            hashMap.put("poseName",historicalRecord.getPoseName());
            hashMap.put("poseTime",historicalRecord.getTime() + "s");
            hashMap.put("poseCompleteness", Float.toString(historicalRecord.getAllComplete())+"%");
            arrayList.add(hashMap);
            System.out.println("arrayList : "+arrayList);
            menuLength = i;
        }
    }

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

        class ViewHolder extends RecyclerView.ViewHolder{

            private final TextView poseNum,poseName,poseTime,poseCompleteness;
            ImageView poseImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                poseNum = itemView.findViewById(R.id.poseNum);
                poseName = itemView.findViewById(R.id.poseName);
                poseTime = itemView.findViewById(R.id.poseTime);
                poseImage = itemView.findViewById(R.id.poseImg);
                poseCompleteness = itemView.findViewById(R.id.poseCompleteness);
            }
        }

        @NonNull
        @Override
        public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_history_pose_layout,parent,false);
            return new MyListAdapter.ViewHolder(view);
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
            holder.poseCompleteness.setText(arrayList.get(position).get("poseCompleteness"));
            holder.poseImage.setImageResource(resId);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    intent.setClass(MenuActivity.this, AddPoseActivity.class);
//                    bundle.putString("menuName", menuName);
//                    bundle.putInt("menuLength", position+1);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                    return false;
//                }
//            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        getMenuToList();
        menuHistoryListAdapter.notifyDataSetChanged();
    }

//    private void recyclerViewAction(RecyclerView recyclerView, final MyListAdapter myAdapter){
//        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
//            @Override
//            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//                return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT); //RecyclerView操作類型
//            }
//
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                //管理滑動情形
//                int position = viewHolder.getAdapterPosition();
//                switch (direction) {
//                    case ItemTouchHelper.LEFT:
//                    case ItemTouchHelper.RIGHT:
//                        trainMenu.remove(position);
//                        tm.updateTrainMenu(trainMenu);
//                        getMenuToList();
//                        myAdapter.notifyItemRemoved(position);
//                        myAdapter.notifyDataSetChanged();
//                        break;
//                }
//            }
//        });
//        helper.attachToRecyclerView(recyclerView);
//    }
}


