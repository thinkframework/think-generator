package com.hdhxby.ecommerce.auth.web.rest.impl;

import com.hdhxby.ecommerce.auth.service.AuthPriorityService;
import com.hdhxby.ecommerce.auth.service.dto.AuthPriorityDTO;
import com.hdhxby.ecommerce.auth.visitor.AuthPriorityVisitorFactory;
import com.hdhxby.ecommerce.auth.visitor.impl.AuthPriorityTreeVisitor;
import com.hdhxby.ecommerce.auth.web.rest.AuthResource;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色表，在系统中有多个角色，每个角色可以分配一些权限资源控制器.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Api(tags = {"权限中心"})
@RequestMapping("/api")
@RestController
public class AuthResourceImpl implements AuthResource {

    @Autowired
    private AuthPriorityService authPriorityService;

    @Autowired
    private AuthPriorityVisitorFactory authPriorityVisitorFactory;

    /**
     * 权限树
     *
     * @param id 权限ID
     * @return 权限树
     */
    @Override
    public ResponseEntity<List<AuthPriorityDTO>> findPriorityById(@PathVariable(name = "id") Long id) {
        AuthPriorityTreeVisitor authPriorityTreeVisitor = authPriorityVisitorFactory.authPriorityTreeVisitor();
        return ResponseEntity.ok(authPriorityService.findAll()
            .stream().map(authPriorityTreeVisitor::execute)
            .collect(Collectors.toList()));
    }
}
