package music_player_app.audio_output_adapter;

import music_player_app.song.Song;

public interface AudioOutputDevice {
    void playAudio(Song song);
}
