//package org.think.swing;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import GeneratorAboutFrame;
//import GeneratorControlFrame;
//import GeneratorLogFrame;
//import GeneratorMavenFrame;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyVetoException;
//import java.io.File;
//
///**
// *
// * @author hdhxby
// * @email hdhxby@qq.com
// */
//public class GeneratorApplicationFrame extends JFrame{
//	private Logger logger = LoggerFactory.getLogger(getClass());
//
//	private JDesktopPane jDesktopPane;
//	public GeneratorApplicationFrame() {
//		super();
//		setTitle("Application");
//
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭
//		setSize(1024,768);
////		setSize(WIDTH,HEIGHT);// 设置大小
////		setClosable(true);
////		setResizable(true);//改变大小
////		setMaximizable(true);
////		setIconifiable(true);
//		setLocationRelativeTo(null);//居中
//
//		Toolkit toolkit= Toolkit.getDefaultToolkit();
//		Dimension dimension =toolkit.getScreenSize();
//		double height = dimension.getHeight();
//		double width = dimension.getWidth();
//
////		setSize((int)height,(int)width/2);
//
//		setJMenuBar(addMenu());
//		add(addToolBar(), BorderLayout.NORTH);
//		add(initGeneratorControlFrame());
//	}
//
//	/**
//	 * 初始化主控制器窗口
//	 */
//	public JDesktopPane initGeneratorControlFrame(){
//		jDesktopPane = new JDesktopPane();
//		jDesktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
//		try {
//			GeneratorControlFrame generatorControlFrame = new GeneratorControlFrame();
//			jDesktopPane.add(generatorControlFrame);
//			generatorControlFrame.setVisible(true);
//			generatorControlFrame.setMaximum(true);//最大化
//			generatorControlFrame.setMaximizable(false);
////			generatorControlFrame.setResizable(false);
//			generatorControlFrame.setClosable(false);
//		}catch (PropertyVetoException e){
//			logger.error("窗口最大化失败.",e);
//		} finally {
//			return jDesktopPane;
//		}
//	}
//
//	public JMenuBar addMenu(){
//		JMenuBar menuBar = new JMenuBar();
//		menuBar.add(addFile());
//		menuBar.add(addTool());
//		menuBar.add(addTheme());
//		menuBar.add(addHelp());
//		return menuBar;
//	}
//
//	public JToolBar addToolBar(){
//		JToolBar toolBar = new JToolBar();
//		JComboBox jComboBox = new JComboBox();
//		UIManager.LookAndFeelInfo[] lookAndFeelInfos =  UIManager.getInstalledLookAndFeels();
//		for(int i=0;i<lookAndFeelInfos.length;i++){
//			UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];
//			jComboBox.addItem(lookAndFeelInfo.getName());
//		}
//		add(jComboBox);
//		jComboBox.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					JComboBox source = (JComboBox)e.getSource();
//					source.getItemAt(source.getSelectedIndex());
//					source.getSelectedItem();
//					UIManager.LookAndFeelInfo[] lookAndFeelInfos =  UIManager.getInstalledLookAndFeels();
//					for(int i=0;i<lookAndFeelInfos.length;i++){
//						UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];
//						if(lookAndFeelInfo.getName().equals((String)source.getSelectedItem())){
//							UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
//							SwingUtilities.updateComponentTreeUI(GeneratorApplicationFrame.this);
//							break;
//						}
//					}
//				} catch (ClassNotFoundException | InstantiationException| IllegalAccessException | UnsupportedLookAndFeelException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
//		toolBar.add(jComboBox);
//		toolBar.addSeparator();
//		return toolBar;
//	}
//
//	public JMenu addFile(){
//		JMenu mnFile = new JMenu("文件(F)");
//		mnFile.setMnemonic('F');
//
//		JMenuItem mnOpenItem = new JMenuItem(new AbstractAction() {
//			private static final long serialVersionUID = 1L;
//			{
//				putValue(Action.NAME, "打开");
//			}
//			public void actionPerformed(ActionEvent e) {
//				GeneratorContext.getInstance().getGeneratorControlFrame().setVisible(true);
//				JFileChooser chooser = new JFileChooser();
//				chooser.setCurrentDirectory(new File("."));
//
//				int r = chooser.showOpenDialog(GeneratorApplicationFrame.this);
//				if (r != JFileChooser.APPROVE_OPTION) return;
//				final File file = chooser.getSelectedFile();
//			}
//		});
//		mnFile.add(mnOpenItem);
//
//		JMenuItem mnSaveItem = new JMenuItem(new AbstractAction() {
//			private static final long serialVersionUID = 1L;
//			{
//				putValue(Action.NAME, "保存");
//			}
//			public void actionPerformed(ActionEvent e) {
//				GeneratorContext.getInstance().getGeneratorControlFrame().setVisible(true);
//				JFileChooser chooser = new JFileChooser();
//				chooser.setCurrentDirectory(new File("."));
//
//				int r = chooser.showSaveDialog(GeneratorApplicationFrame.this);
//				if (r != JFileChooser.APPROVE_OPTION) return;
//				final File file = chooser.getSelectedFile();
//			}
//		});
//		mnFile.add(mnSaveItem);
//
//		JMenuItem mnSaveAsItem = new JMenuItem(new AbstractAction() {
//			private static final long serialVersionUID = 1L;
//			{
//				putValue(Action.NAME, "另存为");
//			}
//			public void actionPerformed(ActionEvent e) {
//				GeneratorContext.getInstance().getGeneratorControlFrame().setVisible(true);
//				JFileChooser chooser = new JFileChooser();
//				chooser.setCurrentDirectory(new File("."));
//
//				int r = chooser.showOpenDialog(GeneratorApplicationFrame.this);
//				if (r != JFileChooser.APPROVE_OPTION) return;
//				final File file = chooser.getSelectedFile();
//			}
//		});
//		mnFile.add(mnSaveAsItem);
//
//		JMenuItem mnExitItem = new JMenuItem(new AbstractAction() {
//			private static final long serialVersionUID = 1L;
//			{
//				putValue(Action.NAME, "退出");
//			}
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
//		mnFile.add(mnExitItem);
//		return mnFile;
//	}
//
//	public JMenu addTool(){
//		JMenu mnTools = new JMenu("工具(T)");
//		mnTools.setMnemonic('T');
//		JMenuItem mnMavenItem = new JMenuItem(new AbstractAction() {
//			private static final long serialVersionUID = 1L;
//			{
//				putValue(Action.NAME, "MAVEN");
//			}
//			public void actionPerformed(ActionEvent e) {
//				GeneratorMavenFrame generatorMavenFrame = new GeneratorMavenFrame();
////				generatorMavenFrame.setIconifiable(true);
////				generatorMavenFrame.setMaximizable(true);
////				generatorMavenFrame.setClosable(false);
////				jDesktopPane.add(generatorMavenFrame);
//				generatorMavenFrame.setVisible(true);
//			}
//		});
////		generatorMavenFrame.setAccelerator(KeyStroke.getKeyStroke("ctrl 0"));
//		mnTools.add(mnMavenItem);
//		return mnTools;
//	}
//
//	public JMenu addHelp(){
//		JMenu mnHelp = new JMenu("帮助(H)");
//		mnHelp.setMnemonic('H');
//
//		JMenuItem mnLogItem = new JMenuItem( new AbstractAction() {
//			private static final long serialVersionUID = 1L;
//			{
//				putValue(Action.NAME, "日志");
////				putValue(Action.SHORT_DESCRIPTION, "生成文件");
////				putValue(Action.SMALL_ICON, icon);
////				putValue(Action.LARGE_ICON_KEY, icon);
//			}
//			public void actionPerformed(ActionEvent e) {
//				GeneratorLogFrame generatorLogFrame = new GeneratorLogFrame();
//				generatorLogFrame.setVisible(true);
//			}
//		});
//		mnHelp.add(mnLogItem);
//		mnHelp.addSeparator();
//		JMenuItem mnAboutItem = new JMenuItem(new AbstractAction() {
//			private static final long serialVersionUID = 1L;
//			{
//				putValue(Action.NAME, "关于");
//			}
//			public void actionPerformed(ActionEvent e) {
//				GeneratorAboutFrame
//					generatorAboutFrame = new GeneratorAboutFrame();
//				jDesktopPane.add(generatorAboutFrame);
//				generatorAboutFrame.setIconifiable(true);
//				generatorAboutFrame.setClosable(true);
//				generatorAboutFrame.setVisible(true);
//			}
//		});
//		mnAboutItem.setAccelerator(KeyStroke.getKeyStroke("ctrl 0"));
//		mnHelp.add(mnAboutItem);
//		return mnHelp;
//	}
//
//	public JMenu addTheme(){
//		JMenu mnTheme = new JMenu("主题");
//		ButtonGroup buttonGroup = new ButtonGroup();
//		UIManager.LookAndFeelInfo[] lookAndFeelInfos =  UIManager.getInstalledLookAndFeels();
//		for(int i=0;i<lookAndFeelInfos.length;i++){
//			final UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];
//			JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(new AbstractAction(lookAndFeelInfo.getName()) {
//				private static final long serialVersionUID = 1L;
//				public void actionPerformed(ActionEvent e) {
//					try{
//						UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
//						SwingUtilities.updateComponentTreeUI(GeneratorApplicationFrame.this);
//					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
//						e1.printStackTrace();
//					}
//				}
//			});
//			if(UIManager.getLookAndFeel().getName().equals(lookAndFeelInfo.getName())){
//				jCheckBoxMenuItem.setSelected(true);
//			}
//			buttonGroup.add(jCheckBoxMenuItem);
//			mnTheme.add(jCheckBoxMenuItem);
//		}
//		return mnTheme;
//	}
//
//	@Override
//	public void setVisible(boolean b) {
//		super.setVisible(b);
//
//	}
//}
