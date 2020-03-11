package Angaben;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] array) {
        Arrays.sort(array);
        return;
        //mergeSort(0, array.length, array);
    }

    public static void mergeSort(int von, int bis, int[] array) {
        //basecase
        if (bis<=von) {
            return;
        }
        int mitte = (bis + von) / 2;
        //System.out.println(von + ", " + bis + ", " + mitte);
        mergeSort(von, mitte, array);
        mergeSort(mitte + 1, bis, array);
        merge(von, mitte, bis, array);
    }

    private static void merge(int von, int mitte, int bis, int[] array) {
        int[] array1 = new int[(mitte + 1) - von]; //links
        int[] array2 = new int[bis - mitte]; //rechts
        for (int i = von; i < mitte + 1; i++) {
            array1[i - von] = array[i];
        }
        for (int i = mitte + 1; i <= bis; i++) {
            array2[i - mitte - 1] = array[i-1];
        }
        int index1 = 0;
        int index2 = 0;
        while (von < bis) {
            if (index1 == array1.length) {
                for (int i = index2; i < array2.length; i++) {
                    array[von] = array2[i];
                    von++;
                }
            } else if (index2 == array2.length) {
                for (int i = index1; i < array1.length; i++) {
                    array[von] = array1[i];
                    von++;
                }
            } else if (array1[index1] < array2[index2]) {
                array[von] = array1[index1];
                index1++;
            } else {
                array[von] = array2[index2];
                index2++;
            }
            von++;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int [] a = new int[]{5,6,8,3,7,9,1,2,3,6};
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}
