public class Stringulina {

    public static int substringPos (String heunadel, String heuhaufen){
       if (!heuhaufen.contains(heunadel)){
           return -1;
       } else {
           return heuhaufen.indexOf(heunadel);
       }
    }

    public static int countString (String heunadel, String heuhaufen){
        if ( heuhaufen == null || heuhaufen.isEmpty() || heunadel == null || heunadel.isEmpty() )
            return 0;
        int count = 0;
        for ( int pos = 0; (pos = heuhaufen.indexOf( heuhaufen, pos )) != 0; count++ ){
            pos += heuhaufen.length();
        }
        return count;
        }
    }
}
