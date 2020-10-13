package ru.mail.polis.ads.tasks3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task2 {
    public static void main(String[] args) {
        FastScanner fastScanner = new FastScanner(System.in);
        int numberElements = fastScanner.nextInt();
        int[] array = new int[numberElements + 1];
        for (int i = 1; i < array.length; i++) {
            array[i] = fastScanner.nextInt();
        }
        boolean isHeap = true;
        for (int i = 1; i < array.length; i++) {
            if (2 * i <= numberElements) {
                if (array[i] > array[2 * i]) isHeap = false;
            }
            if (2 * i + 1 <= numberElements) {
                if (array[i] > array[2 * i + 1]) isHeap = false;
            }
        }
        System.out.println(isHeap ? "YES" : "NO");
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
