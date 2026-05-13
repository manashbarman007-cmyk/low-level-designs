package music_player_app.playlist_play_strategy;
import music_player_app.song.Song;

import java.util.List;

public class CustomPlayingStrategy extends PlayingStrategy {

    private final List<Song> songs;

    private int index;

    // user provides a custom list of songs
    public CustomPlayingStrategy(List<Song> songs) {
        this.songs = songs;
        this.index = 0;
    }

    @Override
    public Song next() {

        if (!hasNext()) {
            throw new IllegalStateException("No next song");
        }

        return songs.get(index++);
    }

    @Override
    public Song previous() {

        if (!hasPrevious()) {throw new IllegalStateException("No previous song");
        }

        return songs.get(--index);
    }

    @Override
    public boolean hasNext() {
        return index < songs.size();
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }
}
