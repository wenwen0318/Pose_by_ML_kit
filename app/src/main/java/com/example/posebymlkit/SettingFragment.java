package com.example.posebymlkit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.camera2.CameraCharacteristics;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.google.common.escape.UnicodeEscaper;
import com.example.posebymlkit.database.MenuHistory;
import com.example.posebymlkit.database.MenuHistoryDBHandler;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    Intent intent = new Intent();
    Button historyBtn;
    Button removeSqlBtn;
    Button transformCameraLen;
    Switch sw;
    Button illBtn;
    Button mailBtn;
    Button notificationBtn;
    SharedPreferences cameraSource;
    SharedPreferences.Editor editor;
    int camera_facing = CameraCharacteristics.LENS_FACING_BACK;
    boolean status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);


        historyBtn = view.findViewById(R.id.historyBtn);
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(SettingFragment.this.getContext(), HistoryListActivity.class);
                startActivity(intent);
            }
        });

        removeSqlBtn = view.findViewById(R.id.removeSQL);
        removeSqlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog();
            }
        });

        cameraSource = PreferenceManager.getDefaultSharedPreferences(getActivity());
        camera_facing = cameraSource.getInt("camera_facing", CameraCharacteristics.LENS_FACING_BACK);
        sw = view.findViewById(R.id.cameraSwitch);
        sw.setChecked(camera_facing == CameraCharacteristics.LENS_FACING_FRONT);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                transformCamera(isChecked);
                editor = cameraSource.edit();
                editor.putInt("camera_facing", camera_facing);
                editor.apply();
            }
        });

        transformCameraLen = view.findViewById(R.id.transformCameraLen);
        transformCameraLen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = !sw.isChecked();
                transformCamera(isChecked);
                sw.setChecked(isChecked);
                editor.putInt("camera_facing", camera_facing);
                editor.apply();
            }
        });

        illBtn = view.findViewById(R.id.illBtn);
        illBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(SettingFragment.this.getContext(), appIllActivity.class);
                startActivity(intent);
            }
        });

        mailBtn = view.findViewById(R.id.mailBtn);
        mailBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mailToDeveloper();
            }
        });

        return view;
    }

    private void mailToDeveloper() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"wen0318asd@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "App Question Request");
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    private void getDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setTitle(R.string.delete_record);
        builder.setMessage(R.string.delete_record_question);
        builder.setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HistoricalRecordDBHandler hr = new HistoricalRecordDBHandler(getActivity());
                hr.deleteHistoricalRecord();
                MenuHistoryDBHandler mh = new MenuHistoryDBHandler(getActivity());
                mh.deleteMenuHistoricalRecord();
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public void transformCamera(boolean isChecked){
        if(isChecked){
            camera_facing = CameraCharacteristics.LENS_FACING_FRONT;
            Toast.makeText(getContext(),"後鏡頭",Toast.LENGTH_SHORT).show();
        }
        else{
            camera_facing = CameraCharacteristics.LENS_FACING_BACK;
            Toast.makeText(getContext(),"前鏡頭",Toast.LENGTH_SHORT).show();
        }
    }

}