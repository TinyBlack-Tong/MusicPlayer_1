package com.example.musicplayer_1;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class songs_item extends ListActivity {
    private TextView mTextMessage;
    private TextView song_item;;
    private RelativeLayout r1;
    private TextView filename;
    private ArrayList<HashMap<String, Object>> list;
    private ListView listView;
    private TextView song_name;
    private AssetManager assetManager;
    private String[] listFileName;
    private ImageView random;
    private ArrayList<HashMap<String,Object>> listItem;
    private ImageView play_all;
    public  TextView write_name;
    private ImageView love;
    private SimpleAdapter simpleAdapter;
    private Button go_item;
    private songs_shuzu s =new songs_shuzu();

    private ImageView xukong;//向右边代表进入我的最爱（歌单）
    private ImageView sayaren;//向左代表进入音乐播放界面



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_item);

        getId();
        getSayaren();
        getXukong();

        song_item.bringToFront();

        r1.bringToFront();
        r1.getBackground().setAlpha(100);


        initList();

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toSong_play(getRandom(s.song_mp3));

            }
        });

        play_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toSong_play(0);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case (0):
                        toSong_play(i);
                        break;
                    case (1):
                        toSong_play(i);
                        break;
                    case (2):
                        toSong_play(i);
                        break;
                    case (3):
                        toSong_play(i);
                        break;
                }


            }
        });
    }

//    进入播放界面
    private void getSayaren(){

        sayaren=findViewById(R.id.saiyaren);

        sayaren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSong_play(0);
            }
        });


    }
//

//    进入到我的歌单
    private void getXukong(){
        xukong=findViewById(R.id.xukong);
        xukong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(songs_item.this,songs_dan.class);
                startActivity(intent);

            }
        });

    }

    private void getId(){
        mTextMessage = findViewById(R.id.message);
        play_all=findViewById(R.id.play_all);
        song_item = findViewById(R.id.song_item);
        song_name = findViewById(R.id.song_name);
        write_name=findViewById(R.id.write_name);
        random=findViewById(R.id.random);
        go_item=findViewById(R.id.go_item);
        r1 = findViewById(R.id.r_1);
    }

    private void initList() {
       listView=getListView();
        ListAdapter adapter=null;
        list = new ArrayList<HashMap<String, Object>>();
        String[] song_name;
        songs_shuzu s1 = new songs_shuzu();
        for (int i = 0; i < s1.songs.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("title", s1.Music_Sons(i));
            map.put("Image", R.drawable.black);
//            map.put("love",R.drawable.love_empty);
            list.add(map);
        }

//        simpleAdapter = new SimpleAdapter(this, list, R.layout.list_item, new String[]{"title", "Image","love"},
//                new int[]{R.id.name_list, R.id.image_list,R.id.love});
        adapter=new MyAdapter(this, list, R.layout.list_item, new String[]{"title", "Image","love"},
                              new int[]{R.id.name_list, R.id.image_list});

        listView.setAdapter(adapter);

    }



//    private  void loveItem(){
//
//        list = new ArrayList<HashMap<String, Object>>();
//        String[] song_name;
//        songs_shuzu s1 = new songs_shuzu();
//        for (int i = 0; i < s1.songs.length; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("love",R.drawable.love_empty);
//            list.add(map);
//        }
//
//        simpleAdapter = new SimpleAdapter(this, list, R.layout.list_item, new String[]{"title", "Image","love"},
//                new int[]{R.id.name_list, R.id.image_list,R.id.love});
//
//    }
    private void toSong_play(int no) {
        songs_shuzu s2 = new songs_shuzu();
        Intent intent = new Intent(songs_item.this, MainActivity.class);
        intent.putExtra("music_name", s2.Music_Sons(no));
        intent.putExtra("music_id",no+"");
        startActivity(intent);
    }

private int getRandom(String[] a){

    Random random=new Random();
    int b=random.nextInt(a.length);
    return b;
}


    public void deleteItem()
    {
        int size = listItem.size();
        if( size > 0 )
        {
            listItem.remove(listItem.size() - 1);
            simpleAdapter   .notifyDataSetChanged();
        }
    }

}



