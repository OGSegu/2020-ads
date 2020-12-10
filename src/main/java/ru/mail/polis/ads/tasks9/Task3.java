package ru.mail.polis.ads.tasks9;

import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int vertexAmount = in.nextInt();
        int edgeAmount = in.nextInt();
        int startIndex = 0;
        int[][] edges = new int[edgeAmount][3];
        for (int i = 0; i < edgeAmount; i++) {
            edges[i][0] = in.nextInt() - 1;
            edges[i][1] = in.nextInt() - 1;
            edges[i][2] = in.nextInt();
        }
        final int INF = 30_000;
        int[] dist = new int[vertexAmount];
        for (int i = 0; i < vertexAmount; i++) {
            dist[i] = INF;
        }
        dist[startIndex] = 0;

        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < edgeAmount; j++)
                if (dist[edges[j][0]] < INF) {
                    dist[edges[j][1]] = Math.min(dist[edges[j][0]] + edges[j][2], dist[edges[j][1]]);
                }
        }
        try (PrintWriter out = new PrintWriter(System.out)) {
            for (int i = 0; i < vertexAmount; i++) {
                out.write(dist[i] + " ");
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
