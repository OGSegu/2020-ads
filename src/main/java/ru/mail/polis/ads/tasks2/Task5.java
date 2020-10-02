package ru.mail.polis.ads.tasks2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        int numberOfElements = Integer.parseInt(br.readLine());
        int[] numbers = new int[numberOfElements];
        Map<Integer, LinkedList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < numberOfElements; i++) {
            String[] stringNumbers = br.readLine().split(" ");
            numbers[i] = Integer.parseInt(stringNumbers[0]);
            int secondNumber = Integer.parseInt(stringNumbers[1]);
            LinkedList<Integer> queue = hashMap.get(numbers[i]);
            if (queue == null) {
                hashMap.put(numbers[i], new LinkedList<>(Collections.singletonList(secondNumber)));
            } else {
                queue.add(secondNumber);
            }
        }
        mergeSort(numbers);
        StringBuilder sb = new StringBuilder();
        for (int key : numbers) {
            LinkedList<Integer> queue = hashMap.get(key);
            while (queue.size() != 0) {
                sb.append(key).append(" ").append(queue.poll()).append("\n");
            }
        }
        System.out.println(sb.toString());
    }


    public static void mergeSort(int[] array) {
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
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    private static void merge(int[] targetArray, int[] array1, int[] array2) {
        int arr1Index = 0;
        int arr2Index = 0;
        int targetIndex = 0;

        while (arr1Index < array1.length && arr2Index < array2.length) {
            if (array1[arr1Index] <= array2[arr2Index]) {
                targetArray[targetIndex] = array1[arr1Index];
                arr1Index++;
            } else {
                targetArray[targetIndex] = array2[arr2Index];
                arr2Index++;
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
}
