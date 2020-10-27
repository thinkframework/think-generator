package io.github.thinkframework.generator.core.design.composite;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FileNodeTest {
    @Test
    public void test(){
        FileNode parentNode = new FileNode();
        parentNode.setFile(new File("a"));
        FileNode fileNode = new FileNode();
        fileNode.setFile(new File("b"));
        fileNode.setParent(parentNode);
        parentNode.getChildren().add(fileNode);
        FileNode childNode = new FileNode();
        childNode.setFile(new File("c"));
        childNode.setParent(fileNode);
        fileNode.getChildren().add(childNode);
        System.out.println(parentNode);
        Assert.assertEquals(parentNode.toString(),
    "a\n" +
            "\tb\n" +
            "\t\tc\n");
    }
}
