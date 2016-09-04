package com.example.alina.numbers;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


public class Main2Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttemp);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       // FragmentDraw fragmentDraw = FragmentDraw.getInstance();
       // fragmentTransaction.add(R.id.fragmentdraw, fragmentDraw, "draw");

        //test
        Temp fragment = new Temp();
        fragmentTransaction.add(R.id.temp,fragment);

        fragmentTransaction.commit();



       // FragmentDraw fragmentDraw = (FragmentDraw)fm.findFragmentById(R.id.circle_frag);

    }

}
