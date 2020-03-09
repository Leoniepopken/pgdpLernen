public class DocumentCollectionCell {

    public DocumentCollectionCell next; //nächstes Element
    public DocumentCollectionCell prev; //vorheriges Element
    private Document info;
    public double similarity;

    public DocumentCollectionCell(Document info) {
        next = null;
        prev = null;
        this.info = info;
    }

    public Document getInfo() {
        return info;
    }

    public void setInfo(Document info) {
        this.info = info;
    }
}
