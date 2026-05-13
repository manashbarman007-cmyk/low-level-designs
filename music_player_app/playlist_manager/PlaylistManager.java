package music_player_app.playlist_manager;

import music_player_app.playlist.Playlist;
import music_player_app.song.Song;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlaylistManager {
    private final Map<String, Playlist> playlistMap;
    private static volatile PlaylistManager playlistManager;

    private PlaylistManager() {
        this.playlistMap = new ConcurrentHashMap<>();
    }

    public static PlaylistManager getPlaylistManager() {
        if (playlistManager == null) {
            synchronized (PlaylistManager.class) {
                if (playlistManager == null) {
                    playlistManager = new PlaylistManager();
                }
            }
        }
        return playlistManager;
    }

    public Playlist createPlaylist (String playlistName) {
        if (playlistMap.putIfAbsent(playlistName, new Playlist(playlistName)) != null) { // composition
            throw new IllegalArgumentException("Playlist already exists");
        }
        return playlistMap.get(playlistName);
    }

    public void addSongForPlaylist (String playlistName, Song song) {
        Playlist playlist = playlistMap.get(playlistName);
        if (playlist != null) {
            playlist.addSong(song);
        }
        else throw new IllegalArgumentException("No such playlist exists");
    }

    public void removePlaylist (String playlistName) {
        playlistMap.remove(playlistName);
    }

    public Playlist getPlaylist (String playlistName) {
        Playlist playlist = playlistMap.get(playlistName);
        if (playlist != null) return playlist;
        else throw new IllegalArgumentException("No such playlist");
    }
}
