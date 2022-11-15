package com.example.posebymlkit;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PoseListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PoseListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PoseListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PoseListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PoseListFragment newInstance(String param1, String param2) {
        PoseListFragment fragment = new PoseListFragment();
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

    int[][] poseList = {
            {R.id.cardView1 ,R.id.textView1 },
            {R.id.cardView2 ,R.id.textView2 },
            {R.id.cardView3 ,R.id.textView3 },
            {R.id.cardView4 ,R.id.textView4 },
            {R.id.cardView5 ,R.id.textView5 },
            {R.id.cardView6 ,R.id.textView6 },
            {R.id.cardView7 ,R.id.textView7 },
            {R.id.cardView8 ,R.id.textView8 },
            {R.id.cardView9 ,R.id.textView9 },
            {R.id.cardView10,R.id.textView10},
    };

    Intent intent = new Intent();
    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pose_list, container, false);

        for (int[] ints : poseList) {
            CardView cardView = view.findViewById(ints[0]);
            TextView poseName = view.findViewById(ints[1]);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.setClass(PoseListFragment.this.getContext(), VideoActivity.class);
                    bundle.putString("poseName", poseName.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

        return view;
    }
}