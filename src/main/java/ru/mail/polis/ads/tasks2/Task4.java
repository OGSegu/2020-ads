package ru.mail.polis.ads.tasks2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        int kMax = Integer.parseInt(br.readLine());
        BigInteger[] numbers = Arrays.stream(br.readLine().split(" ")).map(BigInteger::new).toArray(BigInteger[]::new);
        kMax = numbers.length - kMax;
        System.out.print(findMaxK(numbers, kMax));
    }

    public static BigInteger findMaxK(BigInteger[] array, int k) {
        int left = 0;
        int right = array.length;
        while (true) {
            int mid = partition(array, left, right);
            if (mid == k) {
                return array[mid];
            }
            if (k < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    private static int partition(BigInteger[] array, int l, int r) {
        BigInteger element = array[l];
        int j = l;
        for (int i = l + 1; i < r; i++) {
            if (array[i].compareTo(element) < 0) {
                j++;
                BigInteger temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        BigInteger temp = array[j];
        array[j] = array[l];
        array[l] = temp;
        return j;
    }
}
