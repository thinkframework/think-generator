package org.think.swing.config;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author lixiaobin
 */
public class GeneratorConfigureFrame extends JInternalFrame{
	private XmlUtil applicationContextXmlUtil = new XmlUtil();
	private String xml = "applicationContext.xml";
	private JList jList = new JList();
	private DefaultListModel rootListModel = new DefaultListModel();
	private JTextField idField;
	private JTextField driverClassNameField;
	private JTextField urlField;
	private JTextField usernameField;
	private JLabel usernameLabel;
	private JPasswordField passwordField;
	private JLabel propertiesLabel;

	public GeneratorConfigureFrame() {
		String TITLE = "Data Sources";
		int WIDTH = 640;
		int HEIGHT = 480;
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);// 设置大小
//		setLocationRelativeTo(null);//居中
//		setLocationByPlatform(true);//
		add(addWestPanel(), BorderLayout.WEST);
		add(addCenterPanel());
		init();
	}


	public JPanel addWestPanel(){
		JPanel panel =  new JPanel();
		panel.setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar();
		JButton addButton = new JButton( new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
//				putValue(Action.NAME, "增加");
				putValue(Action.SHORT_DESCRIPTION, "增加");
				putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("general/add.png")));
			}
			public void actionPerformed(ActionEvent e) {
				rootListModel.addElement(applicationContextXmlUtil.newDataSource());
			}
		});
		toolBar.add(addButton);
		JButton removeButton = new JButton( new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
//				putValue(Action.NAME, "删除");
				putValue(Action.SHORT_DESCRIPTION, "删除");
				putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("general/remove.png")));
			}
			public void actionPerformed(ActionEvent e) {
				if(!jList.isSelectionEmpty()) {
					rootListModel.removeElement(jList.getSelectedValue());
				}
			}
		});
		toolBar.add(removeButton);
		panel.add(toolBar, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(200,0));
		scrollPane.setViewportView(jList);
		jList.setModel(rootListModel);
		jList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				DataSourceBean dataSourceBean= (DataSourceBean)((JList)e.getSource()).getSelectedValue();
				idField.setText(dataSourceBean.getId());
				driverClassNameField.setText(dataSourceBean.getDriverClassName());
				urlField.setText(dataSourceBean.getUrl());
				usernameField.setText(dataSourceBean.getUsername());
				passwordField.setText(dataSourceBean.getPassword());
			}
		});
		panel.add(scrollPane);
		return panel;
	}

	public JPanel addCenterPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(addCenterNorthPanel(),BorderLayout.NORTH);
		panel.add(addCenterCenterPanel(),BorderLayout.CENTER);
		panel.add(addCenterSouthPanel(), BorderLayout.SOUTH);
		return panel;
	}


	public JPanel addCenterNorthPanel() {
		JPanel northPanel = new JPanel();
//		GroupLayout layout = new GroupLayout(northPanel);
		northPanel.setPreferredSize(new Dimension(0,200));
		northPanel.setLayout(new GridLayout(5,2));
		Border border = BorderFactory.createTitledBorder("链接信息");
		northPanel.setBorder(border);

		JLabel idLabel = new JLabel("id");
		northPanel.add(idLabel);

		idField = new JTextField();
		idField.setColumns(10);
		northPanel.add(idField);

		JLabel driverClassNameLabel = new JLabel("driverClassName");
		northPanel.add(driverClassNameLabel);

		driverClassNameField = new JTextField();
		driverClassNameField.setColumns(10);
		northPanel.add(driverClassNameField);

		JLabel urlLabel = new JLabel("url");
		northPanel.add(urlLabel);

		urlField = new JTextField();
		urlField.setColumns(10);
		northPanel.add(urlField);

		usernameLabel = new JLabel("username");
		northPanel.add(usernameLabel);


		usernameField = new JTextField();
		usernameField.setColumns(10);
		northPanel.add(usernameField);

		JLabel passwordLabel = new JLabel("password");
		northPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		northPanel.add(passwordField);
		return northPanel;
	}

	public JScrollPane addCenterCenterPanel(){
		JScrollPane centerPanel = new JScrollPane();
		Border propertiesBorder = BorderFactory.createTitledBorder("connectProperties");

		JTextArea connectPropertiesTextArea = new JTextArea();
//		connectPropertiesTextArea.setColumns(30);
		connectPropertiesTextArea.setRows(10);
		centerPanel.setViewportView(connectPropertiesTextArea);
		centerPanel.setBorder(propertiesBorder);
		return centerPanel;
	}

	public JPanel addCenterSouthPanel(){
		JPanel southPanel = new JPanel();

		southPanel.add(new JButton(new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "取消");
			}
			public void actionPerformed(ActionEvent e) {
				GeneratorConfigureFrame.this.setVisible(false);
			}
		}));

		southPanel.add(new JButton(new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "应用");
			}
			public void actionPerformed(ActionEvent e) {
				DataSourceBean dataSourceBean = new DataSourceBean();
				dataSourceBean.setId(idField.getText());
				dataSourceBean.setDriverClassName(driverClassNameField.getText());
				dataSourceBean.setUrl(urlField.getText());
				dataSourceBean.setUsername(usernameField.getText());
				dataSourceBean.setPassword(passwordField.getText());
				applicationContextXmlUtil.setDataSourceBean(dataSourceBean);

			}
		}));

		southPanel.add(new JButton(new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "确定");
			}
			public void actionPerformed(ActionEvent e) {
				DataSourceBean dataSourceBean = new DataSourceBean();
				dataSourceBean.setId(idField.getText());
				dataSourceBean.setDriverClassName(driverClassNameField.getText());
				dataSourceBean.setUrl(urlField.getText());
				dataSourceBean.setUsername(usernameField.getText());
				dataSourceBean.setPassword(passwordField.getText());
				applicationContextXmlUtil.setDataSourceBean(dataSourceBean);

			}
		}));

		return southPanel;
	}

	public void init() {
		for(DataSourceBean dataSourceBean: applicationContextXmlUtil.getDataSourceBeans()) {
			rootListModel.addElement(dataSourceBean);
		}
	}
}
