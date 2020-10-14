package ru.mail.polis.ads.tasks3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task5 {
    public static void main(String[] args) {
        FastScanner fastScanner = new FastScanner(System.in);
        int placesAmount = fastScanner.nextInt();
        int cowsAmount = fastScanner.nextInt();
        int[] coords = new int[placesAmount];
        for (int i = 0; i < placesAmount; i++) {
            coords[i] = fastScanner.nextInt();
        }
        int left = 0;
        int right = coords[placesAmount - 1] - coords[0] + 1;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (isPossible(coords, mid, cowsAmount)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            printWriter.write(String.valueOf(left));
        }
    }

    public static boolean isPossible(int[] array, int value, int cowsAmount) {
        int count = 1;
        int lastCow = array[0];
        for (int coord : array) {
            if (coord - lastCow >= value) {
                count++;
                lastCow = coord;
            }
        }
        return count >= cowsAmount;
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
