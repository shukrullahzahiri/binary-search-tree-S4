
package com.keyin.binarysearchtree;

import jakarta.persistence.*;

@Entity
public class TreeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "input_numbers", columnDefinition = "TEXT")
    private String inputNumbers;

    @Column(name = "tree_structure", columnDefinition = "JSON")
    private String treeStructure;


    public TreeData() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInputNumbers() {
        return inputNumbers;
    }

    public void setInputNumbers(String inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    public String getTreeStructure() {
        return treeStructure;
    }

    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
    }

    @Override
    public String toString() {
        return "TreeData{" +
                "id=" + id +
                ", inputNumbers='" + inputNumbers + '\'' +
                ", treeStructure='" + treeStructure + '\'' +
                '}';
    }
}




