package ru.mail.polis.ads.tasks2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        int numberOfElements = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        String[] numbersString = br.readLine().split(" ");
        br.close();
        for (String s : numbersString) {
            int number = Integer.parseInt(s);
            treeMap.merge(number, 1, Integer::sum);
        }
        Integer[] keys = treeMap.keySet().toArray(new Integer[0]);
        StringBuilder sb = new StringBuilder();
        for (int key : keys) {
            int amount = treeMap.get(key);
            for (int i = 0; i < amount; i++) {
                sb.append(key).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

}
