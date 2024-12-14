
package com.keyin.binarysearchtree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BinarySearchTreeServiceTest {

    @InjectMocks
    private BinarySearchTreeService binarySearchTreeService;

    @Mock
    private BinarySearchTree binarySearchTree;

    @Mock
    private TreeDataRepository treeDataRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsert_Success() {
        int value = 10;

        doNothing().when(binarySearchTree).insert(value);

        boolean result = binarySearchTreeService.insert(value);

        assertTrue(result, "Insert should return true for successful insertion");
        verify(binarySearchTree, times(1)).insert(value);
    }

    @Test
    public void testInsert_Exception() {
        int value = 10;

        doThrow(new RuntimeException("Insertion error")).when(binarySearchTree).insert(value);

        boolean result = binarySearchTreeService.insert(value);

        assertFalse(result, "Insert should return false if an exception occurs");
        verify(binarySearchTree, times(1)).insert(value);
    }

    @Test
    public void testToJson_Success() {
        String expectedJson = "{\"value\":10,\"left\":null,\"right\":null}";

        when(binarySearchTree.toJson()).thenReturn(expectedJson);

        String actualJson = binarySearchTreeService.toJson();

        assertEquals(expectedJson, actualJson, "toJson should return the correct JSON representation");
        verify(binarySearchTree, times(1)).toJson();
    }

    @Test
    public void testToJson_Exception() {
        when(binarySearchTree.toJson()).thenThrow(new RuntimeException("JSON error"));

        String actualJson = binarySearchTreeService.toJson();

        assertNull(actualJson, "toJson should return null if an exception occurs");
        verify(binarySearchTree, times(1)).toJson();
    }

    @Test
    public void testBalanceBST() {
        doNothing().when(binarySearchTree).balance();

        BinarySearchTree.TreeNode root = null;
        binarySearchTreeService.balanceBST(root);

        verify(binarySearchTree, times(1)).balance();
    }

}

