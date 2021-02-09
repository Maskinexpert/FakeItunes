package dk.experis.fakeitunes.models;

public class Track {
    private String showcaseInfo;
    private String trackId;
    private String trackName;
    private String albumId;
    private String genreId;
    private String composer;
    private String milliseconds;

    public Track(String showcaseInfo) {
        this.showcaseInfo = showcaseInfo;
    }


    public Track(String trackId, String trackName, String albumId, String genreId, String composer, String milliseconds){
        this.trackId = trackId;
        this.trackName = trackName;
        this.albumId = albumId;
        this.genreId = genreId;
        this.composer = composer;
        this.milliseconds = milliseconds;
    }

    public String getShowcaseInfo() {
        return showcaseInfo;
    }

    public void setShowcaseInfo(String showcaseInfo) {
        this.showcaseInfo = showcaseInfo;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(String milliseconds) {
        this.milliseconds = milliseconds;
    }
}
