package music_player_app.audio_output_adapter;

import music_player_app.song.Song;
import music_player_app.third_party_apis.BlueToothSpeakerApi;

public class BluetoothSpeakerAdapter implements AudioOutputDevice{
    private final BlueToothSpeakerApi blueToothSpeakerApi;

    public BluetoothSpeakerAdapter() {
        this.blueToothSpeakerApi = new BlueToothSpeakerApi();
    }

    @Override
    public void playAudio(Song song) {
        blueToothSpeakerApi.playSongViaBT(song.getName());
    }
}
