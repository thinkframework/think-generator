package io.github.thinkframework.swing.other;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class GeneratorMavenFrame extends JFrame {
	private JTextField repositoryIdField;
	private JTextField urlField;
	private JTextField groupIdField;
	private JTextField artifactIdField;
	private JTextField versionField;
	private JTextField packagingField;
	private JTextField fileField;
	private JLabel versionLabel;
	public GeneratorMavenFrame() {
		String TITLE = "第三方JAR包安装工具";
		int WIDTH = 600;
		int HEIGHT = 480;
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);// 设置大小
		setLocationRelativeTo(null);//居中
//		setLocationByPlatform(true);//
//		System.out.print(getOwner());
		add(addCenterPanel());
	}

	public JPanel addCenterPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0,200));
		northPanel.setLayout(new GridLayout(5,4));
		Border border = BorderFactory.createTitledBorder("链接信息");
		northPanel.setBorder(border);
		panel.add(northPanel,BorderLayout.NORTH);

		JLabel groupIdLabel = new JLabel("groupId");
		northPanel.add(groupIdLabel);

		groupIdField = new JTextField();
		groupIdField.setColumns(10);
		northPanel.add(groupIdField);

		JLabel artifactIdLabel = new JLabel("artifactId");
		northPanel.add(artifactIdLabel);

		artifactIdField = new JTextField();
		artifactIdField.setColumns(10);
		northPanel.add(artifactIdField);

		versionLabel = new JLabel("version");
		northPanel.add(versionLabel);


		versionField = new JTextField();
		versionField.setColumns(10);
		northPanel.add(versionField);

		JLabel packagingLabel = new JLabel("packaging");
		northPanel.add(packagingLabel);

		packagingField = new JTextField("jar");
		northPanel.add(packagingField);


		JLabel fileLabel = new JLabel("file");
		northPanel.add(fileLabel);

		fileField = new JTextField();
		fileField.setColumns(10);
		northPanel.add(fileField);

		JLabel urlLabel = new JLabel("url");
		northPanel.add(urlLabel);

		urlField = new JTextField();
		urlField.setColumns(10);
		northPanel.add(urlField);

		JLabel repositoryIdLabel = new JLabel("repositoryId");
		northPanel.add(repositoryIdLabel);

		repositoryIdField = new JTextField();
		repositoryIdField.setColumns(10);
		northPanel.add(repositoryIdField);

		JPanel southPanel = new JPanel();

		JButton saveButton = new JButton("安装");
		saveButton.addActionListener(e -> {
			groupIdField.getText();
			artifactIdField.getText();
			versionField.getText();
			packagingField.getText();
			StringBuffer exec = new StringBuffer();
			if(true) {
				exec.append("mvn install:install-file")
						.append(" -DgroupId=").append(groupIdField.getText())
						.append(" -DartifactId=").append(artifactIdField.getText())
						.append(" -Dversion=").append(versionField.getText())
						.append(" -Dpackaging=").append(packagingField.getText())
						.append(" -Dfile=").append(fileField.getText());
			}else{
				exec.append("mvn install:install-file")
						.append(" -DgroupId=").append(groupIdField.getText())
						.append(" -DartifactId=").append(artifactIdField.getText())
						.append(" -Dversion=").append(versionField.getText())
						.append(" -Dpackaging=").append(packagingField.getText())
						.append(" -Dfile=").append(fileField.getText())
						.append(" -Durl=").append(urlField.getText())
						.append(" -DrepositoryId=").append(repositoryIdField.getText());
			}
			try {
				Runtime run = Runtime.getRuntime();
				Process p = run.exec(exec.toString());
				BufferedInputStream in = new BufferedInputStream(p.getInputStream());
				BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
				String lineStr;
				while ((lineStr = inBr.readLine()) != null)
					System.out.println(lineStr);
				if (p.waitFor() != 0) {
					if (p.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束
						System.err.println("命令执行失败!");
				}
				inBr.close();
				in.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		southPanel.add(saveButton);

		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(e -> this.setVisible(false));
		southPanel.add(cancelButton);
		panel.add(southPanel, BorderLayout.SOUTH);
		return panel;
	}
}
