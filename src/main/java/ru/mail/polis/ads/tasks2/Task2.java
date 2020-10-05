package ru.mail.polis.ads.tasks2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task2 {
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int numberOfElements = scanner.nextInt();
        int[] array = new int[numberOfElements];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        customMergeSort(array);
        StringBuilder sb = new StringBuilder();
        for (int number : array) {
            sb.append(number).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static void customMergeSort(int[] array) {
        if (array.length < 2) {
            return;
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
        customMergeSort(left);
        customMergeSort(right);
        merge(array, left, right);
    }

    private static void merge(int[] targetArray, int[] array1, int[] array2) {
        int arr1Index = 0;
        int arr2Index = 0;
        int targetIndex = 0;

        while (arr1Index < array1.length && arr2Index < array2.length) {
            if (array1[arr1Index] % 10 < array2[arr2Index] % 10) {
                targetArray[targetIndex] = array1[arr1Index];
                arr1Index++;
            } else if (array1[arr1Index] % 10 > array2[arr2Index] % 10) {
                targetArray[targetIndex] = array2[arr2Index];
                arr2Index++;
            } else if (array1[arr1Index] % 10 == array2[arr2Index] % 10) {
                if (array1[arr1Index] <= array2[arr2Index]) {
                    targetArray[targetIndex] = array1[arr1Index];
                    arr1Index++;
                } else {
                    targetArray[targetIndex] = array2[arr2Index];
                    arr2Index++;
                }
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
