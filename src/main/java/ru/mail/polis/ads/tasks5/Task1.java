package ru.mail.polis.ads.tasks5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task1 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int[] numbers = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            numbers[i - 1] = i;
        }
        int i = n;
        List<Integer> tailIndex = new ArrayList<>();
        while (i + 1 > i) {
            tailIndex.add(i);
            i--;
        }
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int index : tailIndex) {
            if (numbers[index] <= numbers[i - 1]) {
               continue;
            }
            if (numbers[i - 1] < min) {
                min = numbers[index];
                minIndex = index;
            }
        }
        // SWAP
        int temp = numbers[minIndex];
        numbers[i - 1] = temp;
        numbers[minIndex] = numbers[i - 1];

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
