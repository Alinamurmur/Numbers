package com.example.alina.numbers;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class RecHelp extends Activity {
    private SQLiteDatabase db;
    private Cursor cursor;
    int minBase,secBase,minUser,secUser;



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
            Intent intent = getIntent();
            final String nameUser = intent.getStringExtra("nameUser");
            String timeUser = intent.getStringExtra("StringTime");
            String array[] = timeUser.split(":");
            minUser = Integer.parseInt(array[0]);
            secUser = Integer.parseInt(array[1]);


            boolean equ = false;
            cursor = db.query("RECORDS", new String[]{"NAME", "TIME"},
                    null, null, null, null, null);
            int kolvo = cursor.getCount();
            if (kolvo > 0) {
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    String nameFromBase = cursor.getString(0);//записываем имя с каждой строки
                    if (nameFromBase.equals(nameUser)) { //если совпадает, то проверяем время и выходим из цикла)
                        equ = true;
                        String timeFromBase = cursor.getString(1);
                        String arrayB[] = timeFromBase.split(":");
                        minBase = Integer.parseInt(arrayB[0]);
                        secBase = Integer.parseInt(arrayB[1]);
                        if (minUser < minBase) { //Сравниваем время
                            Helper.updateTable(db, nameFromBase, timeUser);
                        } else if (minUser == minBase) {
                            if (secUser < secBase) {
                                Helper.updateTable(db, nameFromBase, timeUser);
                            }
                        }
                        break;
                    }
                }
                if (equ == false) { //если совпадений не нашли записываем
                    Helper.insertTime(db, nameUser, timeUser);
                }
            } else { //если записей вообще не было
                Helper.insertTime(db, nameUser, timeUser);
            }
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Mi ne smogli proverit", Toast.LENGTH_SHORT);
            toast.show();
        }

        try {
            cursor = db.query("RECORDS",new String[]{"NAME","TIME"},null,null,null,null,null);
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
                tableLayout.addView(row);
            }
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"Что-то пошло не так",Toast.LENGTH_SHORT);
            toast.show();
        }
        cursor.close();
        db.close();
    }
}
