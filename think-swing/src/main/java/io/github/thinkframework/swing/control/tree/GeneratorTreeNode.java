package io.github.thinkframework.swing.control.tree;

import io.github.thinkframework.swing.GeneratorContext;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

/**
 * Created by lixiaobin on 2017/4/26.
 */
public class GeneratorTreeNode extends DefaultMutableTreeNode{

    public GeneratorTreeNode() {
        super();
    }

    public GeneratorTreeNode(Object userObject) {
        super(userObject);
    }

    public GeneratorTreeNode(Object userObject, boolean asksAllowsChildren) {
        super(userObject, asksAllowsChildren);
    }

    protected void addNode(){
        for(String dataSourceName : getDataSourceNames()){
            add(new GeneratorTreeNode(dataSourceName));
        }
    }
    private List<String> getDataSourceNames() {
        return GeneratorContext.getInstance().getDataSourceNames();
    }
}
