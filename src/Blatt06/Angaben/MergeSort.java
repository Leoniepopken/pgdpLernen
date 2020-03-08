package Angaben;

public class MergeSort {

    public static void mergeSort (int [] array){
        mergeSort(0, array.length, array);
    }

    public static void mergeSort(int von, int bis, int [] array){
        //basecase
        if (von == bis){
            return;
        }
        int mitte = (bis + von)/2;
        mergeSort(von, mitte, array);
        mergeSort(mitte, bis, array);
        merge(von, mitte, bis, array);
    }

    private static void merge(int von, int mitte, int bis, int[] array) {
        int mitteAlt = mitte;
        int [] array1 = new int[mitte - von];
        int [] array2 = new int[bis - mitte];
        for (int i = von; i < mitte; i++){
            array1[i - von] = array[i];
        }
        for (int i = mitte; i < bis; i++) {
            array2[i - mitte] = array[i];
        }
        if (von == mitteAlt){

        }
    }
}
