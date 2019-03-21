package io.github.thinkframework.swing.control;

import io.github.thinkframework.swing.config.XmlUtil;
import io.github.thinkframework.swing.other.GeneratorLogFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.thinkframework.swing.GeneratorContext;
import io.github.thinkframework.swing.control.tree.GeneratorTreePanel;
import io.github.thinkframework.swing.jdbc.GeneratorQueryPanel;
import io.github.thinkframework.swing.jdbc.GenericTableTabbedPanel;
import io.github.thinkframework.swing.other.GeneratorMavenFrame;

import javax.sql.DataSource;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 
 * @author hdhxby
 * @email hdhxby@qq.com
 */
public class GeneratorControlFrame extends JFrame{
	private int count = 1;
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final Integer WIDTH=800,HEIGHT=600;
	GeneratorTreePanel generatorTreePanel;
	private JComboBox connectionComboBox;
	private JTabbedPane centerTabbedPane = new JTabbedPane(JTabbedPane.TOP);;


	public GeneratorControlFrame(){
		setTitle("Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭
		setSize(WIDTH,HEIGHT);// 设置大小
		setResizable(true);//改变大小
//		setLocationByPlatform(true);
        setJMenuBar(addMenu());
		add(getToolBar(), BorderLayout.NORTH);
		add(addCenterPanel());

	}

    public JMenuBar addMenu(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(addFile());
        menuBar.add(addTool());
        menuBar.add(addTheme());
        menuBar.add(addHelp());
        return menuBar;
    }

    public JToolBar addToolBar(){
        JToolBar toolBar = new JToolBar();
        JComboBox jComboBox = new JComboBox();
        UIManager.LookAndFeelInfo[] lookAndFeelInfos =  UIManager.getInstalledLookAndFeels();
        for(int i=0;i<lookAndFeelInfos.length;i++){
            UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];
            jComboBox.addItem(lookAndFeelInfo.getName());
        }
        add(jComboBox);
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JComboBox source = (JComboBox)e.getSource();
                    source.getItemAt(source.getSelectedIndex());
                    source.getSelectedItem();
                    UIManager.LookAndFeelInfo[] lookAndFeelInfos =  UIManager.getInstalledLookAndFeels();
                    for(int i=0;i<lookAndFeelInfos.length;i++){
                        UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];
                        if(lookAndFeelInfo.getName().equals((String)source.getSelectedItem())){
                            UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                            SwingUtilities.updateComponentTreeUI(GeneratorControlFrame.this);
                            break;
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException| IllegalAccessException | UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }
            }
        });
        toolBar.add(jComboBox);
        toolBar.addSeparator();
        return toolBar;
    }

    public JMenu addFile(){
        JMenu mnFile = new JMenu("文件(F)");
        mnFile.setMnemonic('F');

        JMenuItem mnOpenItem = new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "打开");
            }
            public void actionPerformed(ActionEvent e) {
                GeneratorContext.getInstance().getGeneratorControlFrame().setVisible(true);
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));

                int r = chooser.showOpenDialog(GeneratorControlFrame.this);
                if (r != JFileChooser.APPROVE_OPTION) return;
                final File file = chooser.getSelectedFile();
            }
        });
        mnFile.add(mnOpenItem);

        JMenuItem mnSaveItem = new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "保存");
            }
            public void actionPerformed(ActionEvent e) {
                GeneratorContext.getInstance().getGeneratorControlFrame().setVisible(true);
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));

                int r = chooser.showSaveDialog(GeneratorControlFrame.this);
                if (r != JFileChooser.APPROVE_OPTION) return;
                final File file = chooser.getSelectedFile();
            }
        });
        mnFile.add(mnSaveItem);

        JMenuItem mnSaveAsItem = new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "另存为");
            }
            public void actionPerformed(ActionEvent e) {
                GeneratorContext.getInstance().getGeneratorControlFrame().setVisible(true);
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));

                int r = chooser.showOpenDialog(GeneratorControlFrame.this);
                if (r != JFileChooser.APPROVE_OPTION) return;
                final File file = chooser.getSelectedFile();
            }
        });
        mnFile.add(mnSaveAsItem);

        JMenuItem mnExitItem = new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "退出");
            }
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnFile.add(mnExitItem);
        return mnFile;
    }

    public JMenu addTool(){
        JMenu mnTools = new JMenu("工具(T)");
        mnTools.setMnemonic('T');
        JMenuItem mnMavenItem = new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "MAVEN");
            }
            public void actionPerformed(ActionEvent e) {
                GeneratorMavenFrame generatorMavenFrame = new GeneratorMavenFrame();
//				generatorMavenFrame.setIconifiable(true);
//				generatorMavenFrame.setMaximizable(true);
//				generatorMavenFrame.setClosable(false);
//				jDesktopPane.add(generatorMavenFrame);
                generatorMavenFrame.setVisible(true);
            }
        });
//		generatorMavenFrame.setAccelerator(KeyStroke.getKeyStroke("ctrl 0"));
        mnTools.add(mnMavenItem);
        return mnTools;
    }

    public JMenu addHelp(){
        JMenu mnHelp = new JMenu("帮助(H)");
        mnHelp.setMnemonic('H');

        JMenuItem mnLogItem = new JMenuItem( new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "日志");
//				putValue(Action.SHORT_DESCRIPTION, "生成文件");
//				putValue(Action.SMALL_ICON, icon);
//				putValue(Action.LARGE_ICON_KEY, icon);
            }
            public void actionPerformed(ActionEvent e) {
                GeneratorLogFrame generatorLogFrame = new GeneratorLogFrame();
                generatorLogFrame.setVisible(true);
            }
        });
        mnHelp.add(mnLogItem);
        mnHelp.addSeparator();
        JMenuItem mnAboutItem = new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "关于");
            }
            public void actionPerformed(ActionEvent e) {
//                GeneratorAboutFrame
//                    generatorAboutFrame = new GeneratorAboutFrame();
//                jDesktopPane.add(generatorAboutFrame);
//                generatorAboutFrame.setIconifiable(true);
//                generatorAboutFrame.setClosable(true);
//                generatorAboutFrame.setVisible(true);
            }
        });
        mnAboutItem.setAccelerator(KeyStroke.getKeyStroke("ctrl 0"));
        mnHelp.add(mnAboutItem);
        return mnHelp;
    }

    public JMenu addTheme(){
        JMenu mnTheme = new JMenu("主题");
        ButtonGroup buttonGroup = new ButtonGroup();
        UIManager.LookAndFeelInfo[] lookAndFeelInfos =  UIManager.getInstalledLookAndFeels();
        for(int i=0;i<lookAndFeelInfos.length;i++){
            final UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];
            JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(new AbstractAction(lookAndFeelInfo.getName()) {
                private static final long serialVersionUID = 1L;
                public void actionPerformed(ActionEvent e) {
                    try{
                        UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                        SwingUtilities.updateComponentTreeUI(GeneratorControlFrame.this);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            if(UIManager.getLookAndFeel().getName().equals(lookAndFeelInfo.getName())){
                jCheckBoxMenuItem.setSelected(true);
            }
            buttonGroup.add(jCheckBoxMenuItem);
            mnTheme.add(jCheckBoxMenuItem);
        }
        return mnTheme;
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
		generatorTreePanel.addTreeSelectionListener(e -> {
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
				logger.error("",ex);
				JOptionPane.showMessageDialog(GeneratorControlFrame.this, ex.getMessage());
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
			logger.error("",e);
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
			logger.error("",e);
		}
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);

	}
}
