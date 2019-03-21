package io.github.thinkframework.swing.control.tree;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 * Created by lixiaobin on 2017/4/26.
 */
public class GeneratorTreeModel extends DefaultTreeModel{
    public GeneratorTreeModel(TreeNode root) {
        super(root);
    }

    public GeneratorTreeModel(TreeNode root, boolean asksAllowsChildren) {
        super(root, asksAllowsChildren);
    }


}
