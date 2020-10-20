package io.github.thinkframework.swing.config;

import io.github.thinkframework.swing.event.ListEvent;
import io.github.thinkframework.swing.model.DataSourceBean;
import io.github.thinkframework.swing.model.XmlUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

/**
 *
 * @author lixiaobin
 */
public class GeneratorConfigurePanel extends JPanel implements ApplicationContextAware, ApplicationListener, InitializingBean {
	private XmlUtil applicationContextXmlUtil = new XmlUtil();
	private JTextField idField;
	private JTextField driverClassNameField;
	private JTextField urlField;
	private JTextField usernameField;
	private JLabel usernameLabel;
	private JPasswordField passwordField;
    private JTextArea connectPropertiesTextArea;
	private ApplicationContext applicationContext;

	public JPanel centerNorthPanel() {
		JPanel northPanel = new JPanel();
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

	public JScrollPane centerCenterPanel(){
		JScrollPane centerPanel = new JScrollPane();
		Border propertiesBorder = BorderFactory.createTitledBorder("connectProperties");

		connectPropertiesTextArea = new JTextArea();
		connectPropertiesTextArea.setRows(10);
		centerPanel.setViewportView(connectPropertiesTextArea);
		centerPanel.setBorder(propertiesBorder);
		return centerPanel;
	}

	public JPanel centerSouthPanel(){
		JPanel southPanel = new JPanel();

		southPanel.add(new JButton(new AbstractAction() {
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "取消");
			}
			public void actionPerformed(ActionEvent e) {
				GeneratorConfigurePanel.this.setVisible(false);
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
                dataSourceBean.setPassword(passwordField.getPassword().toString());
				applicationContextXmlUtil.setDataSourceBean(dataSourceBean);

			}
		}));

		return southPanel;
	}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        setLayout(new BorderLayout());

        add(centerNorthPanel(),BorderLayout.NORTH);
        add(centerCenterPanel(),BorderLayout.CENTER);
        add(centerSouthPanel(), BorderLayout.SOUTH);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
//	    fixme
//        if(event instanceof ListEvent){
//            Optional.ofNullable(applicationContext.getBean((String)event.getSource())).ifPresent(druidDataSource -> {
//                if(druidDataSource instanceof DruidDataSource) {
//                    idField.setText((String)event.getSource());
//                    driverClassNameField.setText(((DruidDataSource)druidDataSource).getDriverClassName());
//                    urlField.setText(((DruidDataSource)druidDataSource).getUrl());
//                    usernameField.setText(((DruidDataSource)druidDataSource).getUsername());
//                    passwordField.setText(((DruidDataSource)druidDataSource).getPassword());
////                    connectPropertiesTextArea.setText(((DruidDataSource)druidDataSource).getConnectProperties());
//                }else if(druidDataSource instanceof SimpleDriverDataSource){
//                    idField.setText((String)event.getSource());
//                    urlField.setText(((SimpleDriverDataSource)druidDataSource).getUrl());
//                    usernameField.setText(((SimpleDriverDataSource)druidDataSource).getUsername());
//                    passwordField.setText(((SimpleDriverDataSource)druidDataSource).getPassword());
////                    passwordField.setText(((SimpleDriverDataSource)druidDataSource).getConnectionProperties());
//                }
//            });
//        }
    }
}
