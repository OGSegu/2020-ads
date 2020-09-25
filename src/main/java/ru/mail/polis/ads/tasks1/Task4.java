package ru.mail.polis.ads.tasks1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        String ans;
        String line;
        do {
            line = br.readLine();
            try {
                switch (line) {
                    case "pop":
                        ans = String.valueOf(stack.pop());
                        break;
                    case "back":
                        ans = String.valueOf(stack.back());
                        break;
                    case "size":
                        ans = String.valueOf(stack.size());
                        break;
                    case "clear":
                        stack.clear();
                        ans = "ok";
                        break;
                    case "exit":
                        ans = "bye";
                        break;
                    default:
                        int element = Integer.parseInt(line.split(" ")[1]);
                        stack.push(element);
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

class Stack<T> {
    private int size = 0;
    private Node<T> last = null;

    public Stack() {
    }

    public void push(T element) {
        Node<T> cLast = this.last;
        this.last = new Node<>(element, cLast);
        this.size++;
    }

    public T pop() {
        if (size == 0)
            throw new EmptyStackException();
        T element = last.current;
        this.last = this.last.next;
        this.size--;
        return element;
    }

    public void clear() {
        Node next;
        for (Node x = this.last; x != null; x = next) {
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

    public T back() {
        if (size == 0)
            throw new EmptyStackException();
        return this.last.current;
    }

    public Node<T> last() {
        return this.last;
    }
}

class Node<T> {
    T current;
    Node<T> next;

    public Node(T currentElement, Node<T> nextElement) {
        this.current = currentElement;
        this.next = nextElement;
    }
}
