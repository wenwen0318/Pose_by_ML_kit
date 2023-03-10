package com.example.posebymlkit;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.posebymlkit.database.HistoricalRecord;
import com.example.posebymlkit.database.HistoricalRecordDBHandler;
import com.example.posebymlkit.database.TrainMenu;
import com.example.posebymlkit.database.TrainMenuDBHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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

    Intent intent = new Intent();
    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_train_menu, container, false);

        menuListView = view.findViewById(R.id.menuListView);

        tm = new TrainMenuDBHandler(getActivity());
        menuNames = tm.getAllTrainMenuName();

        final ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

        for (String menuName:menuNames) {
            HashMap<String, String> hashMap = new HashMap<>();
            Log.d("menu:", menuName);
            hashMap.put("menuName", menuName);
            arrayList.add(hashMap);
        }
        String[] from = {"menuName"};
        int[] value = {R.id.menuName};
        simpleAdapter =
                new SimpleAdapter(requireActivity(), arrayList, R.layout.menu_list_layout, from, value);
        menuListView.setAdapter(simpleAdapter);

        menuListView.setOnItemClickListener(onItemClickListener);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(),AddMenuActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView menuName = view.findViewById(R.id.menuName);
            System.out.println(menuName.getText().toString());
            intent.setClass(getActivity(),MenuActivity.class);
            bundle.putString("menuName",menuName.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}