package org.think.swing.about;

import javax.swing.*;
import java.awt.*;

/**
 * 帮助窗口
 * @author hdhxby
 * @email hdhxby@qq.com
 */
public class GeneratorAboutFrame extends JInternalFrame {
	public GeneratorAboutFrame(){
		setTitle("关于");
		setSize(480,320);
		add(new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D graphics2D = (Graphics2D)g;
				Image image = new ImageIcon(getClass().getClassLoader().getResource("icon.png")).getImage();
				graphics2D.drawImage(image,100,100,null);
				graphics2D.drawString("About",100,150);
			}

			public Dimension getPreferredSize() {
				return new Dimension(480,320);
			}
		});
		pack();
	}


	public static void main(String... args){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GeneratorAboutFrame().setVisible(true);
			}
		});
	}
}
