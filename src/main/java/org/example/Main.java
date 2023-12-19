package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


interface ListElement{
    int getValue();
}

class Node implements ListElement{
    private final int value;
    private Node next;

    public Node(int inputValue){
        this.value = inputValue;
        this.next = null;
    }

    @Override
    public int getValue(){
        return value;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node newNode){
        this.next = newNode;
    }
}

class LinkedList {
    private static final Logger log = LogManager.getLogger(LinkedList.class);
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public void addNode(Node newNode) {
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
    }

    public void displayList() {
        Node current = head;
        while (current != null) {
            log.info("{}" ,current.getValue());
            current = current.getNext();
        }
    }
}

class Main {
    private static final Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        // Пример использования наследования и композиции
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        LinkedList linkedList = new LinkedList();
        linkedList.addNode(node1);
        linkedList.addNode(node2);
        linkedList.addNode(node3);

        // Пример использования полиморфизма через интерфейс
        ListElement listElement = new Node(4);
        log.info("Value of list element: {}", listElement.getValue());

        // Отображение элементов списка
        log.info("Linked List: ");
        linkedList.displayList();
    }
}