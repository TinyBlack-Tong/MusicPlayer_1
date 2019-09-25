package com.example.musicplayer_1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG="myTag";
    private ImageView b_start;//播放按钮
    private ImageView b_last;//上一首
    private ImageView b_next;//下一首
    public MediaPlayer mediaPlayer;
    static SeekBar seekBar;
    private String[] a;//歌曲名单 用数组的方式存储 路径名
    private int b=0;//用b来控制数组a
    private ImageView disk;//碟片
    private ImageView needle;
    private TextView songs_name;
    private ObjectAnimator objectAnimator;
    private ObjectAnimator objectAnimator_needle;
    private TextView time_left;//进度条左边的当前歌曲播放时间
    private TextView time_right;//进度条右边的歌曲总时长
    private boolean flag=false;
    private int m_id;
    private ImageView back;
    private ImageView add_item;
    private TextView song_name;
    public String[] SongName;
    public songs_shuzu music =new songs_shuzu();
    public ArrayList<String> M;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            mediaPlayer=new MediaPlayer();
            b_start=findViewById(R.id.b_start);
            b_last=findViewById(R.id.b_last);
            b_next=findViewById(R.id.b_next);
            disk=findViewById(R.id.disk);
            needle=findViewById(R.id.needle);
            time_left=findViewById(R.id.time_left);
            back=findViewById(R.id.back);
            seekBar=findViewById(R.id.seek_bar);
            songs_name=findViewById(R.id.song_name);
            needle.bringToFront();

            final Intent intent = getIntent();
            final String data=intent.getStringExtra("music_name");
            final String id=getIntent().getStringExtra("music_id");
            m_id=Integer.parseInt(id);

            songs_name.setText(data);
            final songs_shuzu s3=new songs_shuzu();
            a=s3.song_mp3;
            back();


                b_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(flag==false) {
                            b_start.setImageDrawable(getResources().getDrawable(R.drawable.button_pause));
                            handler.sendEmptyMessage(1);
                            musicPlay(a, m_id);
                            diskMove();
                            needleMove();
                            mediaPlayer.start();

                            Log.d("MainActivity", String.valueOf(flag));

                            flag = true;

                        }else{

                            mediaPlayer.pause();
                            flag=false;

                        }


                    }
                });





                b_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        musicPlay(a,m_id);
                        m_id++;

                        if(m_id>s3.song_mp3.length-1){
                           b_next.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   Toast.makeText(MainActivity.this,"已经到最后一首歌了😯",Toast.LENGTH_LONG).show();
                               }
                           });
                        }
                    }

                });




                b_last.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        musicPlay(a,m_id);
                        m_id--;
                        if(m_id<0){
                            b_last.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(MainActivity.this,"没有上一首歌了大兄弟",Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    }
                });

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//


                            int progress=seekBar.getProgress();
                            if(mediaPlayer!=null&&mediaPlayer.isPlaying()){

                                mediaPlayer.seekTo(progress);

                            }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                //添加到歌单按钮
                add_item=findViewById(R.id.add_item);
                add_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        songs_shuzu music =new songs_shuzu();
//
//                        M=music.mMusicList;
//                        M.add(data);
//                        System.out.println(data);
                        Intent intent1=new Intent(MainActivity.this,songs_dan.class);
                        intent1.putExtra("name",data);
                        startActivity(intent1);

//                       M.add(data);
//                        Toast.makeText(MainActivity.this, , Toast.LENGTH_LONG).show();
                    }
                });



    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            seekBar.setMax(mediaPlayer.getDuration());
            time_left.setText(getMusicTime_Current());

        }
    };


    public void back(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,songs_item.class);
                startActivity(intent);

            }
        });


    }
    private String formatTime(int length){
        Date date=new Date(length);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        String TotalTime=simpleDateFormat.format(date);
        return  TotalTime;

    }

    class SeekBarThread implements Runnable{

        @Override
        public void run() {
            while (mediaPlayer!=null){
                handler.sendEmptyMessage(mediaPlayer.getCurrentPosition());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public void musicPlay(String a[],int b){
        song_name=findViewById(R.id.song_name);
        mediaPlayer.reset();
        AssetManager assetManager = getAssets();
        try {
            AssetFileDescriptor assetFileDescriptor=assetManager.openFd(a[b]);
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            getMusicTime_Total();

            mediaPlayer.start();
            song_name.setText(a[b]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new SeekBarThread()).start();

    }
    private void diskMove(){

        objectAnimator= ObjectAnimator.ofFloat(disk,"rotation",0f,360.f);
        objectAnimator.setDuration(10000);
        objectAnimator.setInterpolator(new LinearInterpolator());//
        objectAnimator.setRepeatCount(-1);//设置动画重复次数（-1代表一直转）
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);//动画重复模式
        objectAnimator.start();



    }

    private void needleMove(){

        objectAnimator_needle=ObjectAnimator.ofFloat(needle,"translationY",0f,60f,0);
        objectAnimator_needle.setDuration(3000);
        objectAnimator_needle.start();
    }
    private String getMusicTime_Current(){
        time_left=findViewById(R.id.time_left);
        //当前歌曲的播放时间
        int time_now =mediaPlayer.getCurrentPosition()/1000;
        String time_now_show=time_now/60+":"+time_now%60;

        return time_now_show;

    }
    private void getMusicTime_Total(){

        time_right=findViewById(R.id.time_right);
        //歌曲总的播放时间
        int time_total=mediaPlayer.getDuration()/1000;
        String time_total_show=time_total/60+":"+time_total%60;
        time_right.setText(time_total_show);
    }



}
