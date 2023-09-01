package org.example;

import java.util.Objects;

public class HashTable<T> {

    private int size;
    private int step;
    private String [] slots;
    private int countSlotFull;
    private int put_status; // статус запроса put()
    private int remove_status; // статус команды remove()

    // интерфейс класса, реализующий АТД Stack
    public static final int PUT_OK = 1;
    public static final int PUT_ER = 2;
    public static final int REMOVE_OK = 1;
    public static final int REMOVE_ER = 2;


    public HashTable(int sz)
    {
        size = sz;
        step = 3;
        slots = new String[size];
        for(int i=0; i<size; i++) slots[i] = null;
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
            return -1;
        }
        int placePut = 0;
        for (; placePut < (size * step); indexSlot +=step, placePut++ ) {
            if (indexSlot > (size - 1)) {
                indexSlot = indexSlot - size;
            }
            if (slots [indexSlot] == null) {
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
        if (indexEmptySlot == -1) {
            put_status = PUT_ER;
        }
        if (slots[indexEmptySlot] == null) {
            slots[indexEmptySlot] = value.trim();
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
        if (slots[indexDeletableSlot] != null) {
            slots[indexDeletableSlot] = null;
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
            if (Objects.equals(slots[indexSlot], value.trim())) {
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

    // конструктор
    // постусловие: создана пустая хэш-таблица заданного размера

    // команды
    // предусловие: в таблице имеется свободный слот для value;
    // постусловие: в таблицу добавлено новое значение
    public void put(T value);

    // предусловие: в таблице имеется значение value;
    // постусловие: из таблицы удалено значение value
    public void remove(T value);

    // запросы
    public boolean isPresent(T value); // содержится ли значение value в таблице

    //public int size(); // количество элементов в таблице

    // запросы статусов (возможные значения статусов)
    //public int get_put_status(); // успешно;
    // система коллизий не смогла найти свободный слот для значения

    //public int get_remove_status(); // успешно; значения нету в таблице
}
