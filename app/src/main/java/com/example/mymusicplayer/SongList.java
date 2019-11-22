package com.example.mymusicplayer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

import domain.Song;

public class SongList extends MainActivity {

    private ListView list;
    private final String PATH = "E:\\AndroidStudioProjects\\music_forMusicPlayer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list);
        final String[] filename = {"DP龙猪,宝楽CNBALLER,CLOUDWANG 王云 - 全部都是你", "Sunset - N.Flying", "住在你的心脏 - 黄鲲", "如果的事 - superluckyqi", "偏爱 - Lil Ghost小鬼", "分身情人 - 魏晨", "Red - Taylor Swift", "你的酒馆对我打了烊 - 陈雪凝", "Fly - GOT7", "无名之辈 - 陈雪燃"};
        adapter = new ArrayAdapter<String>(SongList.this, android.R.layout.simple_expandable_list_item_1, filename);
        list = (ListView)findViewById(R.id.addList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SongList.this, MainActivity.class);
                intent.putExtra("addSongName", filename[position]);
                startActivity(intent);
            }
        });

    }
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            final Context context = getApplicationContext();
            int readPermissionCheck = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            int writePermissionCheck = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (readPermissionCheck == PackageManager.PERMISSION_GRANTED
                    && writePermissionCheck == PackageManager.PERMISSION_GRANTED) {
                Log.v("juno", "Permission is granted");
                return true;
            } else {
                Log.v("juno", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            //permission is automatically granted on sdk<23 upon installation
            Log.v("juno", "Permission is granted");
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.v("juno","onRequestPermissionsResult requestCode ： " + requestCode
                + " Permission: " + permissions[0] + " was " + grantResults[0]
                + " Permission: " + permissions[1] + " was " + grantResults[1]
        );
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //resume tasks needing this permission
            File directory = new File(PATH);
            File[] files = directory.listFiles();
            Log.i("juno", "After PERMISSION_GRANTED files : " + (files == null ? null : files.length));
        }
    }

}
