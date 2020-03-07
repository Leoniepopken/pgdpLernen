public class WordCountsArray {

    WordCount [] wordCountArray;
    int i = 0;

    public WordCountsArray (int initSize){
        this.wordCountArray = new WordCount[initSize];
    }

    public void add (String word, int count){
        if (wordCountArray != null) {
            for (int j = 0; j < wordCountArray.length; j++) {
                if (wordCountArray[j].getWord().equals(word)) {
                    wordCountArray[j].setFrequency(wordCountArray[j].getFrequency() + count);
                } else {
                    if (j == wordCountArray.length) {
                        WordCount[] newArray = new WordCount[j + 1];
                        for (int k = 0; k < k; k++) {
                            newArray[k] = wordCountArray[k];
                        }
                        wordCountArray = newArray;
                    }
                    WordCount wort = new WordCount(word, count);
                    wordCountArray[j] = wort;
                    j++;
                }
            }
        }
    }

    public int size(){
        return i;
    }

    public String getWord(int index){
        if (index < 0 || index > i){
            return null;
        }
        return wordCountArray[index].getWord();
    }

    public int getCount(int index){
        if (index < 0 || index > i){
            return -1;
        }
        return wordCountArray[index].getFrequency();
    }

    public void setCount(int index, int count){
        if (count < 0){
            System.out.println("Schätzelein, die Häufigkeit muss positiv sein!");
        } else {
           wordCountArray[index].setFrequency(count);
        }
    }

    public String toString (){
        String out = "";
        for (int j = 0; j < i; j++){
            out += wordCountArray[j].toString() + "\n";
        }
        return out;
    }

    public boolean equals (WordCountsArray [] wca){
        if (wca == null) {return false;}
        if(this.wordCountArray.length != wca.length){return false;}
        for (int i = 0; i < wca.length; i++){
            if(!wordCountArray[i].equals(wca[i])){
                return false;
            }
        }
        return true;
    }

    public int getIndexOfWord(String word){
        for (int i = 0; i < wordCountArray.length; i++){
            if(wordCountArray[i].getWord().equals(word)){
                return i;
            }
        }
        return -1;
    }

    private boolean wordsEqual (WordCountsArray wca){
        for (int i = 0; i < this.wordCountArray.length; i++){
            if (this.wordCountArray[i].getWord().equals(wca.wordCountArray[i].getWord())){
                return true; //wenn alle Wörter an der Stelle i geleich sind ist die Reihenfolge trivialerweise auch richtig
            }
            else {
                return false;
            }
        }
        return false;
    }

    private double scalarProduct (WordCountsArray wca){
        double result = 0;
        if (!this.wordsEqual(wca)){
            return 0;
        }
        else {
           for (int i = 0; i < wca.wordCountArray.length; i++){
               int zwischenergebnis = this.wordCountArray[i].getFrequency()*wca.wordCountArray[i].getFrequency();
               result += zwischenergebnis;
           }
        }
        return result;
    }

    public void sort(){
        for (int i = 0; i < wordCountArray.length; i++){
            for (int j = i + 1; j < wordCountArray.length; j++){
                if (wordCountArray[j].getWord().compareTo(wordCountArray[i].getWord()) < 0){
                    WordCount speicher = wordCountArray[i];
                    wordCountArray[i] = wordCountArray[j];
                    wordCountArray[j] = speicher;
                }
            }
        }
    }

    public double computeSimilarity (WordCountsArray wca){
        wca.sort();
        this.sort();
        return scalarProduct(wca) / Math.sqrt(scalarProduct(wca) * scalarProduct(this));
    }

    public static void main (String [] puh){
        WordCountsArray array = new WordCountsArray(1);
        array.add("Joscha", 3);
        array.add("Leonie", 10);
        System.out.println(array.getCount(1));
        String a = "a";
        String b = "b";
        String c = "b";
        System.out.println(a.compareTo(b)); //-1
        System.out.println(b.compareTo(a)); //1
        System.out.println(b.compareTo(c)); //0
    }
}
