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
            LinkedDocument out = (LinkedDocument) get(i);
            for (int j = 0; j < this.numDocuments() ; j++) {
                LinkedDocument incomingDoc = (LinkedDocument) this.get(j);
                if (incomingDoc.getOutgoingLinks().contains(out)){
                    out.addIncomingLink(incomingDoc);
                }
            }
        }
    }

    public LinkedDocumentCollection crawl(){
       LinkedDocumentCollection resultCollection = new LinkedDocumentCollection();
       crawl(resultCollection);
       return resultCollection;
    }

    private void crawl(LinkedDocumentCollection resultCollection){
        for (int i = 0; i < this.numDocuments(); i++) {
            resultCollection.appendDocument(get(i));
        }

    }
}
