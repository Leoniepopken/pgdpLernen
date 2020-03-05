public class WordCountsArray {

    WordCount [] wordCountArray;
    int i = 0;

    public WordCountsArray (int initSize){
        this.wordCountArray = new WordCount[initSize];
    }

    public void add (String word, int count){
        if(i == wordCountArray.length){
            WordCount [] newArray = new WordCount[i + 1];
            for (int j = 0; j < i; j++){
                newArray[j] = wordCountArray[j];
            }
            wordCountArray = newArray;
        }
        WordCount wort = new WordCount(word, count);
        wordCountArray [i] = wort;
        i++;
    }

    public int size(){
        return i;
    }

    public String getWord(int index){
        if (index < 0 || index > i){
            return "";
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

    public static void main (String [] puh){
        WordCountsArray array = new WordCountsArray(1);
        array.add("Joscha", 3);
        array.add("Leonie", 10);
        System.out.println(array.getCount(1));
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
}
