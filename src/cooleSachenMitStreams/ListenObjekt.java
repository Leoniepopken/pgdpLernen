public class ListenObjekt {

     private String endTime;
     private  String artistName;
     private String trackName;
     private long msPlayed;

    @Override
    public String toString() {
        return "ListenObjekt{" +
                "artistName='" + artistName + '\'' +
                ", trackName='" + trackName + '\'' +
                ", msPlayed=" + msPlayed +
                '}';
    }

    public ListenObjekt(String endTime, String artistName, String trackName, long msPlayed) {
        this.endTime = endTime;
        this.artistName = artistName;
        this.trackName = trackName;
        this.msPlayed = msPlayed;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public long getMsPlayed() {
        return msPlayed;
    }

    public void setMsPlayed(long msPlayed) {
        this.msPlayed = msPlayed;
    }
}
