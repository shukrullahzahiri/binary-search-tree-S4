
package com.keyin.binarysearchtree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Service
public class BinarySearchTreeService {

    private static final Logger logger = LoggerFactory.getLogger(BinarySearchTreeService.class);

    private final BinarySearchTree bst;
    private final TreeDataRepository treeDataRepository;

    @Autowired
    public BinarySearchTreeService(BinarySearchTree bst, TreeDataRepository treeDataRepository) {
        this.bst = bst;
        this.treeDataRepository = treeDataRepository;
    }


    public boolean insert(int value) {
        try {
            bst.insert(value);
            logger.info("Inserted value: {}, Current tree structure: {}", value, bst.toJson());
            return true;
        } catch (Exception e) {
            logger.error("Error inserting value: {}", value, e);
            return false;
        }
    }

    public String toJson() {
        try {
            String json = bst.toJson();
            if (json == null || json.isEmpty()) {
                logger.warn("Empty or null JSON returned from bst.toJson()");
            }
            return json;
        } catch (Exception e) {
            logger.error("Error converting tree to JSON", e);
            return null;
        }
    }


    public void balanceBST(BinarySearchTree.TreeNode root) {
        try {
            bst.balance();
            logger.info("Tree balanced successfully. Current tree structure: {}", bst.toJson());
        } catch (Exception e) {
            logger.error("Error balancing the tree", e);
        }
    }


    public void saveTreeStructure(BinarySearchTree.TreeNode root) {
        if (root == null) {
            logger.warn("Tree root is null. Skipping save operation.");
            return;
        }
        try {
            String treeJson = bst.toJson();
            if (treeJson == null || treeJson.trim().isEmpty()) {
                logger.warn("Tree JSON is null or empty. Skipping save operation.");
                return;
            }
            TreeData treeData = new TreeData();
            treeData.setTreeStructure(treeJson);
            treeDataRepository.save(treeData);
            logger.info("Tree structure saved to the database successfully.");
        } catch (Exception e) {
            logger.error("Error saving tree structure to the database", e);
        }
    }


    public List<TreeData> getAllTrees() {
        try {
            List<TreeData> trees = treeDataRepository.findAll();

            // Log if no trees are found
            if (trees.isEmpty()) {
                logger.warn("No tree structures found in the database.");
            }

            return trees;
        } catch (Exception e) {
            logger.error("Error retrieving tree structures from the database", e);
            return Collections.emptyList();  // Return an empty list on error
        }
    }


    public BinarySearchTree.TreeNode insertNumbersIntoBST(List<Integer> numberList) {
        BinarySearchTree.TreeNode root = null;
        for (int number : numberList) {
            bst.insert(number);
        }
        return bst.getRoot();
    }
}






