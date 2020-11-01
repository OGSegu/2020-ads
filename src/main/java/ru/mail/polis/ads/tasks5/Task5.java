package ru.mail.polis.ads.tasks5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Task5 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int[] numbers = new int[n];
        // Заполняем числа n = 3 -> 1,2,3
        for (int i = 1; i <= n; i++) {
            numbers[i - 1] = i;
        }
        int i = n;
        try (PrintWriter out = new PrintWriter(System.out)) {
            while (true) {
                Arrays.stream(numbers).forEach(e -> out.write(e + " "));
                out.write("\n");
                List<Integer> tailIndex = new ArrayList<>();
                // Заполнение индексами хвоста
                do {
                    tailIndex.add(i - 1);
                    i--;
                    if (i == 0) break;
                } while (numbers[i - 1] > numbers[i]);
                if (i == 0) break;
                int changeElementIndex = i - 1;
                // Поиск минимума для changeElement
                int min = Integer.MAX_VALUE;
                int minIndex = changeElementIndex;
                for (int index : tailIndex) {
                    if (numbers[index] < min && numbers[index] > numbers[changeElementIndex]) {
                        min = numbers[minIndex];
                        minIndex = index;
                    }
                }
                // Замена элемента
                int temp = numbers[changeElementIndex];
                numbers[changeElementIndex] = numbers[minIndex];
                numbers[minIndex] = temp;


                // Разворот хвоста
                int indexDescending = n - 1;
                int indexIncrease = changeElementIndex + 1;
                while (indexDescending > indexIncrease) {
                    int temporary = numbers[indexDescending];
                    numbers[indexDescending] = numbers[indexIncrease];
                    numbers[indexIncrease] = temporary;
                    indexIncrease++;
                    indexDescending--;
                }
                i = n;
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
