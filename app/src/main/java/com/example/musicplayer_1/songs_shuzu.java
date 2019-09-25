package com.example.musicplayer_1;

import java.io.StringReader;
import java.util.ArrayList;

public class songs_shuzu {

    public String[] songs = new String[]{"Justin Bieber-Love Yourself","Kygo,Ed Sheeran - I See Fire (Kygo Remix)", "Taylor Swift-Clean","Taylor Swift-Style","逃跑计划-夜空中最亮的星"};

    public String[] song_mp3 = new String[]{"Justin Bieber-Love Yourself.mp3","Kygo,Ed Sheeran - I See Fire (Kygo Remix).mp3","Taylor Swift-Clean.mp3","Taylor Swift-Style.mp3","逃跑计划-夜空中最亮的星.mp3"};

    public String Music_Sons(int b) {

      return songs[b];

    }


    public  ArrayList<String> mMusicList =new ArrayList<>();
}
