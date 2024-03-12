package ru;

import java.util.*;

public class AlgSort {
    public static void bubblesSort(int[] array){
        for(int i =0; i < array.length-1; i++){
            for (int j = 0; j < array.length-1-i; j++){
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    public static void selectSort (int[] array){
        for (int i = 0; i < array.length; i++){
            int pos = i;
            int min = array[i];
            for (int j = i+1; j < array.length-1; j++){
                if (array[j] < min){
                    pos = j;
                    min = array[j];
                }
            }
            array[pos] = array[i];
            array[i] = min;
        }
    }

    public static int findKthSmallest(int[] array, int k){
        if (array == null || k > array.length){
            throw new IllegalArgumentException("Массив пуст или k больше размера массива.");
        }
        selectSort(array);
        return array[k-1];
    }

    public static void insertSort (String[] array){
        for (int i = 1; i < array.length; i++){
            String key = array[i];
            int j;
            for (j=i-1; j>= 0 && array[j].compareTo(key) > 0;j--){
                array[j+1] = array[j];
            }
            array[j+1] = key;
        }
    }

    public static List<String> sortWords(List<String> anyList){
        return anyList.stream()
                .sorted()
                .toList();
    }

    public static List<Integer> getSort(List<Integer> numbers){
        return  numbers.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public static void main(String[] args) {
        //У вас есть массив целых чисел, и вам нужно отсортировать его в порядке неубывания.
        int[] bubbleArray = {64, 34, 25, 12, 22, 11, 90};
        bubblesSort(bubbleArray);
        System.out.println(Arrays.toString(bubbleArray));
        System.out.println("*********");
        List<Integer> anyNumbers = Arrays.asList(64, 34, 25, 12, 22, 11, 90);
        System.out.println(getSort(anyNumbers));
        System.out.println("*********");
        //Дан массив целых чисел, требуется найти k-ый наименьший элемент в массиве.
        int[] selectArray = {12, 3, 7, 19, 5};
        int k = 2; // Найти второй наименьший элемент
        System.out.println(findKthSmallest(selectArray, k));
        System.out.println("*********");
        //Дан массив строк, и вам нужно отсортировать его в лексикографическом порядке (по алфавиту)
        String[] insertArray = {"apple", "banana", "orange", "kiwi", "grape"};
        insertSort(insertArray);
        System.out.println(Arrays.toString(insertArray));
        System.out.println("*********");
        //Дан массив строк, и вам нужно отсортировать его в лексикографическом порядке (по алфавиту)
        List<String> listWords = Arrays.asList("apple", "banana", "orange", "kiwi", "grape", "cola", "water");
        System.out.println(sortWords(listWords));
    }
}
