package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) return get(x.left, key);
        if (key.compareTo(x.key) > 0) return get(x.right, key);
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return x;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Node x = get(root, key);
        if (x == null) {
            return null;
        }
        root = remove(root, key);
        size--;
        return x.value;
    }

    private Node remove(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        }
        if (key.compareTo(x.key) == 0) {
            x = innerDelete(x);
        }
        return x;
    }

    private Node innerDelete(Node x) {
        if (x.right == null) return x.left;
        if (x.left == null) return x.right;
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    @Override
    public Key min() {
        Node resultNode = min(root);
        return resultNode == null ? null : resultNode.key;
    }

    private Node min(Node x) {
        if (x == null) return null;
        return x.left == null ? x : min(x.left);
    }

    @Override
    public Value minValue() {
        Node resultNode = min(root);
        return resultNode == null ? null : resultNode.value;
    }

    @Override
    public Key max() {
        Node resultNode = max(root);
        return resultNode == null ? null : resultNode.key;
    }

    private Node max(Node x) {
        if (x == null) return null;
        return x.right == null ? x : max(x.right);
    }

    @Override
    public Value maxValue() {
        Node resultNode = max(root);
        return resultNode == null ? null : resultNode.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node x = get(root, key);
        if (x.left == null) return null;
        return floor(x.left).key;
    }

    private Node floor(Node x) {
        if (x.right == null) return x;
        return floor(x.right);
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node x = get(root, key);
        if (x.right == null) return null;
        return ceil(x.right).key;
    }

    private Node ceil(Node x) {
        if (x.left == null) return x;
        return ceil(x.left);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }
}
