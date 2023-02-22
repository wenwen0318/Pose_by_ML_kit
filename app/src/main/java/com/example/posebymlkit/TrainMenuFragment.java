package com.example.posebymlkit;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainMenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrainMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserDataFragment.
     */
    // TODO: Rename and change types and number of parameters
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

    int[] menuList = {
            R.id.customMenuCardView,
            R.id.suggestMenuCardView1
    };
    int[] sugMenu1 = {
            R.id.sugMeu1CardView1, R.id.sugMeu1CardView1Time,
            R.id.sugMeu1CardView2, R.id.sugMeu1CardView2Time,
            R.id.sugMeu1CardView3, R.id.sugMeu1CardView3Time,
            R.id.sugMeu1CardView4, R.id.sugMeu1CardView4Time,
            R.id.sugMeu1CardView5, R.id.sugMeu1CardView5Time,
            R.id.sugMeu1CardView6, R.id.sugMeu1CardView6Time
    };
    Intent intent = new Intent();
    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_train_menu, container, false);

        for(int menu : menuList){
            CardView cardView = view.findViewById(menu);
            String myMenu = cardView.getTransitionName();
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.setClass(TrainMenuFragment.this.getContext(), MenuActivity.class);
                    switch (myMenu){
                        case "suggestMenu1" :
                            bundle.putIntArray("myMenu", sugMenu1);
                            break;
                    }
//                    bundle.putString("cardView", cardView.getTransitionName());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        return view;
    }

}