package ru.mail.polis.ads.tasks5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task2 {

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int amounts = in.nextInt();
        int[] numbers = new int[amounts + 1];
        for (int i = 1; i <= amounts; i++) numbers[i] = in.nextInt();
        int[] d = new int[amounts + 1];
        for (int i = 1; i <= amounts; i++) {
            int min = 0;
            for (int j = i - 1; j > 0; j--) {
                if (numbers[j] == 0) continue;
                if (numbers[i] % numbers[j] == 0) {
                    if (d[j] > min) min = d[j];
                }
            }
            d[i] = min + 1;
        }
        try (PrintWriter out = new PrintWriter(System.out)) {

            out.write(String.valueOf(Arrays.stream(d).max().getAsInt()));
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
