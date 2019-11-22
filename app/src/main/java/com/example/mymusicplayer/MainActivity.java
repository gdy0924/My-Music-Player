package com.example.mymusicplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import domain.Song;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView song_item;;
    private RelativeLayout r1;
    private TextView filename;
    private ArrayList<HashMap<String, Object>> list;
    private TextView song_name;
    private AssetManager assetManager;
    private String[] listFileName;
    private ArrayList<HashMap<String,Object>> listItem;
    private ImageView play_all;
    public  TextView write_name;
    private ImageView love;
    private SimpleAdapter simpleAdapter;
    private Button go_item;

    //歌曲
    Song songs = new Song();

    //添加歌曲
    private Button add;

    //随机播放
    private ImageView random;

    /*选择跳转页面按钮*/
    private Button myList;//左边代表进入我的歌单
    private Button play;//右边代表进入音乐播放界面

    private ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_show);

        random = (ImageView)findViewById(R.id.random);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = getRandom(songs.getSongs());
                String[] s = songs.getSongs();
                Log.e("随机的歌曲编号","" + number);
                Log.d("点击的歌曲名",s[number]);
                Intent intent = new Intent(MainActivity.this, Play.class);
                intent.putExtra("song_id","" + number);
                intent.putExtra("songs", songs.getSongs());
                startActivity(intent);
            }
        });

        //进入正在播放的页面
        add = (Button) findViewById(R.id.addSong);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SongList.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String add = intent.getStringExtra("addSongName");
        Log.e("add:", "" + add);
        if(add != null){
            Song newSongs = songs.addSong(intent.getStringExtra("addSongName"));
            songs = songs.addSong(add);
            String[] song = newSongs.getSongs();
            for(int i = 0 ; i < 6; i++){
                Log.e("!!!:", "" + song[i]);
            }
            Click(newSongs);
        }else{
            Click(songs);
        }
        //Click(songs);
    }

    public void Click(final Song s){
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, s.getSongs());
        listView = (ListView)findViewById(R.id.myList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Play.class);
                String i = "" + position;
                intent.putExtra("song_id", i);
                intent.putExtra("songs", s.getSongs());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder delete = new AlertDialog.Builder(MainActivity.this);
                final LayoutInflater inflater = getLayoutInflater();
                final View v1 = inflater.inflate(R.layout.delete_dialog, null);
                delete.setView(v1).setTitle("").setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(MainActivity.this, "删除",Toast.LENGTH_SHORT).show();
                        Click(s.delSong(position));
                        songs = s.delSong(position);
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                delete.show();
                return true;
            }
        });
    }

    public static int getRandom(String[] a){
        Random random = new Random();
        int b = random.nextInt(a.length);
        return b;
    }
}

