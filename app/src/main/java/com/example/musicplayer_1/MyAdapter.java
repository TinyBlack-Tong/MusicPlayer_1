package com.example.musicplayer_1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends SimpleAdapter{

    Context context;

    public MyAdapter(Context context, List<?extends Map<String,?>> data, int resource, String[] from, int[] to){

        super(context,data,resource,from,to);
        this.context=context;

    }


//
//    @Override
//    public int getCount() {
//        return listItem.size();
//    }
//
//
//    @Override
//    public Object getItem(int position) {
//        return listItem.get(position);
//    }
//
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }


    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        final View v=super.getView(position,convertView,parent);
        final ImageView love=v.findViewById(R.id.love);
        final int p=position;
        Log.d("songs_item",Integer.toString(position));
//
//        love.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                love.setImageResource(R.drawable.love_solid);
//
//                Toast.makeText(context,"成功添加到歌单",Toast.LENGTH_LONG).show();
//
//
//
//            }
//        });

        return v;

    }


}

