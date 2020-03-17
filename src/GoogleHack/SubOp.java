public class SubOp extends BinOp {

    @Override
    public Object evaluate() {
        int konst1 = Integer.parseInt(String.valueOf(const1));
        int konst2 = Integer.parseInt(String.valueOf(const2));
        int result = 0;
        if(konst1 < konst2){
            result = konst2 - konst1;
        } else if (konst1 > konst2){
            result = konst1 - konst2;
        } else if (konst1 == konst2){
            result = 0;
        }
        return result;
    }

    @Override
    public String toString() {
        return null;
    }
}
