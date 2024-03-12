package ru;

import java.util.*;


interface UniversalSortInterface<T extends Comparable<T>> {
    List<T> sort(List<T> items);
}

public class UniversalSort<T extends Comparable<T>> implements UniversalSortInterface<T> {

    @Override
    public List<T> sort(List<T> items) {
        return items.stream()
                .sorted()
                .toList();
    }
}
