package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;

/**
 * Binary search tree with ordered operations support.
 */
public interface Bst<Key, Value> {
    @Nullable Value get(@NotNull Key key);

    default boolean containsKey(@NotNull Key key) {
        return get(key) != null;
    }

    void put(@NotNull Key key, @NotNull Value value);

    @Nullable Value remove(@NotNull Key key);

    @Nullable Key min();

    @Nullable Value minValue();

    @Nullable Key max();

    @Nullable Value maxValue();

    @Nullable Key floor(@NotNull Key key);

    @Nullable Key ceil(@NotNull Key key);

    default int compareTo(Key key1, Key key2) {
            if (System.identityHashCode(key1) == System.identityHashCode(key2))
                return 0;
            else
                return System.identityHashCode(key1) > System.identityHashCode(key2) ? 1 : -1;
    }
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

}
