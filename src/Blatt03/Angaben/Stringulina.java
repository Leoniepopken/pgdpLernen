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

    public static boolean correctlyBracketed(String str){
        int bracketCounterOpen = 0;
        int bracketCounterClose = 0;
        for (int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '['){
                bracketCounterOpen = bracketCounterOpen + 1;
            }
            else if (str.charAt(i) == ')' || str.charAt(i) == '}' || str.charAt(i) == ']'){
                bracketCounterClose = bracketCounterClose + 1;
            }
        }
        //folgend wird schon mal der Fall ,,ungleiche Klammernanzahl abgedeckt" :D
        if (bracketCounterOpen != bracketCounterClose){
            return false;
        }

        /*Gedanke: wenn ich eine klammerung öffne, steigert sich der Counter für open um 1 und wenn sie sich schließt,
        verringert er sich um 1. Der Counter für open darf logischerweise größer sein, als der für open, aber nie
        umgekehrt! Das muss in jedem Schleifendurchlauf geprüft werden*/

        int counterOpen = 0;
        int counterClose = 0;
        for (int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '['){
                counterOpen = counterOpen + 1;
            }
            else if (str.charAt(i) == ')' || str.charAt(i) == '}' || str.charAt(i) == ']'){
                counterClose = counterClose + 1;
                if (counterClose > counterOpen){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean matches(String str, String pattern){
        int t = 0;

        while(t < pattern.length()){
            int vielfachheitLänge = 0;
            int vielfachheit = 0;
            int zählerP = 0; //(Zähler für das Pattern)
            int zählerS = 0; //(Zähler für den String)
            //während folgende cond erfüllt ist, ist es weder ein Punkt , noch eine {
            while (pattern.charAt(zählerP) == str.charAt(zählerS)) {
                zählerP++;
                zählerS++;
            }
            if (pattern.charAt(zählerP + 1) == '.') {
                //sind folgende conds erfüllt, ist der char an der Stelle im str ein Buchstabe, also passend für einen '.'
                if (str.charAt(zählerS) != '.' && str.charAt(zählerS) != '{') {
                    return true;
                } else {
                    return false;
                }
            }
            //ist der char eine {, muss die vielfachheit herausgefunden werden
            if (pattern.charAt(zählerP) == '{') {

                vielfachheit = Integer.parseInt(pattern.substring(zählerP + 1, pattern.indexOf('}')));
                vielfachheitLänge = pattern.substring(zählerP + 1, pattern.indexOf('}')).length();

                //eine Vielfachheit, die nach einem Punkt kommt, bedeutet, dass x-beliebge Buchstaben kommen können
                //aber keine klammer und kein Punkt!
                if (str.charAt(zählerS - 1) == '.') {
                    for (int i = zählerS; i < vielfachheit; i++) {
                        if (str.charAt(i) == '.' && str.charAt(i) == '{') {
                            return false;
                        }
                    }
                }
                //wenns kein Punkt ist, sondern eben ein Buchstabe, muss der gleiche Buchstabe wiedrholt werden
                if (pattern.charAt(zählerP - 1) != '.') {
                    for (int i = zählerS; i < vielfachheit; i++) {
                        if (str.charAt(zählerS - 1) != str.charAt(i)) {
                            return false;
                        }
                    }
                    zählerP = zählerP + 2;
                }
            }
             t++;
        }

        return true;
    }

    public static void main (String [] args){
        Stringulina a = new Stringulina();
        System.out.println(matches("Haaaaaaaaaawko", "Ha{10}..o"));
    }
}
