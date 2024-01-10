package com.hdhxby.ecommerce.auth.visitor.impl;

import com.hdhxby.ecommerce.auth.mapper.AuthPriorityMapper;
import com.hdhxby.ecommerce.auth.repository.AuthPriorityRepository;
import com.hdhxby.ecommerce.auth.service.dto.AuthPriorityDTO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * dto转vm
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AuthPriorityVMVisitor extends AbstractAuthPriorityVisitor<AuthPriorityDTO, AuthPriorityDTO> implements InitializingBean {

    @Autowired
    private AuthPriorityRepository authPriorityRepository;

    @Autowired
    private AuthPriorityMapper authorizedMapper;


    private List<AuthPriorityDTO> list;

    /**
     * 生成树
     *
     * @param authPriorityNode 权限节点
     */
    @Override
    public AuthPriorityDTO execute(AuthPriorityDTO authPriorityNode) {
        AuthPriorityDTO result = authPriorityNode;
        list.stream()
            .filter(child -> authPriorityNode.getId().equals(child.getParentId()))
            .map(this::execute)
            .forEach(child -> result.getChildren().add(child));
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        list = authPriorityRepository.findAll()
            .stream().map(authPriorityMapper::toDto)
            .collect(Collectors.toList());
    }
}
