package com.example.posebymlkit;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraCharacteristics;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.posebymlkit.database.TrainMenu;
import com.example.posebymlkit.database.TrainMenuDBHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class TrainMenuFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ListView menuListView;
    SimpleAdapter simpleAdapter;

    FloatingActionButton fab;

    ArrayList<String> menuNames;
    TrainMenuDBHandler tm;

    public TrainMenuFragment() {

    }

    public static TrainMenuFragment newInstance(String param1, String param2) {
        TrainMenuFragment fragment = new TrainMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    int cameraFacing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_train_menu, container, false);

        menuListView = view.findViewById(R.id.menuListView);

        tm = new TrainMenuDBHandler(getActivity());
        menuNames = tm.getAllTrainMenuName();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        cameraFacing = preferences.getInt("camera_facing", CameraCharacteristics.LENS_FACING_BACK);

        String[] from = {"menuName","menuImageUri"};
        int[] value = {R.id.menuName,R.id.menuImageView};
        simpleAdapter =
                new SimpleAdapter(getContext(), arrayList, R.layout.menu_list_layout, from, value);
        menuListView.setAdapter(simpleAdapter);

        menuListView.setOnItemClickListener(onItemClickListener);
        menuListView.setOnItemLongClickListener(onItemLongClickListener);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(),AddMenuActivity.class);
                startActivity(intent);
            }
        });

        return view;
    };

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("TMF on Resume");
        menuNames = tm.getAllTrainMenuName();
        arrayList.clear();
        for (String menuName:menuNames){
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("menuName",menuName);
            Uri imageUri = getImageUri(menuName);
            hashMap.put("menuImageUri",imageUri.toString());
            arrayList.add(hashMap);
        }
        simpleAdapter.notifyDataSetChanged();
    }

    public Uri getImageUri(String menuName){
        File imageFile = new File(getActivity().getFilesDir(),menuName+".jpg");
        String imageFilePath = imageFile.getAbsolutePath();
        Uri imageUri = Uri.parse(imageFilePath);
        return imageUri;
    }

    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView menuName = view.findViewById(R.id.menuName);
            intent.setClass(getActivity(),MenuActivity.class);
            bundle.putString("menuName",menuName.getText().toString());
            bundle.putInt("camera_facing", cameraFacing);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    private final AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("刪除清單?");

            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String menuName = arrayList.get(position).get("menuName");
                    tm.deleteTrainMenu(menuName);
                    arrayList.remove(position);
                    simpleAdapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.create().show();
            return true;
        }
    };
}