package music_player_app.orchestrator;

import music_player_app.device_manager.DeviceType;
import music_player_app.music_player_facade.MusicPlayerFacade;
import music_player_app.playing_strategy_manager.StrategyType;
import music_player_app.playlist.Playlist;
import music_player_app.playlist_manager.PlaylistManager;
import music_player_app.song.Song;
import java.util.List;

public class MusicPlayerApp {
    private final PlaylistManager playlistManager;
    private final MusicPlayerFacade musicPlayerFacade;

    public MusicPlayerApp(DeviceType deviceType, List<Song> customSongs) {
        this.playlistManager = PlaylistManager.getPlaylistManager();
        this.musicPlayerFacade = new MusicPlayerFacade(deviceType, StrategyType.CUSTOM, customSongs);
    }

    public MusicPlayerApp(DeviceType deviceType, StrategyType strategyType, String playlistName) {
        if (strategyType == StrategyType.CUSTOM) {
            throw new IllegalArgumentException("Use custom constructor for CUSTOM strategy");
        }
        this.playlistManager = PlaylistManager.getPlaylistManager();
        this.musicPlayerFacade = new MusicPlayerFacade(deviceType, strategyType, playlistName);
    }

    public Playlist createPlaylist (String playlistName) {
        return playlistManager.createPlaylist(playlistName);
    }

    public void removePlaylist (String playlistName) {
        playlistManager.removePlaylist(playlistName);
    }

    public Playlist getPlaylist (String playlistName) {
        return playlistManager.getPlaylist(playlistName);
    }

    public void addSongsForPlaylist (String playlistName, Song song) {
        playlistManager.addSongForPlaylist(playlistName, song);
    }

    public void playAll () {
       musicPlayerFacade.playAll();
    }

    public void playNext () {
        musicPlayerFacade.playNext();
    }
    public void playPrev () {
        musicPlayerFacade.playPrev();
    }
    public void pause () {
        musicPlayerFacade.pause();
    }

    public void setDeviceType(DeviceType deviceType) {
        musicPlayerFacade.setDeviceType(deviceType);
    }
}
