public class DocumentCollectionCell {

    private DocumentCollectionCell next; //nächstes Element
    private DocumentCollectionCell prev; //vorheriges Element
    private Document info; //aktuelles Element
    public double similarity;

    public DocumentCollectionCell(Document info) {
        next = null;
        prev = null;
        this.info = info;
    }

    public DocumentCollectionCell getNext() {
        return next;
    }

    public void setNext(DocumentCollectionCell next) {
        this.next = next;
    }

    public DocumentCollectionCell getPrev() {
        return prev;
    }

    public void setPrev(DocumentCollectionCell prev) {
        this.prev = prev;
    }

    public Document getInfo() {
        return info;
    }

    public void setInfo(Document info) {
        this.info = info;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
}
