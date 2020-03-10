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
        if (isEmpty()){
            head = newDoc;
            tail = newDoc;
        } else {
            head.setPrev(newDoc);
            newDoc.setNext(head);
            head = newDoc;
        }
    }

    public void appendDocument(Document doc){
        if (doc == null) {
            return;
        }
        DocumentCollectionCell newDoc = new DocumentCollectionCell(doc);
        if (isEmpty()){
            head = newDoc;
            tail = newDoc;
        } else {
            tail.setNext(newDoc);
            newDoc.setPrev(tail);
            tail = newDoc;
        }
    }

    public boolean isEmpty(){
        return head == null && tail == null;
    }

    public int numDocuments(){
        DocumentCollectionCell current = head;
        int anzahl = 0;
        while (current!= null){
            current = current.getNext();
            anzahl++;
        }
        return anzahl;
    }

    public Document getFirstDocument(){
        if (isEmpty()) {
            return null;
        }
        return head.getInfo();
    }

    public Document getLastDocument(){
        if (isEmpty()) {
            return null;
        }
        return tail.getInfo();
    }

    public void removeFirstDocument(){
        // erster Fall: die Liste ist leer
        if(isEmpty()){
            return;
        }
        //zweiter Fall: Liste enthält genau ein Element
        if(numDocuments() == 1){
            head = null;
            tail = null;
        }
        //dritter Fall: Liste enthält genau zwei Elemente
        assert head != null;
        if (head.getNext() == tail){
            head = tail;
            head.setPrev(null);
        }
        //vierter Fall: Liste entält mindestens drei Elemente
        if (!isEmpty() && head != tail && head.getNext() != tail){ //eigentlich ja unnötig, das alles zu überprüfen, oder?
            head = head.getNext();
            head.setPrev(null);
        }
    }

    public void removeLastDocument(){
        // erster Fall: die Liste ist leer
        if(isEmpty()){
            return;
        }
        //zweiter Fall: Liste enthält genau ein Element
        if(numDocuments() == 1){
            head = null;
            tail = null;
        }
        //dritter Fall: Liste enthält genau zwei Elemente
        assert head != null;
        if (head.getNext() == tail){
            head = tail;
        }
        //vierter Fall: Liste entält mindestens drei Elemente
        if (!isEmpty() && head != tail && head.getNext() != tail){
            tail = tail.getPrev();
            tail.setNext(null);
        }
    }

    public boolean remove (int index){
        DocumentCollectionCell temp = head;
        if(index >= numDocuments()){
            return false;
        }
        if(index < 0){
            return false;
        }
        if (index == 0){
            removeFirstDocument();
            return true;
        }
        if (index == numDocuments() - 1){
            removeLastDocument();
            return true;
        }

        int i = 0;
        while (i < index ){
            temp = temp.getNext();
            i++;
        }
        temp.getPrev().setNext(temp.getNext());
        temp = null;
        return true;
    }

    public Document get (int index){
        if (index > numDocuments() || index < 0){
            return null;
        }
        DocumentCollectionCell temp = head;
        for (int i = 0; i < index; i++){
            temp = temp.getNext();
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
                temp = temp.getNext();
                index++;
            }
            return index;
        }
    }

    public boolean contains(Document doc) {
        DocumentCollectionCell temp = head;
        for (int i = 0; i < numDocuments(); i++){
            temp = temp.getNext();
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
            //Im Dokument alle Wörte durchgehen und dem array hinzufügen
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
            temp = temp.getNext();
        }
        //Teilaufgabe 5
        removeLastDocument();
    }

    public double getQuerySimilarity (int index){
        DocumentCollectionCell temp = head;
        int i = 0;
        while (i < index){
            temp = temp.getNext();
            i++;
        }
        return temp.similarity;
    }

    public void sortBySimilarityDesc(){

    }
}
