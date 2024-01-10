package ${packageName}.repository;

import ${packageName}.domain.AuthAccountPriorityRelationship;
import ${packageName}.repository.criteria.AuthAccountPriorityRelationshipCriteria;
import io.github.thinkframework.commons.repository.CriteriaRepository;

/**
 * 账号与权限的关系表，一个账号可以对应多个权限，一个权限也可以属于多个账号仓库.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public interface AuthAccountPriorityRelationshipRepository extends CriteriaRepository<AuthAccountPriorityRelationship, AuthAccountPriorityRelationshipCriteria, Long> {

}
