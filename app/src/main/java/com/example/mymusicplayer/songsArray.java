package com.example.mymusicplayer;

public class songsArray {
    //歌名数组
    public static String[] songs = new String[]{"WINNER (위너) - AH YEAH","WINNER (위너) - BOOM", "WINNER (위너) - MILLIONS","WINNER (위너) - MOLA","WINNER (위너) - ZOO"};
    //MP3数组
    public String[] song_mp3 = new String[]{"WINNER (위너) - AH YEAH.mp3","WINNER (위너) - BOOM.mp3", "WINNER (위너) - MILLIONS.mp3","WINNER (위너) - MOLA.mp3","WINNER (위너) - ZOO.mp3"};

    public String chooseSong(int id){
        return songs[id];
    }
}

