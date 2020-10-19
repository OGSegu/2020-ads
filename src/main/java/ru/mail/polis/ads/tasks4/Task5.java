package ru.mail.polis.ads.tasks4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task5 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int size = in.nextInt();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++)
            numbers[i] = in.nextInt();
        try (PrintWriter out = new PrintWriter(System.out)) {
            out.write(String.valueOf(countInv(numbers)));
        }
    }

    public static int countInv(int[] array) {
        if (array.length < 2) {
            return 0;
        }
        int middle = array.length / 2;
        int[] left = new int[middle];
        int[] right = new int[array.length - middle];

        for (int i = 0; i < middle; i++) {
            left[i] = array[i];
        }
        for (int i = middle; i < array.length; i++) {
            right[i - middle] = array[i];
        }
        return countInv(left) + countInv(right) + merge(array, left, right);
    }

    private static int merge(int[] targetArray, int[] array1, int[] array2) {
        int arr1Index = 0;
        int arr2Index = 0;
        int targetIndex = 0;
        int invCounter = 0;

        while (arr1Index < array1.length && arr2Index < array2.length) {
            if (array1[arr1Index] <= array2[arr2Index]) {
                targetArray[targetIndex] = array1[arr1Index];
                arr1Index++;
            } else {
                targetArray[targetIndex] = array2[arr2Index];
                arr2Index++;
                invCounter += array1.length - arr1Index;
            }
            targetIndex++;
        }
        while (arr1Index < array1.length) {
            targetArray[targetIndex] = array1[arr1Index];
            arr1Index++;
            targetIndex++;
        }
        while (arr2Index < array2.length) {
            targetArray[targetIndex] = array2[arr2Index];
            arr2Index++;
            targetIndex++;
        }
        return invCounter;
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
