public class Const <T> extends Expression {

    public T konst;

    public Const (T konst){
        this.konst = konst;
    }

    @Override
    public Object evaluate() {
        return null;
    }

    @Override
    public String toString() {
        return "" + konst;
    }
}
