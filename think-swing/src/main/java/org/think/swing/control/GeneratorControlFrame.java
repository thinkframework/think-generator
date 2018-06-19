package org.think.swing.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.think.swing.GeneratorContext;
import org.think.swing.config.XmlUtil;
import org.think.swing.control.tree.GeneratorTreePanel;
import org.think.swing.jdbc.GeneratorQueryPanel;
import org.think.swing.jdbc.GenericTableTabbedPanel;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 
 * @author hdhxby
 * @email hdhxby@qq.com
 */
public class GeneratorControlFrame extends JInternalFrame{
	private int count = 1;
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(getClass());
	private final Integer WIDTH=800,HEIGHT=600;
	GeneratorTreePanel generatorTreePanel;
	private JComboBox connectionComboBox;
	private JTabbedPane centerTabbedPane = new JTabbedPane(JTabbedPane.TOP);;


	public GeneratorControlFrame(){
		setTitle("Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭
//		setSize(WIDTH,HEIGHT);// 设置大小
		setClosable(true);
		setResizable(true);//改变大小
		setMaximizable(true);
		setIconifiable(true);
//		setLocationByPlatform(true);
		add(getToolBar(), BorderLayout.NORTH);
		add(addCenterPanel());

	}

	/**
	 * 初始化ToolBar
	 * @return
	 */
	public JToolBar getToolBar(){
		JToolBar toolBar = new JToolBar();
		JButton openButton = new JButton( new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
//				putValue(Action.NAME, "打开目录");
				putValue(Action.SHORT_DESCRIPTION, "打开目录");
				putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("general/openProject.png")));
			}
			public void actionPerformed(ActionEvent e) {
				openDirectory();
			}
		});
		toolBar.add(openButton);
		toolBar.addSeparator();

		connectionComboBox = new JComboBox();
		for(String dataSourceName : getDataSourceNames()){
			connectionComboBox.addItem(dataSourceName);
		}
		toolBar.add(connectionComboBox);

		toolBar.add(new JButton( new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
//				putValue(Action.NAME, "新建查询");
				putValue(Action.SHORT_DESCRIPTION, "查询");
				putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("runConfigurations/ql_console.png")));
			}
			public void actionPerformed(ActionEvent e) {
				String dataSourceName = connectionComboBox.getSelectedItem().toString();
				JPanel sqlTable =  addSqlTable(dataSourceName);
				centerTabbedPane.addTab(dataSourceName+ count++, null, sqlTable, null);

				centerTabbedPane.setSelectedComponent(sqlTable);
			}
		}));
		toolBar.addSeparator();
		return toolBar;
	}

	public JSplitPane addCenterPanel(){
		JSplitPane jSplitPane =new JSplitPane();
		jSplitPane.setLeftComponent(addLeftCenterPanel());
		jSplitPane.setRightComponent(addRightCenterPanel());
//		jSplitPane.setBottomComponent(new JPanel());
		jSplitPane.setOneTouchExpandable(true);
		jSplitPane.setContinuousLayout(true);
		return jSplitPane;
	}

	public JTabbedPane addLeftCenterPanel(){
		JTabbedPane westTabbedPane = new JTabbedPane();
		initTree(westTabbedPane);
		return westTabbedPane;
	}
	
	public void initTree(final JTabbedPane westTabbedPane){
		generatorTreePanel = new GeneratorTreePanel();
		westTabbedPane.addTab("表信息", null, new JScrollPane(generatorTreePanel), null);

		generatorTreePanel.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				try {
					TreePath treePath = generatorTreePanel.getJTree().getSelectionPath();
					DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) treePath
							.getLastPathComponent();
					int pathCount = treePath.getPathCount();
					if(pathCount == 4){//获取相关的列
						DefaultMutableTreeNode dataSourceNameNode = (DefaultMutableTreeNode)defaultMutableTreeNode.getParent().getParent();
						String dataSourceName = (String)dataSourceNameNode.getUserObject();
						String tableName = (String) defaultMutableTreeNode.getUserObject();
						addTablePanel(dataSourceName,tableName);
					}
				} catch (Exception ex) {
					log.error("",ex);
					JOptionPane.showMessageDialog(GeneratorControlFrame.this, ex.getMessage());
				}
			}

		});

	}

	public JTabbedPane addRightCenterPanel(){
		add(centerTabbedPane, BorderLayout.CENTER);
		centerTabbedPane.addTab("起始页", null, initFirstTable(), null);
		return centerTabbedPane;
	}

	private JEditorPane initFirstTable(){
		JEditorPane editorPane = new JEditorPane();
		try {
			URL url = getClass().getClassLoader().getResource("help.html");
			editorPane.setPage(url);
		} catch (IOException e) {
			log.error("",e);
		}
		return editorPane;
	}

	private JTabbedPane addTablePanel(String dataSourceName,String tableName){
		int count = centerTabbedPane.getTabCount();
		boolean exists = false;
		for(int i=0;i<count;i++){
			if(centerTabbedPane.getTitleAt(i).equals(tableName)){
				centerTabbedPane.setSelectedIndex(i);
				exists = true;
				break;
			}
		}
		if(!exists){
			GenericTableTabbedPanel genericTabbedPanel = new GenericTableTabbedPanel(getDataSource(dataSourceName),tableName);
			centerTabbedPane.addTab(tableName, null, genericTabbedPanel, null);
			centerTabbedPane.setSelectedComponent(genericTabbedPanel);
		}
		return centerTabbedPane;
	}

	private JPanel addSqlTable(String dataSrouceName){
		return new GeneratorQueryPanel(getDataSource(dataSrouceName));
	}


	private List<String> getDataSourceNames() {
		return new XmlUtil().getDataSourceNames();
	}

	protected DataSource getDataSource(String id){
		return GeneratorContext.getInstance().getDataSource(id);
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
			log.error("",e);
		}
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);

	}
}
