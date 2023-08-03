package org.example;

import java.util.LinkedList;
import java.util.Optional;

public class BoundedStack<T> {
    // скрытые поля
    private LinkedList<T> stack; // основное хранилище стека
    private int peek_status; // статус запроса peek()
    private int pop_status; // статус команды pop()
    private int push_status; // статус команды push()
    private int maxSize; // максимальное допустимое количество элементов в стеке

    // интерфейс класса, реализующий АТД Stack
    public final int POP_NIL = 0;
    public final int POP_OK = 1;
    public final int POP_ERR = 2;
    public final int PEEK_NIL = 0;
    public final int PEEK_OK = 1;
    public final int PEEK_ERR = 2;
    public final int PUSH_NIL = 0;
    public final int PUSH_OK = 1;
    public final int PUSH_ERR = 2;

    // конструктор по умолчанию
    public BoundedStack() {
        // вызываем другой конструктор и задаем maxSize = 32
        this(32);
        clear();
    }

    // новый конструктор с параметром maxSize
    public BoundedStack(int maxSize) {
        this.maxSize = maxSize;
        clear();
    }

    //предусловие: стек пуст
    public void set_push_status(int PUSH_NIL) {
        this.push_status = PUSH_NIL;
    }
    //постусловиестек достиг максимального размера
    public void push(T value) {
        if (size() < maxSize) {
            stack.add(value);
            push_status = PUSH_OK;
        } else {
            // Если стек уже достиг максимального размера, не добавляем новый элемент
            push_status = PUSH_ERR;
        }
    }


    public void pop() {
        if(size() > 0) {
            stack.removeFirst();
            pop_status = POP_OK;
        }
        else {
            pop_status = POP_ERR;
        }
    }

    public void clear() {
        stack.clear();// пустой список/стек
    }

    // начальные статусы для предусловий peek() и pop()
    public void set_peek_status(int PEEK_NIL) {
        this.peek_status = PEEK_NIL;
    }

    public void set_pop_status(int POP_NIL) {
        this.pop_status = POP_NIL;
    }

    public Optional<T> peek() {
        if (size() > 0) {
            T result = stack.peekFirst();
            peek_status = PEEK_OK;
            // Обернуть значение в Optional
            return Optional.of(result);
        } else {
            peek_status = PEEK_ERR;
            // Вернуть пустой Optional, если значение отсутствует
            return Optional.empty();
        }
    }

    public int size() {
        return stack.size();
    }

    // запросы статусов
    public int get_pop_status() {
        return pop_status;
    }

    public int get_peek_status() {
        return peek_status;
    }

    //добавил запрос статуса для push
    public int get_push_status() {
        return push_status;
    }
}

