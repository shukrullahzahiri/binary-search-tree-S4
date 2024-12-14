
package com.keyin.binarysearchtree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class TreeController {

    private static final Logger logger = LoggerFactory.getLogger(TreeController.class);

    @Autowired
    private BinarySearchTreeService binarySearchTreeService;

    private final ObjectMapper objectMapper = new ObjectMapper();



    @PostMapping("/enter-numbers")
    public ResponseEntity<String> enterNumbers(@RequestBody Map<String, String> requestBody) {
        String numbers = requestBody.get("numbers");  // Extract the "numbers" value
        logger.info("Processing form input: {}", numbers);

        if (numbers == null || numbers.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Input numbers are required.");
        }

        try {
            // Split and parse the numbers into a list of integers
            List<Integer> numberList = Stream.of(numbers.split(","))
                    .map(String::trim)  // Remove extra spaces
                    .map(Integer::parseInt)  // Convert to Integer
                    .collect(Collectors.toList());

            // Insert numbers into the binary search tree
            BinarySearchTree.TreeNode root = binarySearchTreeService.insertNumbersIntoBST(numberList);

            // Balance the binary search tree and save it
            binarySearchTreeService.balanceBST(root);
            binarySearchTreeService.saveTreeStructure(root);

            return ResponseEntity.ok("Numbers processed and tree balanced successfully!");
        } catch (NumberFormatException e) {
            logger.error("Invalid number format: {}", numbers, e);
            return ResponseEntity.badRequest().body("Invalid number format: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error processing numbers: {}", numbers, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing numbers: " + e.getMessage());
        }
    }

    @GetMapping("/display-balanced-tree")
    public ResponseEntity<String> displayBalancedTree() {
        String treeStructureJson = binarySearchTreeService.toJson();
        return ResponseEntity.ok(treeStructureJson);
    }


    @GetMapping("/previous-trees")
    public ResponseEntity<List<Map<String, Object>>> getPreviousTrees() {
        logger.info("Retrieving previous trees from the database.");

        // Retrieve all trees
        List<TreeData> previousTrees = binarySearchTreeService.getAllTrees();

        if (previousTrees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        }

        List<Map<String, Object>> trees = new ArrayList<>();

        for (TreeData treeData : previousTrees) {
            try {
                // Deserialize the tree structure into a Map for each tree
                Map<String, Object> treeStructure = objectMapper.readValue(treeData.getTreeStructure(), Map.class);
                trees.add(treeStructure);
            } catch (JsonProcessingException e) {
                logger.error("Error parsing tree structure for tree ID: {}", treeData.getId(), e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
            }
        }

        return ResponseEntity.ok(trees);
    }
}

