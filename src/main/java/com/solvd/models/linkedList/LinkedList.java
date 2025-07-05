package com.solvd.models.linkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.App;

public class LinkedList {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class MyLinkedList<T> {
        private Node<T> head;

        public void add(T data) {
            Node<T> newNode = new Node<>(data);
            if (head == null) {
                head = newNode;
                return;
            }
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        public void remove(T data) {
            if (head == null) {
                return;
            }
            if (head.data.equals(data)) {
                head = head.next;
                return;
            }
            Node<T> current = head;
            while ((current.next != null) && (!current.next.data.equals(data))) {
                current = current.next;
            }
            if ((current.next != null) && (current.next.data.equals(data))) {
                current.next = current.next.next;
            }
        }

        public void print() {
            Node<T> current = head;
            StringBuilder linkedListPrint = new StringBuilder();
            while (current != null) {
                linkedListPrint.append(current.data);
                if (current.next != null) {
                    linkedListPrint.append(" < ");
                }
                current = current.next;
            }
            logger.info(linkedListPrint.toString());

        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.print();

        list.remove("B");
        list.print();
    }
}
