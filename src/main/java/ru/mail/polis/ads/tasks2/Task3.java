package ru.mail.polis.ads.tasks2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        int numberOfElements = Integer.parseInt(br.readLine());
        int[] array = new int[numberOfElements];
        String[] numbersString = br.readLine().split(" ");
        for (int i = 0; i < numberOfElements; i++) {
            array[i] = Integer.parseInt(numbersString[i]);
        }
        System.out.print(bubbleSort(array));
    }

    public static int bubbleSort(int[] intArray) {
        int counter = 0;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < intArray.length; i++) {
                if (intArray[i] < intArray[i - 1]) {
                    counter++;
                    int temp = intArray[i];
                    intArray[i] = intArray[i - 1];
                    intArray[i - 1] = temp;
                    isSorted = false;
                }
            }
        }
        return counter;
    }
}
