package com.hdhxby.ecommerce.auth.visitor;

import com.hdhxby.ecommerce.auth.visitor.impl.AuthPriorityClearAbelVisitor;
import com.hdhxby.ecommerce.auth.visitor.impl.AuthPriorityClearVisitor;
import com.hdhxby.ecommerce.auth.visitor.impl.AuthPriorityTreeVisitor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 权限访问者工厂
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AuthPriorityVisitorFactory implements BeanFactoryAware {

    private BeanFactory beanFactory;

    /**
     * 节点是否可清理访问者
     * @return
     */
    public AuthPriorityClearAbelVisitor authPriorityClearAbelVisitor() {
        return beanFactory.getBean(AuthPriorityClearAbelVisitor.class);
    }

    /**
     * 清理节点访问者
     * @return
     */
    public AuthPriorityClearVisitor authPriorityClearVisitor() {
        return beanFactory.getBean(AuthPriorityClearVisitor.class);
    }

    /**
     * 树形访问者
     * @return
     */
    public AuthPriorityTreeVisitor authPriorityTreeVisitor() {
        return beanFactory.getBean(AuthPriorityTreeVisitor.class);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
