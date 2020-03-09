public class DocumentCollection {

    public DocumentCollectionCell head; //Anfang der Liste
    public DocumentCollectionCell tail; //Ende der Liste

    public DocumentCollection(){
        head = null;
        tail = null;
    }

    public void prependDocument(Document doc){
        if (doc == null) {
            return;
        }
        DocumentCollectionCell newDoc = new DocumentCollectionCell(doc);
        if (head == null){
            head = newDoc;
            tail = newDoc;
        } else {
            head.prev = newDoc;
            newDoc.next = head;
            head = newDoc;
        }
    }

    public void appendDocument(Document doc){
        if (doc == null) {
            return;
        }
        DocumentCollectionCell newDoc = new DocumentCollectionCell(doc);
        if (head == null){
            head = newDoc;
            tail = newDoc;
        } else {
            tail.next = newDoc;
            newDoc.prev = tail;
            tail = newDoc;
        }
    }

    public boolean isEmpty(){
        if(head == null && tail == null){
            return true;
        }
        return false;
    }

    public int numDocuments(){
        DocumentCollectionCell current = head;
        int anzahl = 0;
        while (current!= null){
            current = current.next;
            anzahl++;
        }
        return anzahl;
    }

    public Document getFirstDocument(){
        if (head != null) {
            return head.getInfo();
        }
        return null;
    }

    public Document getLastDocument(){
        if (tail != null) {
            return tail.getInfo();
        }
        return null;
    }

    public void removeFirstDocument(){
        // erster Fall: die Liste ist leer
        if(isEmpty()){
            return;
        }
        //zweiter Fall: Liste enthält genau ein Element
        if(head == tail){
            head = null;
            tail = null;
        }
        //dritter Fall: Liste enthält genau zwei Elemente
        assert head != null;
        if (head.next == tail){
            head = tail;
        }
        //vierter Fall: Liste entält mindestens drei Elemente
        if (!isEmpty() && head != tail && head.next != tail){
            head = head.next;
            head.prev = null;
        }
    }

    public void removeLastDocument(){
        // erster Fall: die Liste ist leer
        if(isEmpty()){
            return;
        }
        //zweiter Fall: Liste enthält genau ein Element
        if(head == tail){
            head = null;
            tail = null;
        }
        //dritter Fall: Liste enthält genau zwei Elemente
        assert head != null;
        if (head.next == tail){
            head = tail;
        }
        //vierter Fall: Liste entält mindestens drei Elemente
        if (!isEmpty() && head != tail && head.next != tail){
            tail = tail.prev;
            tail.next = null;
        }
    }

    public boolean remove (int index){
        DocumentCollectionCell temp = head;
        //Index kann nicht größer als die Liste sein!
        if(index >= numDocuments()){
            return false;
        }
        if (index == 0){
            head = head.next;
            head.prev = null;
            return true;
        }
        if (index == numDocuments()){
            tail = tail.prev;
            tail.next = null;
            return true;
        }
        else {
        int i = 0; //Zählvariable durch die Liste
        //Durch die Liste gehen, bis das vorherige Element des Elements mit dem index i gefunden wurde
        while (i < index ){
            temp = temp.next;
            i++;
        }
        //vor temp mit nach temp verknüpfen
        temp.prev.next = temp.next;
        //temp löschen
        temp.next.prev = null;
        return true;
        }
    }

    public Document get (int index){
        if (index > numDocuments() || index < 0){
            return null;
        }
        DocumentCollectionCell temp = head;
        for (int i = 0; i < index; i++){
            temp = temp.next;
        }
        return  temp.getInfo();
    }

    public int indexOf (Document doc){
        if (!contains(doc)){
            return -1;
        } else {
            int index = 0;
            DocumentCollectionCell temp = head;
            while (!temp.getInfo().equals(doc)) {
                temp = temp.next;
                index++;
            }
            return index;
        }
    }

    public boolean contains(Document doc) {
        DocumentCollectionCell temp = head;
        for (int i = 0; i < numDocuments(); i++){
            temp = temp.next;
            if (temp.getInfo().equals(doc)){
                return true;
            }
        }
        return false;
    }

    //Sind alle folgenden Methoden in dieser Klasse richtig? Oder gehören sie wo anders hin?

    private WordCountsArray allWords(){
        WordCountsArray wca = new WordCountsArray(0);
        //alle Dokumente durchgehen
        for (int i = 0; i < numDocuments(); i++){
            //Im Dokument alle Wörte durchgehne und dem array hinzufügen
            for (int j = 0; j < get(i).wordCounts.size(); j++) {
                wca.add(get(i).wordCounts.getWord(j), get(i).wordCounts.getCount(j));
            }
        }
        return wca;
    }

    private void addZeroWordsToDocuments(){
        for (int i = 0; i < numDocuments(); i++){
            for (int j = 0; j < allWords().size(); j++) {
                if (allWords().getCount(j) == 0) {
                    get(i).addContent(allWords().getWord(j));
                }
            }
        }
    }

    private void match(String searchQuery){
        //Teilaufgabe 1
        Document doc = new Document(searchQuery); //ich habe hier in Document einen zweiten Konstruktor geschrieben. Passt?
        appendDocument(doc);
        //Teilaufgabe 2
        addZeroWordsToDocuments();
        for (int i = 0; i < numDocuments(); i++) {
            get(i).wordCounts.sort();
        }
        //Teilaufgabe 3
        DocumentCollectionCell temp = head;
        for (int i = 0; i < numDocuments(); i++) {
            double similarity = get(i).wordCounts.computeSimilarity(doc.wordCounts);
            //Teilaufgabe 4
            temp.similarity = similarity;
            temp = temp.next;
        }
        //Teilaufgabe 5
        removeLastDocument();
    }

    public double getQuerySimilarity (int index){
        DocumentCollectionCell temp = head;
        int i = 0;
        while (i < index){
            temp = temp.next;
            i++;
        }
        return temp.similarity;
    }

    public void sortBySimilarityDesc(){

    }
}
