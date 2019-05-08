package io.github.thinkframework.swing.control.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class GeneratorTree extends JTree implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private GeneratorTreeModel rootTreeModel = new GeneratorTreeModel(null);
    private GeneratorTreeNode rootMutableTreeNode = new GeneratorTreeNode("Databases");

    private ApplicationContext applicationContext;

    public GeneratorTree(){
        super();
        setModel(rootTreeModel);
        rootTreeModel.setRoot(rootMutableTreeNode);
        setComponentPopupMenu(addJPopuMenu());
        expandPath(new TreePath(rootMutableTreeNode));

        addTreeSelectionListener(e ->{
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
                    logger.error("",ex);
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
                TreePath treePath = treePaths[i];
                int count =  treePath.getPathCount();
                for(int j=1;j<count;j++){
                    if(j != 3){
                        continue;
                    }
                    DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) treePath
                            .getPathComponent(j);
                    String dataSourceName = (String)((DefaultMutableTreeNode)defaultMutableTreeNode.getParent().getParent()).getUserObject();
                    String tableName = (String) defaultMutableTreeNode.getUserObject();
                    String type = (String)((DefaultMutableTreeNode)defaultMutableTreeNode.getParent()).getUserObject();

                    //TODO 和生成器解耦,bean的名字写死了，之后优化掉
//                    applicationContext.getBean(Generator.class).dataSourceName(dataSourceName).tableName(tableName).generate();
                    Object generator = applicationContext.getBean("generator");
                    generator.getClass().getMethod("dataSourceName",String.class).invoke(generator,dataSourceName);
                    generator.getClass().getMethod("tableName",String.class).invoke(generator,tableName);
                    generator.getClass().getMethod("generate").invoke(generator);
                }
            }
            int confirm = JOptionPane.showConfirmDialog(GeneratorTree.this, "操作成功,是否打开输出目录?","提示",JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
                openDirectory();
            }
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex){
            logger.error("调用生成器异常",ex);
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


    private String[] getDataSourceNames() {
        return applicationContext.getBeanNamesForType(DataSource.class);
    }

    protected DataSource getDataSource(String id){
        return applicationContext.getBean(id,DataSource.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
