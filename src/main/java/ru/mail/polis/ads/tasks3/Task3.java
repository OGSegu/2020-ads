package ru.mail.polis.ads.tasks3;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Task3 {
    public static void main(String[] args) throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        Queue<Integer> maxOrdered = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> minOrdered = new PriorityQueue<>();
        int median = -1;
        String numberString;
        int i = 1;
        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            while ((numberString = fastScanner.reader.readLine()) != null) {
                int number = Integer.parseInt(numberString);
                if (i % 2 != 0) {
                    if (number > median) {
                        minOrdered.add(number);
                        median = minOrdered.poll();
                    } else {
                        maxOrdered.add(number);
                        median = maxOrdered.poll();
                    }
                } else {
                    if (number > median) {
                        minOrdered.add(number);
                        maxOrdered.add(median);
                    } else {
                        maxOrdered.add(number);
                        minOrdered.add(median);
                    }
                    median = (minOrdered.peek() + maxOrdered.peek()) / 2;
                }
                i++;
                printWriter.write(median + "\n");
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
