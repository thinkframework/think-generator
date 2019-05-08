package io.github.thinkframework.swing.control;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Properties;

/**
 * 过滤文件
 * @author lixiaobin
 */
public class GeneratorFacadeFrame extends JFrame {
	private DataSource dataSource;
	private Properties properties;
	private String tableName;
	private GeneratorFacadeFrame(){
	}

	public GeneratorFacadeFrame( DataSource dataSource, Properties properties, String tableName) {
		super();
		setTitle("");
		setSize(480,320);
		this.dataSource = dataSource;
		this.properties = properties;
		this.tableName = tableName;
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		try {
			if(properties == null) {
				properties.load(getClass().getClassLoader().getResourceAsStream("applicationContext.properties"));
			}
		} catch(IOException e){
			e.printStackTrace();
		}

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				Object o = e.getSource();
				System.out.println(e+"\t"+o);
			}
		});
		chckbxNewCheckBox_2.setText("core");
		chckbxNewCheckBox_2.setSelected(Boolean.valueOf(properties.getProperty("core","false")));
		panel_1.add(chckbxNewCheckBox_2);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Spring MVC",Boolean.valueOf(properties.getProperty("core","false")));
		panel_2.add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Struts2",Boolean.valueOf(properties.getProperty("core","false")));
		panel_2.add(chckbxNewCheckBox_1);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);

		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Spring Data JPA",Boolean.valueOf(properties.getProperty("core","false")));
		panel_3.add(chckbxNewCheckBox_3);

		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Hibernate",Boolean.valueOf(properties.getProperty("core","false")));
		panel_3.add(chckbxNewCheckBox_4);

		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Mybatis",Boolean.valueOf(properties.getProperty("core","false")));
		panel_3.add(chckbxNewCheckBox_5);

		JCheckBox chckbxIbatis = new JCheckBox("Ibatis",Boolean.valueOf(properties.getProperty("core","false")));
		panel_3.add(chckbxIbatis);


		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("是否生成测试类:");
		panel_4.add(lblNewLabel);
		ButtonGroup  buttonGroup = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("是",Boolean.valueOf(properties.getProperty("core","false")));
		panel_4.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnNewRadioButton);
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("否",Boolean.valueOf(properties.getProperty("core","false")));
		panel_4.add(rdbtnNewRadioButton_1);
		buttonGroup.add(rdbtnNewRadioButton_1);

		getContentPane().add(getButton(), BorderLayout.SOUTH);
	}

	private JPanel getButton(){
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		panel.add(new JButton(new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "确定");
				putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("general/openProject.png")));
			}
			public void actionPerformed(ActionEvent e) {
//				new GeneratorFacade().generator(GeneratorContext.getInstance().getDataSource("dataSource"),"T_SYS_MENU");
			}
		}));

		panel.add(new JButton(new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "取消");
				putValue(Action.SMALL_ICON, new ImageIcon(getClass().getClassLoader().getResource("general/openProject.png")));
			}
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		}));
		return panel;
	}

	public static void main(String... args){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GeneratorFacadeFrame(null,new Properties(),null).setVisible(true);
			}
		});
	}
}
