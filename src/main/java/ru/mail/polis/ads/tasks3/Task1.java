package ru.mail.polis.ads.tasks3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task1 {
    public static void main(String[] args) throws EmptyHeapException {
        FastScanner fastScanner = new FastScanner(System.in);
        Heap heap = new Heap();
        int cmdAmount = fastScanner.nextInt();
        for (int i = 0; i < cmdAmount; i++) {
            int cmd = fastScanner.nextInt();
            switch (cmd) {
                case (0):
                    heap.insert(fastScanner.nextInt());
                    break;
                case (1):
                    System.out.println(heap.delMax());
                    break;
            }
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}

class Heap {

    private List<Integer> array = new ArrayList<>();
    private int size = 0;


    public Heap() {
        array.add(0);
    }

    public void insert(int element) {
        size++;
        array.add(element);
        swim(size);
    }

    public int delMax() throws EmptyHeapException {
        if (size < 1) {
            throw new EmptyHeapException();
        }
        int max = array.get(1);
        swap(1, size);
        array.remove(size);
        size--;
        sink(1);
        return max;
    }

    private void swim(int k) {
        while (k > 1 && array.get(k) > array.get(k / 2)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && array.get(j) < array.get(j + 1)) j++;
            if (array.get(k) >= array.get(j)) break;
            swap(k, j);
            k = j;
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = array.get(firstIndex);
        array.set(firstIndex, array.get(secondIndex));
        array.set(secondIndex, temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < size; i++) {
            sb.append(array.get(i)).append(" ");
        }
        return sb.toString();
    }
}

class EmptyHeapException extends Exception {

    EmptyHeapException() {

    }
}
