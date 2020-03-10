package Angaben;

public class IntListElement {

    private IntListElement next;
    private int info;

    public IntListElement (int info){
        this.next = null;
        this.info = info;
    }

    public IntListElement getNext() {
        return next;
    }

    public void setNext(IntListElement next) {
        this.next = next;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
}
