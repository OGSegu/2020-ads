package ru.mail.polis.ads.tasks4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task4 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int numbersOfElements = in.nextInt();
        int[] cost = new int[numbersOfElements + 2];
        for (int i = 1; i < cost.length - 1; i++) cost[i] = in.nextInt();
        int maxStep = in.nextInt();
        int[] dArray = new int[numbersOfElements + 2];
        for (int i = 1; i < dArray.length; i++) {
            int localMax = Integer.MIN_VALUE;
            for (int j = i - 1; j >= Math.max(i - maxStep, 0); j--) {
                if (dArray[j] > localMax) localMax = dArray[j];
            }
            dArray[i] = localMax + cost[i];
        }
        try (PrintWriter out = new PrintWriter(System.out)) {
            out.write(String.valueOf(dArray[dArray.length - 1]));
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