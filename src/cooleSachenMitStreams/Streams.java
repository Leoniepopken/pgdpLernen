import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Streams {

    ArrayList<ListenObjekt> liste = new ArrayList();

    public Streams() {
        for (int i = 0; i < 3; i++) {

            try {
                FileReader input = new FileReader("C:\\_lpopken\\Uni\\Informatik\\pgdpLernen\\src\\cooleSachenMitStreams\\StreamingHistory" + i + ".json");
                Gson g = new Gson();
                liste.addAll(Arrays.asList(g.fromJson(input, ListenObjekt[].class)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println(liste.size() + " einträge");
    }

    public void played() {
        int seconds = liste.stream().mapToInt(o -> (int) o.getMsPlayed() / 1000).sum();
        printTime(seconds);
    }

    public String printTime (int seconds){
        int days = seconds/86400;
        seconds -= days*86400;
        int hours = (seconds/60)/60;
        seconds -= hours*3600;
        int minutes = seconds/60;
        seconds -= minutes*60;
        return "Tage: " + days + " Stunden: " + hours + " Minuten: " + minutes + " Sekunden: " + seconds;
    }

    public void favArtist(){
        Map<String, Long> map = new HashMap<>();
        liste.stream()
                .filter(a -> !a.getArtistName().equals("DAS PODCAST UFO") && !a.getArtistName().equals("Fest & Flauschig"))
                .forEach((a) -> {
            if(map.containsKey(a.getArtistName())){
                map.put(a.getArtistName(), map.get(a.getArtistName())+a.getMsPlayed());
            } else {
               map.put(a.getArtistName(), a.getMsPlayed());
            }
        });
        final Long[] currentMax = {0L};
        final String[] currentMaxArtist = new String[1];
        map.forEach((artist,time)-> {
            if(time > currentMax[0]){
                currentMax[0] = time;
                currentMaxArtist[0] = artist;
            }
        });
        System.out.println(currentMaxArtist[0] + " "+ printTime(currentMax[0].intValue() / 1000));
    }

    public void favSong(){
        Map<String, Long> map = new HashMap<>();
        liste.stream().filter(a -> !a.getTrackName().equals("Das Wichtigste heute Morgen") && !a.getTrackName().equals("UFO169 Live Show München")).forEach(a -> {
            if(map.containsKey(a.getTrackName())){
                map.put(a.getTrackName(), a.getMsPlayed() + map.get(a.getTrackName()));
            } else {
                map.put(a.getTrackName(), a.getMsPlayed());
            }
        });
        final long[] currentMax = {0};
        final String[] currentSong = new String[1];
        map.forEach((song, max) -> {
            if(max > currentMax[0]){
                currentMax[0] = max;
                currentSong[0] = song;
            }
        });
        System.out.println(currentSong[0] + " "+ printTime((int)currentMax[0] / 1000));
    }

    public static void main(String[] args) {
        Streams s = new Streams();
        s.played();
        s.favArtist();
        s.favSong();
    }
}
