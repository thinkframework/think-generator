package io.github.thinkframework.generator.core.design.composite;


import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileNode implements TreeNode {

    private File file;

    private FileNode parent;
    private Set<FileNode> children = new HashSet<>();

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileNode getParent() {
        return parent;
    }

    public void setParent(FileNode parent) {
        this.parent = parent;
    }

    public Set<FileNode> getChildren() {
        return children;
    }

    public void setChildren(Set<FileNode> children) {
        this.children = children;
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        for (FileNode parent = this.parent;parent != null;parent = parent.getParent()){
            string.append("\t");
        }
        return String.format("%s%s\n%s",string.toString(),file.getName(),
            children.stream().map(child ->child.toString()).reduce((a,b) -> a+b).orElse(""));
    }
}
