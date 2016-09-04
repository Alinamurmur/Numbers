package com.example.alina.numbers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

//Button but1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button but1 = (Button)findViewById(R.id.b1);
        Button but2 = (Button)findViewById(R.id.b2);
        Button but3 = (Button)findViewById(R.id.b3);

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Records.class);
                startActivity(intent);
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Records.class);
                startActivity(intent);
            }
        });



    }

}
