package music_player_app.playlist;

import music_player_app.song.Song;

import java.util.ArrayList;
import java.util.Collections;

import java.util.*;

public class Playlist {
    private String name;
    private final List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }


    public void addSong (Song song) {
        if (songs.contains(song)) {
            throw new IllegalArgumentException("Song already exists.");
        }
        songs.add(song);
    }

    public void removeSong (Song song) {
        if (songs.contains(song)) {
            songs.remove(song);
        }else throw new IllegalArgumentException("No such song.");
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return Collections.unmodifiableList(songs);
    }

    public void setName(String name) {
        this.name = name;
    }
}
