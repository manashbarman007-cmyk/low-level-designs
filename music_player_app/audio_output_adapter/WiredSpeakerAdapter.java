package music_player_app.audio_output_adapter;

import music_player_app.song.Song;
import music_player_app.third_party_apis.WiredSpeakerApi;

public class WiredSpeakerAdapter implements AudioOutputDevice{
    private final WiredSpeakerApi wiredSpeakerApi;

    public WiredSpeakerAdapter() {
        this.wiredSpeakerApi = new WiredSpeakerApi();
    }

    @Override
    public void playAudio(Song song) {
        wiredSpeakerApi.playSongViaWire(song.getName());
    }
}
