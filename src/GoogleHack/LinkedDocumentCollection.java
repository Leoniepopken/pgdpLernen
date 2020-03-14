public class LinkedDocumentCollection extends DocumentCollection{

    @Override
    public void prependDocument(Document doc){
        if (!(doc instanceof LinkedDocument) || contains(doc)){
            return;
        }
        super.prependDocument(doc);
    }

    @Override
    public void appendDocument(Document doc){
        if (!(doc instanceof LinkedDocument) || contains(doc)) {
            return;
        }
        super.appendDocument(doc);
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
