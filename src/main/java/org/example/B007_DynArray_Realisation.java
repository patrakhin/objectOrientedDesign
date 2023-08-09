package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class B007_DynArray_Realisation<T>{

    //Скрытые поля
    private ArrayList<T> arrayList;
    private int capacity = 16;

    //запросы статусов
    private int get_makeArray_status; // возвращает значение MAKE_ARRAY_*
    private int get_Append_status; // возвращает значение APPEND_*
    private int get_Insert_status; // возвращает значение INSERT_*
    private int get_Remove_status; // возвращает значение REMOVE_*

    //интерфейс класса, реализующий АТД DynArray
    public final int MAKE_ARRAY_NIL = 0; // makeArray еще не вызывался
    public final int MAKE_ARRAY_OK = 1; // последний makeArray отработал нормально
    public final int MAKE_ARRAY_ER = 2; // текущий массив заданного размера не полон

    public final int APPEND_NIL = 0; // Append еще не вызывался
    public final int APPEND_OK = 1; // последний Append отработал нормально

    public final int INSERT_NIL = 0; //makeArray еще не вызывался
    public final int INSERT_OK = 1; // последний Insert отработал нормально
    public final int INSERT_ER = 2; // ячейка с индексом уже занята

    public final int REMOVE_NIL = 0; // makeArray еще не вызывался
    public final int REMOVE_OK = 1; // последний Remove отработал нормально
    public final int REMOVE_ER = 2; // объекты в массиве отсутствуют

    // конструктор
    public B007_DynArray_Realisation() {
        makeArray(16);
        arrayList = new ArrayList<>(capacity);

    } // постусловие: создан пустой массив фиксированного размера

    //предусловие: массив создается впервые
    //постусловие: добавляется новый объект в хвост массива
    //постусловие: создается условие для увеличения размера массива, если будет произведена эта операция еще раз
    public void Append(T value){
        arrayList.add(value);
        this.get_Append_status = APPEND_OK;
    }; //команда

    //предусловие: массив заданного размера создается впервые или текущий массив заданного размера полон
    //постусловие: массив увеличивается на заданный размер
    public void makeArray(int new_capacity){
        this.capacity = new_capacity;
    }; //команда

    //предусловие: массив создается впервые
    //постусловие: добавляется новый объект по индексу
    //постусловие: создается условие для увеличения размера массива, если будет произведена эта операция еще раз
    public void Insert(T element, int index){
        arrayList.add(index, element);
        this.get_Insert_status = INSERT_OK;
    }; //команда

    //предусловие: массив не пустой
    //постусловие: освобождается ячейка для вставки нового значения
    //постусловие: создается условие для уменьшения размера массива
    public void Remove(int index){
        arrayList.remove(index);
        this.get_Remove_status = REMOVE_OK;
    }; //команда


}
