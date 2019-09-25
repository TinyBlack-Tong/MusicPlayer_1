package com.example.musicplayer_1.SQLite.testSql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.musicplayer_1.R;
import com.example.musicplayer_1.SQLite.DataBaseHelper;

import java.util.PropertyResourceBundle;

public class TestSql extends AppCompatActivity {

    private Button create;
    private Button add;
    private Button check;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sql);
        create=findViewById(R.id.create);
        add=findViewById(R.id.add);
        check=findViewById(R.id.check);



        dataBaseHelper=new DataBaseHelper(this,"Songs.db",null,1);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dataBaseHelper.getWritableDatabase();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dataBaseHelper.getReadableDatabase();
                ContentValues values=new ContentValues();
//

                values.put("name","Justin Bieber-Love Yourself.mp3");
                db.insert("songs",null,values);
                values.clear();
                values.put("name","Kygo,Ed Sheeran - I See Fire (Kygo Remix).mp3");
                db.insert("songs",null,values);
                values.clear();
                values.put("name","Taylor Swift-Clean.mp3");
                db.insert("songs",null,values);
                values.clear();
                values.put("name","Taylor Swift-Style.mp3");
                db.insert("songs",null,values);
                values.clear();
                values.put("name","逃跑计划-夜空中最亮的星.mp3");
                db.insert("songs",null,values);
                values.clear();
            }
        });


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dataBaseHelper.getWritableDatabase();

                Cursor cursor=db.query("songs",null,null,null,null,null,null);


                if (cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        Log.d("TestSql",name);
                    }while (cursor.moveToNext());
                    cursor.close();
                }
            }
        });
    }
}
