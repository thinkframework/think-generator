package io.github.thinkframework.generator.core.design.composite;

import java.io.File;
import java.util.Collection;
import java.util.Set;

public interface TreeNode {

    public TreeNode getParent();
    public Collection<? extends TreeNode> getChildren();

}
