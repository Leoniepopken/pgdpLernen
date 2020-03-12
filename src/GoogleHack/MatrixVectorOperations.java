import java.util.Arrays;

public class MatrixVectorOperations {

    public static double[] multiply (double[][] matrix, double[] vektor){
        double [] result = new double[vektor.length];
        //int zeilen = matrix.length;
        int spalten = matrix[1].length;
        int r = 0; //für result
        int z = 0; //fürs weiterrücken in die nächste Zeile
        while(r < result.length){
            //Spalten durchlauf:
            double zwischenErgebnis = 0;
            for (int j = 0; j < spalten; j++){
                zwischenErgebnis = matrix[z][j] * vektor[j];
                result[r] += zwischenErgebnis;
            }
            r++; //ein feld weiter in result
            z++; //eine zeile weiter nach unten in der Matrix
        }
        return result;
    }

    public static double skalarRechnung(double [] a, double [] b){
            double result = 0;
            for (int i = 0; i < a.length; i++) {
                double zwischenergebnis = a[i] * b[i];
                result += zwischenergebnis;
            }
        return result;
    }

    public static double cosineSimilarity(double [] a, double [] b){
        double skalarproduktAB = skalarRechnung(a, b);
        double skalarproduktAA = skalarRechnung(a, a);
        double skalarproduktBB = skalarRechnung(b, b);
        return skalarproduktAB / Math.sqrt(skalarproduktAA*skalarproduktBB);
    }

    public static double [][] transpose (double [][] matrix){
        double [][] result = new double[matrix.length][];
        //Zeilendurchlauf des result: zeile für zeile die alte spalte hinzufügen
        for (int i = 0; i < result.length; i++){
            //herausfiltern der i-ten spalte der alten matrix:
            double [] spalte = new double[matrix[0].length];
            //Durchlauf: spalten der matrix
            for (int j = 0; j < matrix[0].length; j++){
                int k = 0;
                //Durlauf: Zeilen in der Spalte j klappt
                while (k < matrix.length) {
                    spalte[k] = matrix[k][j];
                    k++;
                }
                for (int a = 0; a < matrix.length; a++){
                    result[i][a] = spalte[a];
                }
            }
        }
        return result;
    }

    /**
     * @param joscha joschas standort
     * @param leonie leonies standort
     * @return
     */
    public static double euclideanDistance (double [] joscha, double [] leonie){
        double [] differenzDerOrte = new double[joscha.length];
        for (int i = 0; i < joscha.length; i++){
            differenzDerOrte [i] = joscha[i] - leonie[i];
        }
        double unterDerWurzel = 0;
        for (int i = 0; i < differenzDerOrte.length; i++){
            double zwischenErgebnis = Math.pow(differenzDerOrte[i], 2);
            unterDerWurzel += zwischenErgebnis;
        }
        double result = Math.sqrt(unterDerWurzel);
        return result;
        //so weit sind wir auseinader!!!!
    }

    //Hilfsmethode
    public static int faculty(int n) {
        int result = 1;
        if(n == 0){
            result = 1;
        }else if(n > 0){
            for(int i = 1; i <= n; i++){
                result = result*i;
            }
        }else{
            n = n*-1;
            for(int i = 1; i <= n; i++){
                result = result*i;
            }
        }
        return result;
    }

    public static int [][] permutations(int n){
        int [] row = new int [n - 1];
        int permutationen = faculty(n); //Anzahl der Permutationen
        int [][] result = new int [permutationen][];
        return result;
    }

    public static int sgn (int [] permutation){
        return 1;
    }

    public static int determinant (int [][] A){
        return 1;
    }



    public static void main (String [] args){
        double [][] matrix = {{1, 3, 4},{3, 2, 5},{5, 1, 7}};
        double [] vektor = {2, 5, 6};
        double [] a = {2, 2};
        double [] b = {1, 3};
        System.out.println(Arrays.toString(multiply(matrix, vektor)));
        System.out.println(skalarRechnung(a, b));
        System.out.println(euclideanDistance(a, b));
        System.out.println(Arrays.deepToString(transpose(matrix)));
    }
}
