package com.hdhxby.ecommerce.auth.visitor.impl;

import com.hdhxby.ecommerce.auth.mapper.AuthPriorityMapper;
import com.hdhxby.ecommerce.auth.service.dto.AuthPriorityDTO;
import com.hdhxby.ecommerce.auth.visitor.AuthPriorityVisitor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 抽象权限访问者
 */
public abstract class AbstractAuthPriorityVisitor<T extends AuthPriorityDTO, R> implements AuthPriorityVisitor<T, R>, InitializingBean {

    @Autowired
    protected AuthPriorityMapper authPriorityMapper;


}
