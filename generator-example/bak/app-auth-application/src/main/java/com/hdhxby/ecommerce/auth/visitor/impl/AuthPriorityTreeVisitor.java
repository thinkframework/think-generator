package com.hdhxby.ecommerce.auth.visitor.impl;

import com.hdhxby.ecommerce.auth.domain.AuthPriority;
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
 * 权限节点访问者
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AuthPriorityTreeVisitor extends AbstractAuthPriorityVisitor<AuthPriorityDTO, AuthPriorityDTO> implements InitializingBean {

    @Autowired
    private AuthPriorityRepository authPriorityRepository;

    @Autowired
    private AuthPriorityMapper authorizedMapper;


    private List<AuthPriority> list;

    /**
     * 生成树
     *
     * @param authPriorityNode 权限节点
     */
    @Override
    public AuthPriorityDTO execute(AuthPriorityDTO authPriorityNode) {
        authPriorityNode.setChildren(
            list.stream()
                .filter(child -> authPriorityNode.getId().equals(child.getParentId()))
                .map(child -> authorizedMapper.toDto(child))
                .peek(child -> execute(child))
                .collect(Collectors.toList())
        );
        return authPriorityNode;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        list = authPriorityRepository.findAll();
    }
}
