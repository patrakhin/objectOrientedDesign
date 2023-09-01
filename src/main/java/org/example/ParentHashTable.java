package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParentHashTable<T> {

    private int size;
    private int step;
    List <T> slots;
    private int countSlotFull;
    private int put_status; // статус запроса put()
    private int remove_status; // статус команды remove()

    // интерфейс класса, реализующий АТД ParentHashTable
    public static final int PUT_OK = 1;
    public static final int PUT_ER = 2;
    public static final int PUT_FULL = 3;
    public static final int REMOVE_OK = 1;
    public static final int REMOVE_ER = 2;


    public ParentHashTable(int sz)
    {
        size = sz;
        step = 3;
        slots = new ArrayList<>(size);
        countSlotFull = (size - 1);
    }

    public int hashFun(String value)
    {
        String valueString = value.trim();
        int sumCode = 0;
        for (int i = 0; i < valueString.length(); i++) {
            char j = valueString.charAt(i);
            sumCode = sumCode + j;
        }
        return (((sumCode) % step) % size);
    }


    public int seekSlot(String value)
    {
        int indexSlot = hashFun(value.trim());
        if (countSlotFull == -1) {
            return -2;
        }
        int placePut = 0;
        for (; placePut < (size * step); indexSlot +=step, placePut++ ) {
            if (indexSlot > (size - 1)) {
                indexSlot = indexSlot - size;
            }
            if (slots.get(indexSlot) == null) {
                return indexSlot;  // seek empty slot
            }
            if (indexSlot == (size - 1)) {
                indexSlot = (step - 1);
            }
        }
        return -1;
    }

    public void put(String value)
    {
        int indexEmptySlot = seekSlot(value.trim());
        if (indexEmptySlot == -2) {
            put_status = PUT_FULL;
        }
        if (slots.get(indexEmptySlot) == null) {
            slots.set(indexEmptySlot, (T) value.trim());
            countSlotFull --;
            put_status = PUT_OK;
        }
        put_status = PUT_ER;
    }

    public void remove(String value)
    {
        int indexDeletableSlot = hashFun(value.trim());
        if (!isPresent(value.trim())) {
            remove_status = REMOVE_ER;
        }
        if (slots.get(indexDeletableSlot) != null) {
            slots.set(indexDeletableSlot, null);
            countSlotFull ++;
            remove_status = REMOVE_OK;
        }
        remove_status = REMOVE_ER;
    }

    public boolean isPresent(String value)
    {
        int indexSlot = hashFun(value.trim());
        if (indexSlot > (size - 1)) {
            return false;
        }
        int placePut = 0;
        for (; placePut < (size * step); indexSlot +=step, placePut++ ) {
            if (indexSlot > (size - 1)) {
                indexSlot = indexSlot - size;
            }
            if (Objects.equals(slots.get(indexSlot), value.trim())) {
                return true;  // value is define
            }
            if (indexSlot == (size - 1)) {
                indexSlot = (step - 1);
            }
        }
        return false;
    }

    public int size(){
        return this.size;
    }

    // запросы статусов (возможные значения статусов)
    public int get_put_status() // успешно;
    {
        return put_status;
    }
    // система коллизий не смогла найти свободный слот для значения

    public int get_remove_status() // успешно; значения нету в таблице
    {
        return remove_status;
    }

}


class PowerSet<T> extends ParentHashTable<T>{

    private int sizeList;

    private int putValue_status; // статус запроса put()

    // интерфейс класса, реализующий АТД ParentHashTable
    public static final int PUT_VAL_OK = 1;
    public static final int PUT_VAL_ER = 2;
    public static final int PUT_VAL_FULL = 3;

    //конструктор
    //постусловие: создано пустое множество
    //с ограничением на максимальное количество элементов в множестве
    public PowerSet(int sz) {
        super(sz);
        this.sizeList = sz;
    }

    // команды
    // предусловие: в таблице имеется свободное место для value
    // предусловие: value в таблице нет
    // постусловие: в таблицу добавлено новое значение
    public void putValue(String value){
        if (isPresent(value.trim())){
            putValue_status = PUT_VAL_FULL;
        }
        if (!isPresent(value.trim())){
            put(value.trim());
            putValue_status = PUT_VAL_OK;
        }
        putValue_status = PUT_VAL_ER;
    }

    //постусловие: возвращается пересечение множеств
    public PowerSet<T> getIntersection(PowerSet<T> set2){
        PowerSet<T> arrayIntersection = new PowerSet<>(sizeList);
        if (slots == null || set2.size() == 0) {
            return arrayIntersection;
        }
        for (int i = 0; i < set2.size(); i ++) {
            String buff = (String) set2.slots.get(i);
            if (isPresent(buff)) {
                arrayIntersection.slots.set(i, (T) buff);
            }
        }
        // intersection the powerSet and set2
        return arrayIntersection; // return empty  Power Set but not null!!!!
    }

    //постусловие: возвращается объединение множеств
    public PowerSet<T> getUnion(PowerSet<T> set2){
        PowerSet<T> arrayUnion = new PowerSet<>(sizeList);
        if (slots == null) {
            return set2;
        }
        if (set2.size() == 0) {
            arrayUnion.slots.addAll(slots);
            return arrayUnion;
        }
        arrayUnion.slots.addAll(slots);
        for (int i = 0; i < set2.size(); i ++) {
            String buff = (String) set2.slots.get(i);
            if (!isPresent(buff)) {
                arrayUnion.slots.add((T) buff);
            }
        }
        return arrayUnion; // return empty  Power Set but not null!!!!
    }

    //постусловие: возвращается подмножество текущего множества из таких элементов,
    //которые не входят в множество-параметр
    public PowerSet getDifference(PowerSet<T> set2){
        PowerSet listIDifference = new PowerSet(sizeList);

        if (!slots.isEmpty() && set2.size() == 0) {
            listIDifference.slots.addAll(slots);
            return listIDifference;
        }
        if (slots.isEmpty() && set2.size() > 0) {
            listIDifference.slots.addAll(slots);
            return listIDifference;
        }
        listIDifference.slots.addAll(slots);
        for (int i = 0; i < set2.size(); i++) {
            String buff = (String) set2.slots.get(i);
            listIDifference.remove(buff);
        }
        return listIDifference; // return empty  Power Set but not null!!!!
    }

    //запросы
    //будет ли множество-параметр подмножеством текущего множества
    public boolean IsSubset(PowerSet<T> set2){
        if (set2.size() == 0 && !slots.isEmpty()) {
            return true;
        }
        int countHit = 0;
        for (int i = 0; i < set2.size(); i++) {
            String buff = (String) set2.slots.get(i);
            if (slots.contains(buff)) {
                countHit ++;
            }
        }
        if (countHit == set2.size() && countHit < slots.size()) {
            return true;
        }
        if (countHit == set2.size() && countHit == slots.size()) {
            return true;
        }
        return false;
        // return true, if set2 is
        // subSet this pSet,
        // else false
    }

    // запросы статусов (возможные значения статусов)
    public int get_putValue_status(){
        return putValue_status;
    } // успешно; нет места; value в таблице есть

}

