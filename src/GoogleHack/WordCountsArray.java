public class WordCountsArray {

    private WordCount[] wordCountArray;
    int anzahlElemente = 0;

    public WordCountsArray(int initSize) {
        this.wordCountArray = new WordCount[initSize];
    }

    public void add(String word, int count) {
        if (wordCountArray.length == anzahlElemente) {
            WordCount[] newArray = new WordCount[anzahlElemente * 2];
            for (int i = 0; i < wordCountArray.length; i++) {
                newArray[i] = wordCountArray[i];
            }
            wordCountArray = newArray;
        }

        if (wordCountArray != null) {
            for (int j = 0; j < size(); j++) {
                if (wordCountArray[j].getWord().equals(word)) {
                    wordCountArray[j].setFrequency(wordCountArray[j].getFrequency() + count);
                    return;
                }
            }
            wordCountArray[anzahlElemente] = new WordCount(word, count);
            anzahlElemente++;
        }
    }

    public int size() {
        return anzahlElemente;
    }

    public String getWord(int index) {
        if (index < 0 || index > anzahlElemente) {
            return null;
        }
        return wordCountArray[index].getWord();
    }

    public int getCount(int index) {
        if (index < 0 || index > anzahlElemente) {
            return -1;
        }
        return wordCountArray[index].getFrequency();
    }

    public void setCount(int index, int count) {
        if (count < 0) {
            System.out.println("Schätzelein, die Häufigkeit muss positiv sein!");
        } else {
            wordCountArray[index].setFrequency(count);
        }
    }

    public String toString() {
        String out = "";
        for (int j = 0; j < anzahlElemente; j++) {
            out += wordCountArray[j].toString() + "\n";
        }
        return out;
    }

    public boolean equals(WordCountsArray[] wca) {
        if (wca == null) {
            return false;
        }
        if (this.wordCountArray.length != wca.length) {
            return false;
        }
        for (int i = 0; i < wca.length; i++) {
            if (!wordCountArray[i].equals(wca[i])) {
                return false;
            }
        }
        return true;
    }

    public int getIndexOfWord(String word) {
        for (int i = 0; i < size(); i++) {
            if (wordCountArray[i].getWord().equals(word)) {
                return i;
            }
        }
        return -1;
    }

    private boolean wordsEqual(WordCountsArray wca) {
        if (this.size() != wca.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!this.getWord(i).equals(wca.getWord(i))) {
                return false;
            }
        }
        return true;
    }

    private double scalarProduct(WordCountsArray wca) {
        double result = 0;
        if (!this.wordsEqual(wca)) {
            return 0;
        } else {
            for (int i = 0; i < wca.wordCountArray.length; i++) {
                int zwischenergebnis = this.getCount(i) * wca.getCount(i);
                result += zwischenergebnis;
            }
        }
        return result;
    }

    public void sort() {
        for (int i = 0; i < wordCountArray.length; i++) {
            for (int j = i + 1; j < wordCountArray.length; j++) {
                if (wordCountArray[j].getWord().compareTo(wordCountArray[i].getWord()) < 0) {
                    WordCount speicher = wordCountArray[i];
                    wordCountArray[i] = wordCountArray[j];
                    wordCountArray[j] = speicher;
                }
            }
        }
    }

    public double computeSimilarity(WordCountsArray wca) {
        wca.sort();
        this.sort();
        if(!wordsEqual(wca)){
            return -1;
        }
        return scalarProduct(wca) / Math.sqrt(wca.scalarProduct(wca) * scalarProduct(this));
    }

    public static void main(String[] puh) {
        WordCountsArray array = new WordCountsArray(1);
        array.add("Joscha", 3);
        array.add("Leonie", 10);
        array.add("Andi", 5);
        array.add("Zara", 8);
        array.sort();
        System.out.println(array.getCount(1));
        String a = "a";
        String b = "b";
        String c = "b";
        System.out.println(a.compareTo(b)); //-1
        System.out.println(b.compareTo(a)); //1
        System.out.println(b.compareTo(c)); //0
    }
}
