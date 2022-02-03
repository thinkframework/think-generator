package io.github.thinkframework.swing.core.component.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lixiaobin on 2017/4/26.
 */
public class GeneratorTreeModel implements TreeModel, ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(GeneratorTreeModel.class);

    protected EventListenerList listenerList = new EventListenerList();

    private ApplicationContext applicationContext;

    private TreeNode root = new GeneratorTreeNode("dataSources");

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((TreeNode) parent).getChildAt(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((TreeNode) parent).getChildCount();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((TreeNode) node).isLeaf();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((TreeNode) parent).getIndex((TreeNode) child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listenerList.add(TreeModelListener.class, l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listenerList.remove(TreeModelListener.class, l);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public class GeneratorTreeNode implements TreeNode {

        private String name;

        private GeneratorTreeNode parent;

        private Vector<GeneratorTreeNode> children = new Vector<>();

        private boolean leaf;

        private String columnLabel;

        public GeneratorTreeNode(String name) {
            this.name = name;
        }

        public GeneratorTreeNode(String name, String columnLabel) {
            this.name = name;
            this.columnLabel = columnLabel;
        }

        public GeneratorTreeNode(String name, GeneratorTreeNode parent, String columnLabel) {
            this.name = name;
            this.parent = parent;
            this.columnLabel = columnLabel;
        }

        public GeneratorTreeNode(String name, GeneratorTreeNode parent, boolean leaf, String columnLabel) {
            this.name = name;
            this.parent = parent;
            this.leaf = leaf;
            this.columnLabel = columnLabel;
        }

        @Override
        public GeneratorTreeNode getChildAt(int childIndex) {
            return getChildren().get(childIndex);
        }

        @Override
        public int getChildCount() {
            return getChildren().size();
        }

        @Override
        public GeneratorTreeNode getParent() {
            return parent;
        }

        @Override
        public int getIndex(TreeNode node) {
            return getChildren().indexOf(node);
        }

        @Override
        public boolean getAllowsChildren() {
            return true;
        }

        @Override
        public boolean isLeaf() {
            return leaf;
        }

        @Override
        public Enumeration children() {
            return getChildren().elements();
        }

        protected Vector<GeneratorTreeNode> getChildren() {
            if (this.children.isEmpty()) {
                Vector<GeneratorTreeNode> vector = new Vector<>();
                if (columnLabel == null) {//获取所有数据源
                    vector.addAll(Stream.of(applicationContext.getBeanNamesForType(DataSource.class))
                        .map(value -> new GeneratorTreeNode(value, ""))
                        .collect(Collectors.toCollection(Vector::new)));
                } else {
                    if ("".equals(columnLabel)) {//获取相关的表类型
                        String dataSourceName = name;
                        try (Connection connection = applicationContext.getBean(dataSourceName, DataSource.class).getConnection()) {
                            ResultSet rs = connection.getMetaData().getTableTypes();
                            while (rs.next()) {
                                vector.add(new GeneratorTreeNode(rs.getString("TABLE_TYPE"), this, "TABLE_TYPE"));
                            }
                        } catch (SQLException ex) {
                            log.error("", ex);
                        }
                    } else if ("TABLE_TYPE".equals(columnLabel)) {//获取相关的表名称
                        String dataSourceName = (getParent()).getName();
                        try (Connection connection = applicationContext.getBean(dataSourceName, DataSource.class).getConnection()) {
                            String schema = null;
                            try {
                                schema = connection.getSchema();
                            } catch (SQLException e) {
                                log.error("{}", e.getClass().getName());
                            }
                            ResultSet rs = connection.getMetaData().getTables(connection.getCatalog(), schema, "%", new String[]{name});
                            while (rs.next()) {
                                vector.add(new GeneratorTreeNode(rs.getString("TABLE_NAME"), this, true, "TABLE_NAME"));
                            }
                        } catch (SQLException ex) {
                            log.error("", ex);
                        }
                    }
                }
                this.children.addAll(vector);
            }
            return children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getUserObject() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

    }
}
