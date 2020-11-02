package ru.mail.polis.ads.tasks5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task4 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        String p = in.next();
        String s = in.next();
        char[] pattern  = new char[p.length()];
        char[] sequence = new char[s.length()];
        for (int i = 0; i < p.length(); i++) {
            pattern[i] = p.charAt(i);
        }
        for (int i = 0; i < s.length(); i++) {
            sequence[i] = s.charAt(i);
        }
        int[][] d = new int[p.length() + 1][s.length() + 1];
        d[0][0] = 1;
        for (int i = 1; i <= pattern.length; i++) {
            for (int j = 1; j <= sequence.length; j++) {
                if (sequence[j - 1] == pattern[i - 1] || pattern[i - 1] == '?' || sequence[j - 1] == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (pattern[i - 1] == '*' || sequence[j - 1] == '*') {
                    d[i][j] = d[i-1][j] | d[i][j - 1] | d[i - 1][j - 1];
                }
            }
        }
        try (PrintWriter out = new PrintWriter(System.out)) {
            out.write(d[pattern.length][sequence.length] == 1 ? "YES" : "NO");
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
