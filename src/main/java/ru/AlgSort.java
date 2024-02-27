package ru;

import java.util.Arrays;

public class AlgSort {
    public static void bubblesSort(int[] array){
       boolean swapped;
       for (int i = 0; i < array.length - 1; i++){
           swapped = false;
           for (int j = 0; j < array.length - 1 - i; j++){
               if (array[j] > array[j+1]){
                   int temp = array[j];
                   array[j] = array[j+1];
                   array[j+1] = temp;

                   swapped = true;
               }
           }
           if (!swapped){
               break;
           }
       }
    }

    public static void main(String[] args) {
        int[] unsorted = {2, 7, 1, 6, 5, 4, 3};
        bubblesSort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }
}
