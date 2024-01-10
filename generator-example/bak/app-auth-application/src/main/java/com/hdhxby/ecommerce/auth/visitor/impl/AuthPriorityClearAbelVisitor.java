package com.hdhxby.ecommerce.auth.visitor.impl;

import com.hdhxby.ecommerce.auth.repository.AuthAccountPriorityRelationshipRepository;
import com.hdhxby.ecommerce.auth.repository.AuthPriorityRepository;
import com.hdhxby.ecommerce.auth.repository.AuthRolePriorityRelationshipRepository;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountPriorityRelationshipCriteria;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthPriorityCriteria;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthRolePriorityRelationshipCriteria;
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
public class AuthPriorityClearAbelVisitor extends AbstractAuthPriorityVisitor<AuthPriorityDTO, Boolean> {

    @Autowired
    private AuthPriorityRepository authPriorityRepository;

    @Autowired
    private AuthRolePriorityRelationshipRepository authRolePriorityRelationshipRepository;

    @Autowired
    private AuthAccountPriorityRelationshipRepository authAccountPriorityRelationshipRepository;

    /**
     * 是否可删除
     *
     * @param authPriorityNode 权限节点
     * @return 是否可删除
     */
    @Override
    public Boolean execute(AuthPriorityDTO authPriorityNode) {
        LongFilter longFilter = new LongFilter().equals(authPriorityNode.getId());
        // 自己的结果:不存在角色权限,账户权限关联关系
        // 与上
        // 子集的结果
        return authRolePriorityRelationshipRepository.findAllByCriteria(AuthRolePriorityRelationshipCriteria.builder().id(longFilter).build()).isEmpty()
            && authAccountPriorityRelationshipRepository.findAllByCriteria(AuthAccountPriorityRelationshipCriteria.builder().id(longFilter).build()).isEmpty()
            && authPriorityRepository.findAllByCriteria(AuthPriorityCriteria.builder().parentId(longFilter).build())
            .stream().map(child -> execute(new AuthPriorityDTO(child.getId())))
            .reduce(true, (first, second) -> first && second);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
