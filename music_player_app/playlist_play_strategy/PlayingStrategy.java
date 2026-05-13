package music_player_app.playlist_play_strategy;

import music_player_app.playlist.Playlist;
import music_player_app.playlist_manager.PlaylistManager;
import music_player_app.song.Song;

public abstract class PlayingStrategy {

    final PlaylistManager playlistManager;

    public PlayingStrategy() {
        this.playlistManager = PlaylistManager.getPlaylistManager();
    }

    public Playlist getPlaylist(String playlistName) {
        return playlistManager.getPlaylist(playlistName);
    }


    public abstract Song next();

    public abstract Song previous();

    public abstract boolean hasNext();

    public abstract boolean hasPrevious();
}
