package com.charjack.bohe.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charjack.bohe.MainActivity;
import com.charjack.bohe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectFragment extends Fragment {

    private MainActivity mainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) context;
    }
    public SelectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select, container, false);
    }


}
