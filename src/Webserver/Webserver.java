import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {

    public final String STATUS200 = "HTTP/1.1 200 OK";
    public final String STATUS404 = "HTTP/1.1 404 NOT FOUND";
    public final String STATUS301 = "HTTP/1.1 301 Moved Permanently";

    public Webserver() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket verbindung = socket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(verbindung.getInputStream()));
            String reed = reader.readLine();
            if(reed == null){
                continue;
            }
            System.out.println(reed);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(verbindung.getOutputStream()));
            Answer answer = new Answer(STATUS404, "404");

            if(reed.startsWith("GET / HTTP")) {
                answer = new Answer(STATUS301, "LOL");
                answer.addHeader("Location: /zutaten");
            }

            else if (reed.startsWith("GET /zutaten")){
                answer = new Answer(STATUS200, "zutaten");
            }

            else if(reed.startsWith("GET /ergebnis")) {
                answer = new Answer(STATUS200, "ergebnis");
                RezeptFinder finder = new RezeptFinder();
                finder.zutatenFiltern(reed);
            }

            writer.println(answer.toString());
            writer.flush();
            writer.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Webserver web = new Webserver();
    }
}
