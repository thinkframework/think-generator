package io.github.thinkframework.swing.control.tree;

import io.github.thinkframework.generator.GeneratorFacade;
import io.github.thinkframework.swing.GeneratorContext;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorTree extends JTree {
    private GeneratorTreeModel rootTreeModel = new GeneratorTreeModel(null);
    private GeneratorTreeNode rootMutableTreeNode = new GeneratorTreeNode("Databases");
    public GeneratorTree(){
        super();
        setModel(rootTreeModel);
        rootTreeModel.setRoot(rootMutableTreeNode);
        setComponentPopupMenu(addJPopuMenu());
        expandPath(new TreePath(rootMutableTreeNode));
        setPreferredSize(new Dimension(240,8192));//宽度

        addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                Connection connection=null;
                try {
                    TreePath treePath = getSelectionPath();
                    DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) treePath
                            .getLastPathComponent();
                    int pathCount = treePath.getPathCount();
                    if(pathCount == 1){//获取所有数据源
                        for(String dataSourceName: getDataSourceNames()) {
							rootMutableTreeNode.add(new DefaultMutableTreeNode(dataSourceName));
                        }
                    }else if(pathCount == 2){//获取相关的表
                        String dataSourceName = (String)defaultMutableTreeNode.getUserObject();
                        connection = getDataSource(dataSourceName).getConnection();
                        ResultSet rs = connection.getMetaData().getTableTypes();
                        while(rs.next()){
                            defaultMutableTreeNode.add(new DefaultMutableTreeNode(rs.getString("TABLE_TYPE")));
                        }
                    }else if(pathCount == 3){//获取相关的表
                        String tableType = (String)defaultMutableTreeNode.getUserObject();
                        DefaultMutableTreeNode dataSourceNameNode = (DefaultMutableTreeNode)defaultMutableTreeNode.getParent();
                        String dataSourceName = (String)dataSourceNameNode.getUserObject();
                        connection = getDataSource(dataSourceName).getConnection();
                        //FIXME schema可能不可用
                        ResultSet rs = connection.getMetaData().getTables(null,null,"%",new String[]{tableType});
                        while(rs.next()){
                            defaultMutableTreeNode.add(new DefaultMutableTreeNode(rs.getString("TABLE_NAME")));
                        }
                    }
//                    else if(pathCount == 4){//获取相关的列
//                        DefaultMutableTreeNode dataSourceNameNode = (DefaultMutableTreeNode)defaultMutableTreeNode.getParent().getParent();
//                        String dataSourceName = (String)dataSourceNameNode.getUserObject();
//                        String tableName = (String) defaultMutableTreeNode.getUserObject();
//                        addTablePanel(dataSourceName,tableName);
//                    }
                } catch (Exception ex) {
//                    log.error("",ex);
                } finally {
                    if(connection != null){
                        if(connection != null){
                            try {
                                connection.close();
                            } catch (SQLException ex) {
//                                log.error(ex);
                            }
                        }
                    }
                }
            }

        });

    }

    public JPopupMenu addJPopuMenu(){
        JPopupMenu jPopupMenu = new JPopupMenu();
        jPopupMenu.add(new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "生成文件");
//				putValue(Action.SHORT_DESCRIPTION, "生成文件");
//				putValue(Action.SMALL_ICON, menu_open);
            }
            public void actionPerformed(ActionEvent e) {
                generatorTable();
            }
        }));
        return jPopupMenu;
    }



    public void generatorTable(){
        TreePath[] treePaths = getSelectionPaths();
        try{
            if(treePaths == null){
                return;
            }
            for (int i=0;i<treePaths.length;i++){
                TreePath treePath = (TreePath) treePaths[i];
                int count =  treePath.getPathCount();
                for(int j=1;j<count;j++){
                    if(j != 3){
                        continue;
                    }
                    DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) treePath
                            .getPathComponent(j);
                    String tableName = (String) defaultMutableTreeNode.getUserObject();
//                    log.debug(tableName);
                    String type = (String)((DefaultMutableTreeNode)defaultMutableTreeNode.getParent()).getUserObject();
                    String id = (String)((DefaultMutableTreeNode)defaultMutableTreeNode.getParent().getParent()).getUserObject();
                    DataSource dataSource = GeneratorContext.getInstance().getDataSource(id);
                    new GeneratorFacade().generator(dataSource,tableName);
                }
            }
            int confirm = JOptionPane.showConfirmDialog(GeneratorTree.this, "操作成功,是否打开输出目录?","提示",JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
                openDirectory();
            }
        }catch (Exception ex){
//            log.error("",ex);
            ex.printStackTrace();
        }
    }

    private void openDirectory(){
        String osName = System.getProperty("os.name");
        try {
            if("Mac OS X".equals(osName)){
                Runtime.getRuntime().exec("open .");
            }else {
                Runtime.getRuntime().exec("cmd /c start .");
            }
        } catch (IOException e) {
//            log.error("",e);
        }
    }


    private java.util.List<String> getDataSourceNames() {
        return GeneratorContext.getInstance().getDataSourceNames();
    }

    protected DataSource getDataSource(String id){
        return GeneratorContext.getInstance().getDataSource(id);
    }
}
