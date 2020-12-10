package ru.mail.polis.ads.tasks9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Task2 {
    static boolean[] checked;
    static List<Integer>[] graphs;
    static int minCycle = Integer.MAX_VALUE;

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int vertexAmount = in.nextInt();
        int edgeAmount = in.nextInt();
        graphs = new ArrayList[vertexAmount + 1];
        checked = new boolean[vertexAmount + 1];
        for (int i = 1; i < graphs.length; i++) {
            graphs[i] = new ArrayList<>();
        }
        for (int i = 1; i <= edgeAmount; i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            graphs[first].add(second);
        }
        for (int i = 1; i < graphs.length; i++) {
            if (!checked[i])
                dfs(i);
        }
        try (PrintWriter out = new PrintWriter(System.out)) {
            out.write("Yes\n" + minCycle);
        }
    }

    private static void dfs(int index) {
        checked[index] = true;

        for (int element : graphs[index]) {
            if (!checked[element]) {
                dfs(element);
            } else {
                minCycle = Math.min(minCycle, element);
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
