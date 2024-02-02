package org.example;

import java.util.Arrays;


public class Vector<T extends Number> {
    private T[] elements;

    public Vector(T[] elements) {
        this.elements = elements;
    }

    public Vector<T> add(Vector<T> other) {
        if (elements.length != other.elements.length) {
            return null;  // возвращаем null при различных длинах векторов
        }

        // Создаем новый массив с результатами сложения
        T[] resultElements = Arrays.copyOf(elements, elements.length);
        for (int i = 0; i < elements.length; i++) {
            resultElements[i] = addElements(elements[i], other.elements[i]);
        }

        return new Vector<>(resultElements);
    }

    private T addElements(T element1, T element2) {
        // Производим сложение с учетом типа T
        if (element1 instanceof Double) {
            return (T) Double.valueOf(((Number) element1).doubleValue() + ((Number) element2).doubleValue());
        } else if (element1 instanceof Integer) {
            return (T) Integer.valueOf(((Number) element1).intValue() + ((Number) element2).intValue());
        }
        return null; // Вернем null, если тип T не поддерживается
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    public static void main(String[] args) {
        Integer[] array1 = {1, 2, 3};
        Integer[] array2 = {4, 5, 6};
        Vector<Integer> vector1 = new Vector<>(array1);
        Vector<Integer> vector2 = new Vector<>(array2);

        Vector<Integer> result = vector1.add(vector2);
        System.out.println("Vector1: " + vector1);
        System.out.println("Vector2: " + vector2);
        System.out.println("Result: " + result);

        // Пример для случая с разной длиной векторов
        Double[] array3 = {1.0, 2.0, 3.0};
        Double[] array4 = {4.0, 5.0, 6.0, 7.0};
        Vector<Double> vector3 = new Vector<>(array3);
        Vector<Double> vector4 = new Vector<>(array4);

        Vector<Double> result2 = vector3.add(vector4);
        System.out.println("Vector3: " + vector3);
        System.out.println("Vector4: " + vector4);
        System.out.println("Result2: " + result2);  // Должен вернуть null
    }
}
