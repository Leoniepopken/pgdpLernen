
public class WordCount {

    private String word;
    private int frequency;

    public WordCount (String Wort, int häufigkeit){
        this.word = Wort;
        this.frequency = häufigkeit;
    }

    public String getWord (){
        return word;
    }
    public int getFrequency(){
        return frequency;
    }
    public void setFrequency(int frequency){
        this.frequency = frequency;
    }
    public int incrementCount(){
        frequency += 1;
        return frequency;
    }
    public int incrementCount(int n){
        if (n > 0){
            frequency = frequency + n;
            return frequency;
        }
        else {
            return frequency;
        }
    }
}
