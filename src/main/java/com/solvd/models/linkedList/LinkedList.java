package com.solvd.models.linkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedList {
    private static final Logger logger = LoggerFactory.getLogger(LinkedList.class);

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

        public T get(int index) {
            if (index < 0) {
                logger.info("Invalid index");
                return null;
            }

            Node<T> current = head;
            int currentIndex = 0;
            while (current != null) {
                if (currentIndex == index) {
                    logger.info(current.data.toString());
                    return current.data;
                }
                current = current.next;
                currentIndex++;
            }
            logger.info("No element");
            return null;
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
