package ru.mail.polis.ads.tasks1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sequence = br.readLine();
        System.out.println(checkSequence(sequence) ? "YES" : "NO");
    }

    private static boolean checkSequence(String sequence) {
        int counter = 0;
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == '(')
                counter++;
            else {
                counter--;
                if (counter < 0) break;
            }
        }
        return counter == 0;
    }
}
