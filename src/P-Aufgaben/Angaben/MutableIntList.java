package Angaben;

public class MutableIntList {

    private IntListElement head;
    private IntListElement tail;

    public void append (int info){
        if (size() == 0){
            head.setInfo(info);
            tail.setInfo(info);
        } else {
            IntListElement newElement = new IntListElement(info);
            tail.setNext(newElement);
            tail = newElement;
        }
    }

    public int size(){
        IntListElement temp = head;
        int size = 0;
        while (temp.getNext() != tail){
            temp = temp.getNext();
            size++;
        }
        return size;
    }

    public void remove(int pos){
        IntListElement temp = head;
        int index = 0;
        while(index < pos - 1){
            temp = temp.getNext();
            index++;
        }
        temp.setNext(temp.getNext().getNext());
        temp.setNext(null);
    }

    public String toString (){
        IntListElement temp = head;
        String s = "";
        while (temp != tail){
            s = s + ", " + temp.getInfo();
            temp = temp.getNext();
        }
        return s;
    }

    public int sum (){
        IntListElement temp = head;
        int sum = 0;
        while (temp != tail){
            sum += temp.getInfo();
            temp = temp.getNext();
        }
        return sum;
    }
}
