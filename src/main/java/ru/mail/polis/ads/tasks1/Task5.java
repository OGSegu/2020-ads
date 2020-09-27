package ru.mail.polis.ads.tasks1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;

public class Task5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new Queue<>();
        String ans;
        String line;
        do {
            line = br.readLine();
            try {
                switch (line) {
                    case "pop":
                        ans = String.valueOf(queue.pop());
                        break;
                    case "front":
                        ans = String.valueOf(queue.front());
                        break;
                    case "size":
                        ans = String.valueOf(queue.size());
                        break;
                    case "clear":
                        queue.clear();
                        ans = "ok";
                        break;
                    case "exit":
                        ans = "bye";
                        break;
                    default:
                        int element = Integer.parseInt(line.split(" ")[1]);
                        queue.push(element);
                        ans = "ok";
                        break;
                }
            } catch (EmptyStackException e) {
                ans = "error";
            }
            System.out.println(ans);
        } while (!line.equals("exit"));
    }
}

class Queue<T> {

    private int size = 0;
    private Node<T> first = null;
    private Node<T> last = null;

    public Queue() {
    }

    public void push(T element) {
        Node<T> node;
        if (size == 0) {
            node = new Node<>(null, element, null);
            this.first = node;
        } else {
            node = new Node<>(null, element, last);
            this.last.previous = node;
        }
        this.last = node;
        this.size++;
    }

    public T pop() {
        if (size == 0)
            throw new EmptyQueueException();
        T element = first.current;
        this.first = first.previous;
        this.size--;
        return element;
    }

    public T front() {
        if (size == 0)
            throw new EmptyQueueException();
        return first.current;
    }

    public void clear() {
        Node<T> next;
        for (Node<T> x = this.last; x != null; x = next) {
            next = x.next;
            x.current = null;
            x.next = null;
        }
        this.last = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public Node<T> first() {
        return first;
    }

    public Node<T> last() {
        return  last;
    }

    static class Node<T> {
        Node<T> previous;
        T current;
        Node<T> next;

        public Node(Node<T> previousElement, T currentElement, Node<T> nextElement) {
            this.previous = previousElement;
            this.current = currentElement;
            this.next = nextElement;
        }
    }
}

class EmptyQueueException extends RuntimeException {

}