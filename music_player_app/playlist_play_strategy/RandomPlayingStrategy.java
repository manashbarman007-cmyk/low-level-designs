package music_player_app.playlist_play_strategy;

import music_player_app.playlist.Playlist;
import music_player_app.song.Song;

import java.util.List;
import java.util.Random;

public class RandomPlayingStrategy extends PlayingStrategy{

    private final Playlist playlist;
    private final List<Song> songs;
    private final int size;
    private final Random random;
    private int index;
    private Integer previouslyPlayedIndex;


    public RandomPlayingStrategy(String playlistName) {
        super(); // call parent constructor
        this.playlist = super.getPlaylist(playlistName);
        this.songs = playlist.getSongs();
        this.size = songs.size();
        this.random = new Random();
        this.index = -1;
        this.previouslyPlayedIndex = null;
    }
    @Override
    public Song next() {
        if (index != -1) {
            previouslyPlayedIndex = index;
        }
        if (songs.isEmpty()) {
            throw new IllegalStateException("Empty playlist");
        }
        index = random.nextInt(0, size); // size is exclusive
        return songs.get(index);
    }

    @Override
    public Song previous() {
        if (hasPrevious()) {
            return songs.get(previouslyPlayedIndex);
        }
        else throw new IllegalStateException("No previous song exists");
    }

    @Override
    public boolean hasNext() {
        return true; // dummy value
    }

    @Override
    public boolean hasPrevious() {
        return previouslyPlayedIndex != null;
    }
}
