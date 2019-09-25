package com.example.musicplayer_1;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.musicplayer_1.SQLite.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class songs_dan extends ListActivity {
    private ArrayList<HashMap<String, Object>> list;
    private TextView test;
    private ListView listView;
    public Bundle bundle;
    public String[] array;
    private String name;
    private Intent intent;
    private TextView go_back;
    private TextView go_songs;
    private MainActivity m=new MainActivity();
    private DataBaseHelper dataBaseHelper;
    private List<String> song_name=new ArrayList<String>();
    private Button test_sql;
//    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_dan);

        MainActivity mainActivity=new MainActivity();
        test=findViewById(R.id.test_intent);
        test_sql=findViewById(R.id.test_sql);
        test_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getName();
            }
        });

//
//        test.setText(getName());

        getName();
        goSong();
        goBack();
        initList();

    }

        private void goBack(){
        go_back=findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(songs_dan.this,songs_item.class);
                startActivity(intent);
            }
        });

        }







    private void goSong(){

        go_songs=findViewById(R.id.go_songs);
        go_songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(songs_dan.this,songs_item.class);
                startActivity(intent);

            }
        });

    }


        private void initList() {
            MainActivity m=new MainActivity();
            listView=getListView();
            ListAdapter adapter=null;
            list = new ArrayList<HashMap<String, Object>>();
            String[] song_name;
            songs_shuzu s1 = new songs_shuzu();


            for(int i=0;i<6;i++) {

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("title",getName().get(i));
                map.put("Image", R.drawable.black);
                map.put("love", R.drawable.love_solid);
                list.add(map);
            }
//
//        simpleAdapter = new SimpleAdapter(this, list, R.layout.list_item, new String[]{"title", "Image","love"},
//                new int[]{R.id.name_list, R.id.image_list,R.id.love});
            adapter=new MyAdapter(this, list, R.layout.list_item, new String[]{"title", "Image","love"},
                    new int[]{R.id.name_list, R.id.image_list,R.id.love});
            listView.setAdapter(adapter);
        }


private List<String> getName() {
    dataBaseHelper=new DataBaseHelper(this,"Songs.db",null,1);
    dataBaseHelper.getWritableDatabase();
    SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

    Cursor cursor = db.query("songs", null, null, null, null, null, null);


    if (cursor.moveToFirst()) {
        do {
            song_name.add(cursor.getString(cursor.getColumnIndex("name")));
        } while (cursor.moveToNext());
        cursor.close();
    }
    return song_name;
}

}

