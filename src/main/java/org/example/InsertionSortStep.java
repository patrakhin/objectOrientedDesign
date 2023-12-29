package org.example;

import java.util.Arrays;

public class InsertionSortStep {

    // Метод для сортировки массива с заданным шагом
    public static int[] insertionSortStep(int[] array, int step) {
        if (step < 0 || step >= array.length) {
            return array;
        }
        int sizeBuffArray = 0;
        // Проходим по всем элементам массива с заданным шагом
        for (int i = 0; i < step; i ++) {
            if ((array.length - i) % step == 0){
                sizeBuffArray = (array.length - i) / step;
            }else{
                sizeBuffArray = (array.length - i) / step + 1;
            }
            int[] buffArray = new int[sizeBuffArray];
            int[] indexArray = new int[sizeBuffArray];
            for (int j = i, k = 0; j < array.length; j +=step, k++){
                    buffArray[k] = array[j];
                    indexArray[k] = j;
            }
            Arrays.sort(buffArray);
            for (int x = 0; x < buffArray.length; x++){
                int insertIndex = indexArray[x];
                int numberOfArray = buffArray[x];
                array[insertIndex] = numberOfArray;
            }
        }
        return array;
    }


    // Тестирование алгоритма
    public static void main(String[] args) {
        int[] array = {7, 6, 5, 4, 3, 2, 1};
        int step = 9;
        System.out.println(Arrays.toString(insertionSortStep(array, step)));
    }
}
