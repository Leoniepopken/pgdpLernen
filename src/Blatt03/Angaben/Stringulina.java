import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Stringulina {

    public static int substringPos(String heunadel, String heuhaufen) {
        if (!heuhaufen.contains(heunadel)) {
            return -1;
        } else {
            return heuhaufen.indexOf(heunadel);
        }
    }

    public static int countString(String heunadel, String heuhaufen) {
        if (heuhaufen == null || heuhaufen.isEmpty() || heunadel == null || heunadel.isEmpty())
            return 0;
        int count = 0;
        for (int pos = 0; (pos = heuhaufen.indexOf(heuhaufen, pos)) != 0; count++) {
            pos += heuhaufen.length();
        }
        return count;
    }

    public static boolean correctlyBracketed(String str) {
        int bracketCounterOpen = 0;
        int bracketCounterClose = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '[') {
                bracketCounterOpen = bracketCounterOpen + 1;
            } else if (str.charAt(i) == ')' || str.charAt(i) == '}' || str.charAt(i) == ']') {
                bracketCounterClose = bracketCounterClose + 1;
                if (bracketCounterClose > bracketCounterOpen) {
                    return false;
                }
            }
        }
        return bracketCounterOpen == bracketCounterClose;
    }

    public static boolean matches(String str, String pattern) {
        int t = 0;

        while (t < pattern.length()) {
            int vielfachheitLänge = 0;
            int vielfachheit = 0;
            int zählerP = 0; //(Zähler für das Pattern)
            int zählerS = 0; //(Zähler für den String)6

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

    public boolean matcht(String str, String pattern) {
        int countS = 0;
        int countP = 0;
        while (countS < str.length() || countP < pattern.length()) {
            int m = 1;
            char wasDavorStand = pattern.charAt(countP);

            if (countP + 1 < pattern.length() && pattern.charAt(countP + 1) == '{') {
                int i = countP + 2;
                String zahl = "";
                while (pattern.charAt(i) != '}') {
                    zahl += pattern.charAt(i);
                    i++;
                }
                m = Integer.parseInt(zahl);
                countP = i;
            }

            while (m > 0) {
                if (wasDavorStand != '.' && str.charAt(countS) != wasDavorStand) {
                    return false;
                }
                m--;
                countS++;
            }
            countP++;
        }
        return true;
    }

    public static void main(String[] args) {
        Stringulina a = new Stringulina();
        System.out.println(a.matcht("Pijnguin", "P.{3}ngui{15}."));
    }
    Stringulina a;
    @Before
    public void setup (){
        a = new Stringulina();
    }

    @Test
    public void testPingu (){
        assertFalse(a.matcht("Pijnguin", "P.{3}ngui{15}."));
    }

    @Test
    public void test (){
        assertTrue(a.matcht("Pijnguin", "P.{2}ngui{1}."));
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void fehlerTest (){
        a.matcht(null, "jnfrnfh");
    }
}
