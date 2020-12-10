package ru.mail.polis.ads.tasks9;

import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task4 {

    static List<AbstractMap.SimpleEntry<Integer, Integer>>[] edgeList;
    static boolean[] visited;

    static final int INF = 100_000;

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        int vertexAmount = in.nextInt();
        int edgeAmount = in.nextInt();
        int from = in.nextInt();
        int to = in.nextInt();


        edgeList = new ArrayList[edgeAmount * 2];
        visited = new boolean[vertexAmount];

        for (int i = 0; i < edgeAmount; i++) {
            int localFrom = in.nextInt() - 1;
            int localTo = in.nextInt() - 1;
            int weight = in.nextInt();
            if (edgeList[localFrom] == null)
                edgeList[localFrom] = new ArrayList<>();
            if (edgeList[localTo] == null)
                edgeList[localTo] = new ArrayList<>();
            edgeList[localFrom].add(new AbstractMap.SimpleEntry<>(localTo, weight));
            edgeList[localTo].add(new AbstractMap.SimpleEntry<>(localFrom, weight));
        }


        int[] dist = new int[vertexAmount];
        int[] prev = new int[vertexAmount];
        for (int i = 0; i < vertexAmount; i++) {
            dist[i] = INF;
            prev[i] = -1;
        }
        shortestPath(dist, from, to, vertexAmount, prev);
        try (PrintWriter out = new PrintWriter(System.out)) {
            if (dist[to - 1] != INF) {
                out.write(dist[to - 1] + "\n");
                List<Integer> path = new ArrayList<>();
                path.add(to - 1);
                int j = prev[to - 1];
                while (j != -1) {
                    path.add(j);
                    j = prev[j];
                }
                for (int i = path.size() - 1; i >= 0; i--) {
                    out.write((path.get(i) + 1) + " ");
                }
            } else {
                out.write("-1");
            }
        }
    }

    private static void shortestPath(int[] dist, int from, int to, int vertexAmount, int[] prev) {
        dist[from - 1] = 0; // Вычитаем единицу так как работает с 0 индексами
        visited[from - 1] = true;
        int findFrom = from - 1;
        int minIndex = 0;
        for (int i = 0; i < vertexAmount; i++) {
            visited[findFrom] = true;
            int min = INF;
            for (int j = 0; j < edgeList[findFrom].size(); j++) {
                int indexTo = edgeList[findFrom].get(j).getKey();
                if (indexTo < INF && !visited[indexTo]) {
                    int weightTo = edgeList[findFrom].get(j).getValue() + dist[findFrom];
                    if (dist[indexTo] > weightTo) {
                        prev[indexTo] = findFrom;
                    } else {
                        continue;
                    }
                    dist[indexTo] = weightTo;
                    if (weightTo < min) {
                        min = weightTo;
                        minIndex = indexTo;
                        if (minIndex == to - 1) {
                            return;
                        }
                    }
                }
            }
            findFrom = minIndex;

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


