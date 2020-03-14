import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Answer {

    String header;
    String body;

    public Answer(String header, String body) {
        this.header = header;
        try {
            setBody(body);
        } catch (IOException e){
            setHeader("HTTP/1.1 500 internal Server Error");
            this.body = "Fehler";
        }

    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setBody(String body) throws IOException {
        this.body = Files.lines(Path.of("C:\\_lpopken\\Uni\\Informatik\\pgdpLernen\\pgdpLernen\\src\\Webserver\\HTML\\" + body + ".html"))
                .collect(Collectors.joining());

    }

    public String toString (){
        return header + "\n" + "\n" + body;
    }

    public void addHeader (String header){
        this.header += "\n" + header;
    }
}
