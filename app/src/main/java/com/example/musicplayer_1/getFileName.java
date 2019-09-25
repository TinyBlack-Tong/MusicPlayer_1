package com.example.musicplayer_1;

import java.io.File;

public class getFileName {


    public   static String[] getFileName(String path){

        File file =new File(path);
        String []filename=file.list();
        return filename;

    }

    public void getMusic(){

        String [] fileName=getFileName("/Users/zhangtong/Desktop/Desktop/小黑");

        for(int a=0;a<3;a++) {

            System.out.println(fileName[a]);
        }
    }

}
