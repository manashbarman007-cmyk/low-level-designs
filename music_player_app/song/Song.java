package music_player_app.song;

public class Song {
    private final String name;
    private final String artist;
    private final String path;

    public Song(String name, String artist, String path) {
        this.name = name;
        this.artist = artist;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getPath() {
        return path;
    }
}
