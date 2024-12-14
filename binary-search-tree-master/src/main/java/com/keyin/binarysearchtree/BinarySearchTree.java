package com.keyin.binarysearchtree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BinarySearchTree {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    static class TreeNode {
        private final int value;
        private TreeNode left, right;

        TreeNode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }
    }


    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }


    public void balance() {
        root = balanceTree(toSortedArray(root));
    }

    private TreeNode balanceTree(int[] sortedArray) {
        return buildTree(sortedArray, 0, sortedArray.length - 1);
    }

    private TreeNode buildTree(int[] array, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(array[mid]);
        node.left = buildTree(array, start, mid - 1);
        node.right = buildTree(array, mid + 1, end);
        return node;
    }

    private int[] toSortedArray(TreeNode root) {
        return inOrderTraversal(root).stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> inOrderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        result.addAll(inOrderTraversal(root.left));
        result.add(root.value);
        result.addAll(inOrderTraversal(root.right));
        return result;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}

