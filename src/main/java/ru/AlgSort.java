package ru;

import java.util.Arrays;

public class AlgSort {
    public static void bubblesSort(int[] array){
        boolean swap;
        for (int i = 0; i < array.length - 1; i++){
            swap = false;
            for (int j = 0; j < array.length - 1 - i; j++){
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;

                    swap = true;
                }
            }
            if (!swap){
                break;
            }
        }
    }

    public static void selectSort (int[] array){
        for (int i = 0; i < array.length; i++){
            int pos = i;
            int min = array[i];
            for (int j = i+1; j < array.length; j++){
                if (array[j] < min){
                    pos = j;
                    min = array[j];
                }
            }
            array[pos] = array[i];
            array[i] = min;
        }
    }

    public static void insertSort (int[] array){
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j;
            for (j = i - 1; j >= 0 && array[j] > key; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }
    }


    public static void main(String[] args) {
        int[] bubbleArray = {2, 7, 1, 6, 5, 4, 3};
        bubblesSort(bubbleArray);
        System.out.println(Arrays.toString(bubbleArray));
        System.out.println("*********");
        int[] selectArray = {2,4,6,8,1,3,5,7};
        selectSort(selectArray);
        System.out.println(Arrays.toString(selectArray));
        System.out.println("*********");
        int[] insertArray = {6,8,1,3,5,7};
        insertSort(insertArray);
        System.out.println(Arrays.toString(insertArray));
        System.out.println("*********");
    }
}
