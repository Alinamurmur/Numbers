package com.example.alina.numbers;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Records extends Activity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        TableLayout tableLayout = (TableLayout)findViewById(R.id.tableR);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);

        SQLiteOpenHelper dbHelper = new Helper(this);
        db = dbHelper.getWritableDatabase();
        try {
            cursor = db.query("RECORDS",new String[]{"NAME", "TIME"},
                    null,null,null,null,null);
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                TableRow row = new TableRow(this);
                TextView nameView = new TextView(this);
                nameView.setText(cursor.getString(0));
                nameView.setTypeface(Typeface.DEFAULT_BOLD);
                nameView.setGravity(Gravity.CENTER_HORIZONTAL);
                nameView.setTextSize(20);
                row.addView(nameView);
                TextView timeView = new TextView(this);
                timeView.setText(cursor.getString(1));
                timeView.setGravity(Gravity.CENTER_HORIZONTAL);
                timeView.setTextSize(20);
                row.addView(timeView);
                row.setBackgroundColor(Color.GREEN);
                tableLayout.addView(row);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Ой как нехорошо вышло то", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
