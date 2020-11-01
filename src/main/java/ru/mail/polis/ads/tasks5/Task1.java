package ru.mail.polis.ads.tasks5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task1 {

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        final double C = Double.parseDouble(in.next());
        final double rate = 0.00000001;
        double end = C;
        double begin = rate;
        try (PrintWriter out = new PrintWriter(System.out)) {
            while (end - begin > rate) {
                double middle = (begin + end) / 2.0000000;
                double answer = check(middle);
                if (C > answer) {
                    begin = middle + rate;
                } else if (C < answer) {
                    end = middle;
                } else {
                    break;
                }
            }
            out.write(String.format("%.7f%n", end).replace(",", "."));
        }
    }

    private static double check(double middle) {
        return middle * middle + Math.sqrt(middle);
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
