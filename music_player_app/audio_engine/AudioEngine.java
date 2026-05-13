package music_player_app.audio_engine;

import music_player_app.audio_output_adapter.AudioOutputDevice;
import music_player_app.song.Song;

public class AudioEngine {

    private Song currentSong;


    public void playSong (AudioOutputDevice audioOutputDevice, Song song) {
        this.currentSong = song;
        audioOutputDevice.playAudio(song);
    }

    public void pause () {
        if (currentSong != null) {
            System.out.println("Pausing : " + this.currentSong.getName());

        }
        else throw new IllegalStateException("No song played yet");
    }
}
