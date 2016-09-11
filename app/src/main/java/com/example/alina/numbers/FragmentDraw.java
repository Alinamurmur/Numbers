package com.example.alina.numbers;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class FragmentDraw extends Fragment {
    private Chronometer chronometer;
    Button stop,pause;
    AlertDialog.Builder ald,paus;
    long timeWhenStopped = 0;
    int min =0,sec=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_circle, container, false);
        final Intent intentR = new Intent(getActivity(),Records.class);

        chronometer = (Chronometer) rootView.findViewById(R.id.chronometer);
        chronometer.start();

        paus = new AlertDialog.Builder(getActivity());
        paus.setMessage("Игра приостановлена");
        paus.setCancelable(false);
        paus.setPositiveButton("Продолжить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
            }
        });
        pause = (Button)rootView.findViewById(R.id.button5);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
                paus.show();
            }
        });

        ald = new AlertDialog.Builder(getActivity());
        ald.setMessage(R.string.message);
        ald.setPositiveButton(R.string.menu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);}});

        ald.setNeutralButton(R.string.b1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentDraw fragment = new FragmentDraw();
                fragmentTransaction.add(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                timeWhenStopped = 0;}});

        ald.setNegativeButton(R.string.b2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intentR);}});
        ald.setCancelable(true);
        ald.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(getActivity(),"Вы ничего не выбрали :(",Toast.LENGTH_LONG).show();}});

        stop = (Button) rootView.findViewById(R.id.button4);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                String chronoText = chronometer.getText().toString();
                intentR.putExtra("StringTime",chronoText);
                ald.show();
            }
        });

        RelativeLayout relativeLayout = (RelativeLayout)rootView.findViewById(R.id.cir);
        relativeLayout.addView(new Circle(getActivity()));
        return rootView;
    }
}
