package io.github.thinkframework.swing.config.list;

import io.github.thinkframework.swing.event.ListEvent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 列表控制类
 * @author lixiaobin
 */
public class GeneratorList extends JList implements ApplicationEventPublisherAware, InitializingBean {

    private ApplicationEventPublisher applicationEventPublisher;


    public JPopupMenu addJPopuMenu() {
        JPopupMenu jPopupMenu = new JPopupMenu();
        jPopupMenu.add(new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;

            {
                putValue(Action.NAME, "新建连接");
            }

            public void actionPerformed(ActionEvent e) {

            }
        }));
        jPopupMenu.add(new JMenuItem(new AbstractAction() {
            private static final long serialVersionUID = 1L;
            {
                putValue(Action.NAME, "删除连接");
            }
            public void actionPerformed(ActionEvent e) {

            }
        }));
        return jPopupMenu;
    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void afterPropertiesSet() {
        addJPopuMenu();
		addListSelectionListener(e ->  {
            String dataSourceName= (String)((JList)e.getSource()).getSelectedValue();
            this.applicationEventPublisher.publishEvent(new ListEvent(dataSourceName));
		});
    }
}
