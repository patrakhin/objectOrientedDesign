package org.example;

import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;

    Book(String title) {
        this.title = title;
    }

    void read() {
        System.out.println("Reading the book: " + title);
    }
}

class Library {
    private List<Book> bookCollection = new ArrayList<>();

    void addBook(Book book) {
        bookCollection.add(book);
    }

    void provideBook() {
        if (!bookCollection.isEmpty()) {
            Book book = bookCollection.remove(0);
            book.read();
        } else {
            System.out.println("No available books");
        }
    }
}

public class ContainsRelationshipLibrary {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Master and Margarita");
        Book book2 = new Book("Crime and Punishment");

        library.addBook(book1);
        library.addBook(book2);

        library.provideBook();  // Читаем книгу: Мастер и Маргарита
        library.provideBook();  // Читаем книгу: Преступление и наказание
        library.provideBook();  // Нет доступных книг
    }
}

