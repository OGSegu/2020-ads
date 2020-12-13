package ru.mail.polis.ads.tasks9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task4 {

    static boolean[] visited;
    static int[] prev;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        int vertexAmount = in.nextInt();
        int edgesAmount = in.nextInt();
        int from = in.nextInt() - 1;
        int to = in.nextInt() - 1;

        int[][] graph = new int[vertexAmount][vertexAmount];
        for (int i = 0; i < edgesAmount; i++) {
            int lFrom = in.nextInt();
            int lTo = in.nextInt();
            int weight = in.nextInt();
            graph[lFrom - 1][lTo - 1] = weight;
            graph[lTo - 1][lFrom - 1] = weight;
        }

        visited = new boolean[vertexAmount];
        dist = new int[vertexAmount];
        prev = new int[vertexAmount];
        for (int i = 0; i < vertexAmount; i++) {
            dist[i] = INF;
            prev[i] = -1;
        }
        getShortestPath(vertexAmount, from, graph);
        printPath(to);
    }

    private static void printPath(int finish) {
        try (PrintWriter out = new PrintWriter(System.out)) {
            if (dist[finish] != INF) {
                out.write(dist[finish] + "\n");
                List<Integer> path = new ArrayList<>();
                path.add(finish);
                int j = prev[finish];
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

    private static void getShortestPath(int n, int start, int[][] graph) {
        visited[start] = true;
        dist[start] = 0;
        int minDistance = 0;
        int minVertex = start;

        while (minDistance < INF) {
            int current = minVertex;
            visited[current] = true;
            for (int i = 0; i < n; i++) {
                if (graph[current][i] != 0) {
                    if (dist[current] + graph[current][i] < dist[i]) {
                        dist[i] = dist[current] + graph[current][i];
                        prev[i] = current;
                    }
                }
            }
            minDistance = INF;
            for (int j = 0; j < n; ++j) {
                if (!visited[j] && dist[j] < minDistance) {
                    minDistance = dist[j];
                    minVertex = j;
                }
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


