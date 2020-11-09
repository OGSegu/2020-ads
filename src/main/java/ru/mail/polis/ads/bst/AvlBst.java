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
        fixHeight(x);
        x = balance(x);
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
        return floor(root, key, null);
    }

    private Key floor(Node node, Key key, Key maxKey) {
        if (node == null) {
            return maxKey;
        }
        if (node.key == key) {
            maxKey = node.key;
        }
        if (key.compareTo(node.key) < 0) {
            maxKey = floor(node.left, key, maxKey);
        }
        if (key.compareTo(node.key) > 0) {
            maxKey = node.key;
            maxKey = floor(node.right, key, maxKey);
        }
        return maxKey;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node node = root;
        return ceil(node, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            return node.key;
        }
        if (key.compareTo(node.key) < 0) {
            return node.left == null ? node.key : ceil(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            return node.right == null ? null : ceil(node.right, key);
        }
        return node.key;
    }

    public Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }

    public int factor(Node x) {
        return height(x.left) - height(x.right);
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

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }
}
