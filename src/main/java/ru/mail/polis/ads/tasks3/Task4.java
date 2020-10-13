package ru.mail.polis.ads.tasks3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task4 {
    public static void main(String[] args) {
        FastScanner fastScanner = new FastScanner(System.in);
        int elementsNumber = fastScanner.nextInt();
        int queriesNumber = fastScanner.nextInt();
        int[] numbersArray = new int[elementsNumber];
        for (int i = 0; i < elementsNumber; i++) {
            numbersArray[i] = fastScanner.nextInt();
        }
        for (int i = 0; i < queriesNumber; i++) {
            int value = fastScanner.nextInt();
            System.out.println(binarySearch(numbersArray, value) ? "YES" : "NO");
        }
    }

    private static boolean binarySearch(int[] array, int value) {
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) / 2;
            if (array[mid] < value) {
                begin = mid + 1;
            } else if (array[mid] > value) {
                end = mid;
            } else {
                return true;
            }
        }
        return false;
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