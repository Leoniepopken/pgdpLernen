import java.util.ArrayList;
import java.util.List;

public class Primverquerung {

    public static boolean istPrim (int n){
        if (n == 1){return false;}
        for (int i = 2; i < n; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static int quersumme(int zahl) {
        if (zahl <= 9) return zahl;
        return zahl%10 + quersumme(zahl/10);
    }

    public static int querPrim(int n){
        List primListe = new ArrayList();
        if(n < 0) { return 0; };
        for (int i = 1; i < n; i++){
            if(istPrim(i)){
                primListe.add(i);
            }
        }
        int summe = 0;
        for (int i = 0; i < primListe.size(); i++){
            int zahl = (int) primListe.get(i);
            if (quersumme(zahl) % 2 == 0){
                summe = summe + zahl;
            }
        }
        return summe;
    }
    public void test(){}

    public static void main (String [] args){
        Primverquerung p = new Primverquerung();
        System.out.println(querPrim(30));
    }
}

