package music_player_app.playlist_play_strategy;

import music_player_app.playlist.Playlist;
import music_player_app.song.Song;

import java.util.List;

public class SequentialPlayingStrategy extends PlayingStrategy{
    private final Playlist playlist;
    private final List<Song> songs;
    private final int size;
    private int index;


    public SequentialPlayingStrategy(String playlistName) {
        super(); // call parent constructor
        this.playlist = super.getPlaylist(playlistName);
        this.songs = playlist.getSongs();
        this.size = songs.size();
        this.index = 0;
    }

    @Override
    public Song next() {
        Song song;
        if (hasNext()) {
            song = songs.get(index);
            this.index++;
            return song;
        }
        else throw new IllegalStateException("No next song present.");
    }

    @Override
    public Song previous() {
        Song song;
        if (hasPrevious()) {
            this.index--;
            song = songs.get(index);
            return song;
        }
        else throw new IllegalStateException("No previous song present.");
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }
}
