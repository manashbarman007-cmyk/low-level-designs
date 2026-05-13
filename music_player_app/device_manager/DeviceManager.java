package music_player_app.device_manager;

import music_player_app.audio_output_adapter.AudioOutputDevice;
import music_player_app.audio_output_factory.AudioOutputFactory;

public class DeviceManager {
    private static volatile DeviceManager deviceManager;
    private final AudioOutputFactory audioOutputFactory;

    private DeviceManager() {
        this.audioOutputFactory = new AudioOutputFactory();
    }

    public static DeviceManager getDeviceManager() {
        if (deviceManager == null) {
            synchronized (DeviceManager.class) {
                if (deviceManager == null) {
                    deviceManager = new DeviceManager();
                }
            }
        }
        return deviceManager;
    }

    // for changing runtime behavior
    public AudioOutputDevice connect (DeviceType deviceType) {
       return audioOutputFactory.getAudioOutputDevice(deviceType);
    }
}
