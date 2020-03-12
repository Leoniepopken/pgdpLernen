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
            return;
        }
        //dritter Fall: Liste enthält genau zwei Elemente
        assert head != null;
        if (head.getNext() == tail){
            head = tail;
            head.setPrev(null);
            return;
        }
        //vierter Fall: Liste entält mindestens drei Elemente
            head = head.getNext();
            head.setPrev(null);
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
        temp.getNext().setPrev(temp.getPrev());
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

    private WordCountsArray allWords(){
        WordCountsArray wca = new WordCountsArray(0);
        //alle Dokumente durchgehen
        for (int i = 0; i < numDocuments(); i++){
            //Im Dokument alle Wörte durchgehen und dem array hinzufügen
            for (int j = 0; j < get(i).wordCounts.size(); j++) {
                wca.add(get(i).getWordCounts().getWord(j), get(i).wordCounts.getCount(j));
            }
        }
        return wca;
    }

    private void addZeroWordsToDocuments(){
        WordCountsArray allWords = allWords();
        for (int i = 0; i < numDocuments(); i++){
            for (int j = 0; j < allWords.size(); j++) {
                get(i).getWordCounts().add(allWords.getWord(j), 0);
            }
        }
    }

    private void match(String searchQuery){
        //Teilaufgabe 1
        Document doc = new Document(searchQuery);
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
            temp.setSimilarity(similarity);
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
        return temp.getSimilarity();
    }

    public void sortBySimilarityDesc(){
        DocumentCollectionCell [] array = new DocumentCollectionCell[numDocuments()];
        DocumentCollectionCell temp = head;
        for (int i = 0; i < numDocuments(); i++) {
            array[i] = temp;
            temp = temp.getNext();
        }
        mergeSortIt(array);
        head = null;
        tail = null;
        head = array[0];
        DocumentCollectionCell tem = head;
        for (int i = 1; i < array.length; i++) {
            tem.setNext(array[i]);
            tem.getNext().setPrev(tem);
            tem = tem.getNext();
        }
    }



    private static DocumentCollectionCell[] merge(DocumentCollectionCell[] a, DocumentCollectionCell[] b) {
        DocumentCollectionCell[] merged = new DocumentCollectionCell[a.length + b.length];
        int aIndex = 0;
        int bIndex = 0;
        for (int i = 0; i < merged.length; i++) {
            if (aIndex >= a.length)
                merged[i] = b[bIndex++];
            else if (bIndex >= b.length)
                merged[i] = a[aIndex++];
            else if (a[aIndex].getSimilarity() > b[bIndex].getSimilarity())
                merged[i] = a[aIndex++];
            else
                merged[i] = b[bIndex++];
        }
        return merged;
    }

    private static DocumentCollectionCell[] mergeSortIt(DocumentCollectionCell[] a) {
        // Wir teilen zunächst das Array in a.length viele 1-elementige Arrays auf
        DocumentCollectionCell[][] parts = new DocumentCollectionCell[a.length][];
        for (int i = 0; i < a.length; i++)
            parts[i] = new DocumentCollectionCell[] { a[i] };
        // Wir mergen wiederholt je zwei benachbarte Arrays, bis nur mehr ein Teil-Array
        // über bleibt.
        while (parts.length > 1) {
            DocumentCollectionCell[][] partsNew = new DocumentCollectionCell[(parts.length + 1) / 2][];
            for (int i = 0; i < partsNew.length; i++) {
                if (2 * i + 1 < parts.length)
                    partsNew[i] = merge(parts[2 * i], parts[2 * i + 1]);
                else
                    // Ist die Länge nicht durch zwei teilbar, übernehmen wir den letzten Teil-Array
                    // einfach
                    partsNew[i] = parts[2 * i];
            }
            parts = partsNew;
        }
        return parts[0];
    }

}
