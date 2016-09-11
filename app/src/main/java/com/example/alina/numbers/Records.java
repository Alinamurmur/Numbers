package com.example.alina.numbers;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.widget.TextView;
import android.widget.Toast;

public class Records extends Activity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        Intent intent = getIntent();
        final String nameUser = intent.getStringExtra("nameUser");
        String timeS = intent.getStringExtra("StringTime");
        String array[] = timeS.split(":");
        if (array.length == 2) {
            int min=Integer.parseInt(array[0]);
            int sec=Integer.parseInt(array[1]);}

        TextView name = (TextView)findViewById(R.id.nam);
        TextView time = (TextView)findViewById(R.id.strtime);
        name.setText(nameUser);
        time.setText(timeS);
       // mise.setText(min+":"+sec);


        try {
            SQLiteOpenHelper reco = new Helper(this);
            db = reco.getWritableDatabase();
            cursor = db.query("RECORDS",new String[]{"NAME","TIME","MIN","SEC"},
                    "_id = ?",new String[]{Integer.toString(1)},null,null,null);
            if (cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String timeText = cursor.getString(1);
                int minInt = cursor.getInt(2);
                int secInt = cursor.getInt(3);

                TextView nameTV = (TextView)findViewById(R.id.nT);
                TextView timeTV = (TextView)findViewById(R.id.timeT);
                TextView miseTV = (TextView)findViewById(R.id.minsecI);
                nameTV.setText(nameText);
                timeTV.setText(timeText);
                miseTV.setText(minInt+":"+secInt);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"Database unvailable",Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
