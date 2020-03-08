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
}
