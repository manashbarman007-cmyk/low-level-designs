package music_player_app.playing_strategy_manager;

import music_player_app.playlist_play_strategy.CustomPlayingStrategy;
import music_player_app.playlist_play_strategy.PlayingStrategy;
import music_player_app.playlist_play_strategy.RandomPlayingStrategy;
import music_player_app.playlist_play_strategy.SequentialPlayingStrategy;
import music_player_app.song.Song;

import java.util.List;

public class PlayingStrategyManager {

    private static volatile PlayingStrategyManager playingStrategyManager;

    private PlayingStrategyManager() {}

    public static PlayingStrategyManager getPlayingStrategyManager() {
        if (playingStrategyManager == null) {
            synchronized (PlayingStrategyManager.class) {
                if (playingStrategyManager == null) {
                    playingStrategyManager = new PlayingStrategyManager();
                }
            }
        }
        return playingStrategyManager;
    }

    public PlayingStrategy getStrategy(StrategyType strategyType, String playlistName) {
        switch (strategyType) {
            case SEQUENTIAL -> {
                return new SequentialPlayingStrategy(playlistName);
            }
            case RANDOM -> {
                return new RandomPlayingStrategy(playlistName);
            }
            default -> throw new IllegalArgumentException("Strategy not supported");
        }
    }

    // method overloading for custom play strategy
    public PlayingStrategy getStrategy(StrategyType strategyType, List<Song> customSongs) {
        if (strategyType == StrategyType.RANDOM || strategyType == StrategyType.SEQUENTIAL) {
            throw new IllegalArgumentException("Strategy not supported");
        }
        return new CustomPlayingStrategy(customSongs);
    }
}
