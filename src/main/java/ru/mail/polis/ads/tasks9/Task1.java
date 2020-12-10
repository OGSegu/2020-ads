package ru.mail.polis.ads.tasks9;

import java.io.*;
import java.util.*;

public class Task1 {

    // 0 - not checked
    // 1 - dfs checking
    // 2 - checked
    static byte[] checked;
    static List<Integer>[] graphs;
    static boolean hasCycle = false;
    static List<Integer> resultList = new ArrayList<>();

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int vertexAmount = in.nextInt();
        int edgeAmount = in.nextInt();
        graphs = new ArrayList[vertexAmount + 1];
        checked = new byte[vertexAmount + 1];
        for (int i = 1; i < graphs.length; i++) {
            graphs[i] = new ArrayList<>();
        }
        for (int i = 1; i <= edgeAmount; i++) {
            int first = in.nextInt();
            int second = in.nextInt();
            graphs[first].add(second);
        }

        for (int i = 1; i <= vertexAmount; i++) {
            if (checked[i] == 0) {
                dfs(i);
            }
        }

        try (PrintWriter out = new PrintWriter(System.out)) {
            if (hasCycle) {
                out.write("-1");
            } else {
                for (int i = resultList.size() - 1; i >= 0; i--) {
                    out.write(resultList.get(i) + " ");
                }
            }
        }
    }

    private static void dfs(int index) {
        checked[index] = 1;
        for (int element : graphs[index]) {
            if (checked[element] == 0) {
                dfs(element);
            } else if (checked[element] == 1) {
                hasCycle = true;
                break;
            }
        }
        checked[index] = 3;
        resultList.add(index);
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