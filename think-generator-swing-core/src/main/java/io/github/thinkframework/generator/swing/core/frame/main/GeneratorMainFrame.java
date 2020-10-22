package io.github.thinkframework.generator.swing.core.frame.main;

import io.github.thinkframework.generator.swing.core.frame.datasource.GeneratorDataSourceFrame;
import io.github.thinkframework.generator.swing.core.frame.about.GeneratorAboutFrame;
import io.github.thinkframework.generator.swing.core.util.GeneratorFileUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * @author hdhxby
 * @email hdhxby@qq.com
 */
public class GeneratorMainFrame extends JFrame implements ApplicationContextAware, ResourceLoaderAware, InitializingBean {
    private static final long serialVersionUID = 1L;

    private final Integer WIDTH = 1024, HEIGHT = 768;

    private ApplicationContext applicationContext;

    private ResourceLoader resourceLoader;

    private GeneratorMainPanel generatorMainPanel;

    private JToolBar toolBar;

    @Override
    public void afterPropertiesSet() {
        setTitle("Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭
        setSize(WIDTH, HEIGHT);// 设置大小
        setResizable(true);//改变大小
        setLocationRelativeTo(null);//居中
        getJMenuBar();
        setJMenuBar(menuBar());
        add(generatorMainPanel);
    }

    /**
     * 初始化菜单
     *
     * @return 菜单
     */
    public JMenuBar menuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(addFile());
        jMenuBar.add(addTool());
        jMenuBar.add(addTheme());
        jMenuBar.add(addHelp());
        return jMenuBar;
    }

    /**
     * 文件
     * @return 文件菜单
     */
    public JMenu addFile() {
        JMenu mnFile = new JMenu("文件(F)");
        mnFile.setMnemonic('F');
//        mnFile.add(new JMenuItem(new AbstractAction() {
//            private static final long serialVersionUID = 1L;
//
//            {
//                putValue(Action.NAME, "刷新");
//            }
//
//            public void actionPerformed(ActionEvent e) {
//                ((AbstractRefreshableApplicationContext) applicationContext).refresh();
//            }
//        }));
        JMenuItem mnOpenItem = new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            {
                putValue(Action.NAME, "打开");
                try {
                    putValue(Action.SMALL_ICON, new ImageIcon(resourceLoader.getResource("general/openDisk.png").getURL()));
                } catch (Exception e) {
                }
            }

            public void actionPerformed(ActionEvent e) {
                GeneratorMainFrame.this.setVisible(true);
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));

                int r = chooser.showOpenDialog(GeneratorMainFrame.this);
                if (r != JFileChooser.APPROVE_OPTION) return;
                final File file = chooser.getSelectedFile();
                    GeneratorFileUtil.openFile(file);
            }
        });
        mnFile.add(mnOpenItem);

        mnFile.addSeparator();

        mnFile.add(new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "打开目录");
                try {
                    putValue(Action.SMALL_ICON, new ImageIcon(resourceLoader.getResource("general/openDisk.png").getURL()));
                } catch (Exception e) {
                }
            }

            public void actionPerformed(ActionEvent e) {
                GeneratorFileUtil.openDirectory(new File(System.getProperty("user.dir")));
            }
        }));

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

    /**
     * 帮助
     * @return 帮助菜单
     */
    public JMenu addHelp() {
        JMenu mnHelp = new JMenu("帮助(H)");
        mnHelp.setMnemonic('H');
        JMenuItem mnLogItem = new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "日志");
            }

            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() ->{
//                    GeneratorLogFrame generatorLogFrame = applicationContext.getBean(GeneratorLogFrame.class);
//                    generatorLogFrame.setVisible(true);
                    GeneratorFileUtil.openFile(new File(System.getProperty("user.dir") + File.separator + "generator.txt"));
                });
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
                EventQueue.invokeLater(() ->{
                    GeneratorAboutFrame generatorAboutFrame = applicationContext.getBean(GeneratorAboutFrame.class);
                    generatorAboutFrame.setVisible(true);
                });
            }
        });
        mnAboutItem.setAccelerator(KeyStroke.getKeyStroke("ctrl 0"));
        mnHelp.add(mnAboutItem);
        return mnHelp;
    }

    public JMenu addTheme() {
        JMenu mnTheme = new JMenu("主题(T)");
//        mnTheme.setMnemonic('T');
        ButtonGroup buttonGroup = new ButtonGroup();
        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < lookAndFeelInfos.length; i++) {
            final UIManager.LookAndFeelInfo lookAndFeelInfo = lookAndFeelInfos[i];
            JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(new AbstractAction(lookAndFeelInfo.getName()) {
                private static final long serialVersionUID = 1L;

                public void actionPerformed(ActionEvent e) {
                    try {
                        if (System.getProperty("os.name").contains("")) {

                        }
                        UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                        SwingUtilities.updateComponentTreeUI(GeneratorMainFrame.this);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            if (UIManager.getLookAndFeel().getName().equals(lookAndFeelInfo.getName())) {
                jCheckBoxMenuItem.setSelected(true);
            }
            buttonGroup.add(jCheckBoxMenuItem);
            mnTheme.add(jCheckBoxMenuItem);
        }
        return mnTheme;
    }


    /**
     * 初始化ToolBar
     *
     * @return
     */
    public JMenu addTool() {
        JMenu mnTool = new JMenu("工具");
        mnTool.setMnemonic('G');
        mnTool.add(new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            {
                putValue(Action.NAME, "数据源设置");
                try {
                    putValue(Action.SMALL_ICON, new ImageIcon(resourceLoader.getResource("general/settings.png").getURL()));
                } catch (Exception e) {
                }
            }

            public void actionPerformed(ActionEvent e) {
                GeneratorDataSourceFrame generatorDataSourceFrame = applicationContext.getBean(GeneratorDataSourceFrame.class);
                generatorDataSourceFrame.setVisible(true);
            }
        }));
        return mnTool;
    }

    public void setGeneratorControlPanel(GeneratorMainPanel generatorMainPanel) {
        this.generatorMainPanel = generatorMainPanel;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
