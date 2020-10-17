package ru.mail.polis.ads.tasks4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task2 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] floorArray = new int[m + 1][n + 1];
        int[][] dynamicArray = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                floorArray[i][j] = in.nextInt();
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j <= n; j++) {
                dynamicArray[i][j] = Math.max(dynamicArray[i + 1][j], dynamicArray[i][j - 1]) + floorArray[i][j];
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = n;
        int number = dynamicArray[i][j];
        while (i != m && j != 1) {
            int delta = number - floorArray[i][j];
            if (delta == dynamicArray[i][j - 1]) {
                sb.append("R");
                j--;
            } else if (delta == dynamicArray[i + 1][j]) {
                sb.append("F");
                i++;
            }
            number = dynamicArray[i][j];
        }
        if (i == m) {
            while (j != 1) {
                sb.append("R");
                j--;
            }
        } else {
            while (i < m - 1) {
                sb.append("F");
                i++;
            }
        }
        try (PrintWriter out = new PrintWriter(System.out)) {
            out.write(sb.reverse().toString());
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
