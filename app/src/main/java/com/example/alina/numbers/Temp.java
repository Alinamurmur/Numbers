package com.example.alina.numbers;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Temp extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        Context context = getActivity().getApplicationContext();
        FrameLayout layout = new FrameLayout(context);
        layout.setBackgroundColor(Color.GRAY);
        TextView text = new TextView(context);
        text.setText("Fragment takoi fragment");
        layout.addView(text);

        return layout;
    }
}
