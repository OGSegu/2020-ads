package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class HashMapNode implements HashTable<String, String> {

    private String[] array;

    private int size;
    private float loadFactor = 0;

    public HashMapNode() {
        this(16);
    }

    public HashMapNode(int initialCapacity) {
        this.array = new String[initialCapacity];
    }

    @Nullable
    @Override
    public String get(String key) {
        int index = hash(key);
        return array[index];
    }

    @Override
    public void put(@NotNull String key, @NotNull String value) {
        if (loadFactor > 0.75F)
            resize();
        int index = hash(key);
        if (array[index] == null)
            size++;
        array[index] = value;
        loadFactor = size / (float) array.length;
    }

    @Nullable
    @Override
    public String remove(@NotNull String key) {
        String removedElement = get(key);
        if (removedElement == null)
            return null;
        int index = hash(key);
        array[index] = null;
        size--;
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        String[] newArray = new String[size * 2];
        String[] oldArray = array.clone();
        this.array = newArray;
        for (String element : oldArray) {
            if (element == null)
                continue;
            int newIndex = hash(element);
            array[newIndex] = element;
        }
    }

    private int hash(String key) {
        return Objects.hashCode(key) % array.length;
    }

}
