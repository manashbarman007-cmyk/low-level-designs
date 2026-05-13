package music_player_app.audio_output_factory;

import music_player_app.audio_output_adapter.AudioOutputDevice;
import music_player_app.audio_output_adapter.BluetoothSpeakerAdapter;
import music_player_app.audio_output_adapter.HeadPhoneAdapter;
import music_player_app.audio_output_adapter.WiredSpeakerAdapter;
import music_player_app.device_manager.DeviceType;

public class AudioOutputFactory {
    public AudioOutputDevice getAudioOutputDevice (DeviceType deviceType) {
        switch (deviceType) {
            case BlueTooth -> {
                return new BluetoothSpeakerAdapter();
            }
            case HeadPhone -> {
                return new HeadPhoneAdapter();
            }
            case WiredSpeaker -> {
                return new WiredSpeakerAdapter();
            }
            default -> throw new IllegalArgumentException("No such device type");
        }
    }
}
