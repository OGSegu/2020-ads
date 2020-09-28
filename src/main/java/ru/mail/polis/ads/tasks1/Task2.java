package ru.mail.polis.ads.tasks1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class Task2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int amount = Integer.parseInt(br.readLine());
        BinaryTree binaryTree = new BinaryTree();
        Stack<Node> queue = new Stack<>();
        for (int j = 0; j < amount; j++) {
            String input = br.readLine();
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                if (Character.isLowerCase(ch)) {
                    queue.push(new Node(ch));
                } else {
                    Node left = queue.pop();
                    Node right = queue.pop();
                    Node node = new Node(ch, left, right);
                    if (i == input.length() - 1) {
                        binaryTree.root = node;
                    }
                    queue.push(node);
                }
            }
            System.out.println(binaryTree.traverseLevelOrder());
        }
    }
}

class Node {
    char value;
    Node left;
    Node right;

    Node(char value) {
        this.value = value;
        right = null;
        left = null;
    }

    Node(char value, Node left, Node right) {
        this.value = value;
        this.right = right;
        this.left = left;
    }
}

class BinaryTree {

    Node root;

    public BinaryTree() {
    }

    public String traverseLevelOrder() {
        if (root == null) {
            return "";
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        StringBuilder sb = new StringBuilder();
        while (!nodes.isEmpty()) {

            Node node = nodes.remove();

            sb.append(node.value);

            if (node.right != null) {
                nodes.add(node.right);
            }

            if (node.left != null) {
                nodes.add(node.left);
            }
        }
        return sb.reverse().toString();
    }
}
