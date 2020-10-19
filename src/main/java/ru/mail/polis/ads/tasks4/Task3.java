package ru.mail.polis.ads.tasks4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task3 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        short nSize = (short) in.nextInt();
        short[] nSequence = new short[nSize];
        for (short i = 0; i < nSize; i++)
            nSequence[i] = (short) in.nextInt();
        short mSize = (short) in.nextInt();
        short[] mSequence = new short[mSize];
        for (short i = 0; i < mSize; i++)
            mSequence[i] = (short) in.nextInt();
        short[][] dynamicArray = new short[nSize + 1][mSize + 1];
        for (short i = 1; i <= nSize; i++) {
            for (short j = 1; j <= mSize; j++) {
                if (nSequence[i - 1] == mSequence[j - 1]) {
                    dynamicArray[i][j] = (short) (dynamicArray[i - 1][j - 1] + 1);
                } else {
                    dynamicArray[i][j] = (short) Math.max(dynamicArray[i - 1][j], dynamicArray[i][j - 1]);
                }
            }
        }
        try (PrintWriter out = new PrintWriter(System.out)) {
            out.write(String.valueOf(dynamicArray[nSize][mSize]));
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
