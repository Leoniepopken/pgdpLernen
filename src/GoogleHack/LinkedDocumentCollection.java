public class LinkedDocumentCollection extends DocumentCollection{

    @Override
    public void prependDocument(Document doc){
        if (doc == null || !(doc instanceof LinkedDocument)){
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

    @Override
    public void appendDocument(Document doc){
        if (doc == null || !(doc instanceof LinkedDocument)) {
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

    public void calculateIncomingLinks(){
        for (int i = 0; i < this.numDocuments(); i++) {
            //TO-DO
        }
    }

    //public LinkedDocumentCollection crawl(){

    //}

    private void crawl(LinkedDocumentCollection resultCollection){

    }
}
