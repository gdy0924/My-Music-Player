package domain;

public class Song {
    private String[] songs;
    private String[] songs_mp3;
    public Song(){
        songs = new String[]{"WINNER (위너) - AH YEAH","WINNER (위너) - BOOM", "WINNER (위너) - MILLIONS","WINNER (위너) - MOLA","WINNER (위너) - ZOO"};
        songs_mp3 = new String[]{"WINNER (위너) - AH YEAH.mp3","WINNER (위너) - BOOM.mp3", "WINNER (위너) - MILLIONS.mp3","WINNER (위너) - MOLA.mp3","WINNER (위너) - ZOO.mp3"};
    }

    public String[] getSongs() {
        return songs;
    }

    public void setSongs(String[] songs) {
        this.songs = songs;
    }

    public Song addSong(String song){
        String[] newSongs = new String[songs.length + 1];
        for(int i = 0; i < songs.length; i++)
            newSongs[i] = songs[i];
        newSongs[songs.length] = song;
        Song s = new Song();
        s.setSongs(newSongs);
        return s;
    }

    public Song delSong(int id){
        String[] newSongs = new String[songs.length - 1];
        for(int i = 0; i < id; i++)
            newSongs[i] = songs[i];
        for(int i = id + 1; i < songs.length; i++)
            newSongs[i - 1] = songs[i];
        Song s = new Song();
        s.setSongs(newSongs);
        return s;
    }
}
