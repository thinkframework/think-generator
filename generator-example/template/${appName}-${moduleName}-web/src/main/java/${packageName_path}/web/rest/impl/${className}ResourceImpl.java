package com.hdhxby.ecommerce.auth.web.rest.impl;

import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountCriteria;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountPriorityRelationshipCriteria;
import com.hdhxby.ecommerce.auth.repository.criteria.AuthAccountRoleRelationshipCriteria;
import com.hdhxby.ecommerce.auth.service.*;
import com.hdhxby.ecommerce.auth.service.dto.*;
import com.hdhxby.ecommerce.auth.web.rest.AuthAccountResource;
import io.github.thinkframework.commons.repository.filter.LongFilter;
import io.github.thinkframework.commons.web.rest.AbstractRestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ${clazz.remarks}.
 *
 * @author unascribed
 * @version 1.0.0
* @since 1.0.0
 */
//@Tag(name = "账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限")
@RequestMapping("/api/${moduleName}/${moduleName}-${className}s")
@RestController
public class ${className}ResourceImpl extends AbstractRestResource<${className}Service,
        ${className}DTO, ${className}Criteria, Long> implements ${className}Resource {

}
