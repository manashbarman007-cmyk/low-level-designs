package music_player_app.audio_output_adapter;

import music_player_app.song.Song;
import music_player_app.third_party_apis.HeadPhoneApi;

public class HeadPhoneAdapter implements AudioOutputDevice{

    private final HeadPhoneApi headPhoneApi;

    public HeadPhoneAdapter() {
        this.headPhoneApi = new HeadPhoneApi();
    }

    @Override
    public void playAudio(Song song) {
        headPhoneApi.playSongViaHP(song.getName());
    }
}
