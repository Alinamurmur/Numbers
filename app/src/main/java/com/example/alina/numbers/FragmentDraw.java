package com.example.alina.numbers;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class FragmentDraw extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_circle, container, false);

        RelativeLayout relativeLayout = (RelativeLayout)rootView.findViewById(R.id.cir);
        relativeLayout.addView(new Circle(getActivity()));

        return rootView;
    }
}
