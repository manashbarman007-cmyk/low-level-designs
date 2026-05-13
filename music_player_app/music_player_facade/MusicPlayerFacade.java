package music_player_app.music_player_facade;

import music_player_app.audio_engine.AudioEngine;
import music_player_app.audio_output_adapter.AudioOutputDevice;
import music_player_app.device_manager.DeviceManager;
import music_player_app.device_manager.DeviceType;
import music_player_app.playing_strategy_manager.PlayingStrategyManager;
import music_player_app.playing_strategy_manager.StrategyType;
import music_player_app.playlist_play_strategy.PlayingStrategy;
import music_player_app.playlist_play_strategy.RandomPlayingStrategy;
import music_player_app.song.Song;
import java.util.List;

public class MusicPlayerFacade {
    private final DeviceManager deviceManager;
    private final AudioEngine audioEngine;
    private DeviceType deviceType;
    private final PlayingStrategyManager playingStrategyManager;
    private AudioOutputDevice currentDevice;
    private PlayingStrategy currentStrategy;

    public MusicPlayerFacade(DeviceType deviceType, StrategyType strategyType, String playlistName) {
        this.deviceType = deviceType;
        this.deviceManager = DeviceManager.getDeviceManager();
        this.audioEngine = new AudioEngine();
        this.playingStrategyManager = PlayingStrategyManager.getPlayingStrategyManager();
        this.currentStrategy = playingStrategyManager.getStrategy(strategyType, playlistName);
    }

    public MusicPlayerFacade(DeviceType deviceType, StrategyType strategyType, List<Song> customSongs) {
        this.deviceType = deviceType;
        this.deviceManager = DeviceManager.getDeviceManager();
        this.audioEngine = new AudioEngine();
        this.playingStrategyManager = PlayingStrategyManager.getPlayingStrategyManager();
        this.currentStrategy = playingStrategyManager.getStrategy(strategyType, customSongs);
    }

    public void play (Song song) {
        if (currentDevice == null) {
            currentDevice = connectDevice();
        }
        audioEngine.playSong(currentDevice, song);
    }

    public void pause () {
        audioEngine.pause();
    }

    // changing device dynamically
    public AudioOutputDevice connectDevice () {
        return deviceManager.connect(deviceType);
    }

    public void playAll () {

        if (currentStrategy instanceof RandomPlayingStrategy) {
            throw new IllegalStateException("Use playNext() or playPrev() for random songs.");
        }

        while (currentStrategy.hasNext()) {
            Song song = currentStrategy.next();
            play(song);
        }

    }

    public void playNext () {
        if (currentStrategy.hasNext()) {
            Song song = currentStrategy.next();
            play(song);
        }
    }
    public void playPrev () {
        if (currentStrategy.hasPrevious()) {
            Song song = currentStrategy.previous();
            play(song);
        }
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        if (this.deviceType != deviceType) {
            this.deviceType = deviceType;
            this.currentDevice = connectDevice();
        }
    }

    // dynamic change (for custom playing strategy we do method overloading)
    public void setPlayingStrategy (StrategyType strategyType, List<Song> customSongs) {
       this.currentStrategy = playingStrategyManager.getStrategy(strategyType, customSongs);
    }


    // dynamic change
    public void setPlayingStrategy (StrategyType strategyType, String playlistName) {
       this.currentStrategy = playingStrategyManager.getStrategy(strategyType, playlistName);
    }
}
