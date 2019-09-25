package com.example.musicplayer_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class free_songs extends MainActivity {
    private TextView song_item;
    private RelativeLayout r1;
    //    private RelativeLayout r2;
    private ArrayList<HashMap<String, Object>> list;
    private ListView listView;
    private TextView song_name;
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing__item);
        song_item = findViewById(R.id.song_item);
        song_item.bringToFront();
        r1 = findViewById(R.id.r_1);
        r1.bringToFront();
        r1.getBackground().setAlpha(100);
        song_name = findViewById(R.id.song_name);
        getFileName g1=new getFileName();
        g1.getMusic();
    }
}

