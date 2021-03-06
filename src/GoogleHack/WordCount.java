
public class WordCount {

    private String word;
    private int frequency;
    private double normalizedWeight;
    private double weight;

    @Override
    public String toString() {
        return word + " - " + frequency;
    }

    public WordCount(String Wort, int häufigkeit) {
        this.word = Wort;
        this.frequency = häufigkeit;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double getNormalizedWeight() {
        return normalizedWeight;
    }

    public void setNormalizedWeight(double normalizedWeight) {
        this.normalizedWeight = normalizedWeight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int incrementCount() {
        frequency += 1;
        return frequency;
    }

    public int incrementCount(int n) {
        if (n > 0) {
            frequency = frequency + n;
            return frequency;
        } else {
            return frequency;
        }
    }

    public boolean equals(WordCount wc){
        if (wc == null) {return false;};
        if(this.getWord().equals(wc.word) && this.getFrequency() == wc.getFrequency()){
            return true;
        }
        return false;
    }
}
