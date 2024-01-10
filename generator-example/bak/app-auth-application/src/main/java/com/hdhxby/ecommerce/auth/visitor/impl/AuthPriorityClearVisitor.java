package com.hdhxby.ecommerce.auth.visitor.impl;

import com.hdhxby.ecommerce.auth.repository.AuthAccountPriorityRelationshipRepository;
import com.hdhxby.ecommerce.auth.repository.AuthPriorityRepository;
import com.hdhxby.ecommerce.auth.repository.AuthRolePriorityRelationshipRepository;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthPriorityCriteria;
import com.hdhxby.ecommerce.auth.service.dto.AuthPriorityDTO;
import io.github.thinkframework.commons.repository.filter.LongFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 权限节点访问者
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class AuthPriorityClearVisitor extends AbstractAuthPriorityVisitor<AuthPriorityDTO, Boolean> {

    @Autowired
    private AuthPriorityRepository authPriorityRepository;

    @Autowired
    private AuthRolePriorityRelationshipRepository authRolePriorityRelationshipRepository;

    @Autowired
    private AuthAccountPriorityRelationshipRepository authAccountPriorityRelationshipRepository;

    /**
     * 递归删除
     *
     * @param authPriorityNode 权限节点
     */
    @Override
    public Boolean execute(AuthPriorityDTO authPriorityNode) {
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(authPriorityNode.getId());
        //先删除子集
        authPriorityRepository.findAllByCriteria(AuthPriorityCriteria.builder().parentId(longFilter).build())
            .forEach(child -> execute(new AuthPriorityDTO(child.getId())));
        //后删除自己
        authPriorityRepository.deleteById(authPriorityNode.getId());
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
