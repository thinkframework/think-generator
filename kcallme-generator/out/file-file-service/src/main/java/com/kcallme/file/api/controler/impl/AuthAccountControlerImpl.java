package com.kcallme.file.web.rest.impl;

import com.kcallme.file.service.AuthAccountPriorityService;
import com.kcallme.file.service.dto.AuthAccountPriorityDTO;
import com.kcallme.file.visitor.AuthAccountPriorityVisitorFactory;
import com.kcallme.file.visitor.impl.AuthAccountPriorityTreeVisitor;
import com.kcallme.file.web.rest.AuthAccountResource;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Api(tags = {"账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限"})
@RequestMapping("/api")
@RestController
public class AuthAccountControlerImpl implements AuthAccountControler {

    @Autowired
    private AuthAccountPriorityService authPriorityService;

    @Autowired
    private AuthAccountPriorityVisitorFactory authPriorityVisitorFactory;

    /**
     * 权限树
     *
     * @param id 权限ID
     * @return 权限树
     */
    @Override
    public ResponseEntity<List<AuthAccountPriorityDTO>> findPriorityById(@PathVariable(name = "id") Long id) {
        AuthAccountPriorityTreeVisitor authPriorityTreeVisitor = authPriorityVisitorFactory.authPriorityTreeVisitor();
        return ResponseEntity.ok(authPriorityService.findAll()
            .stream().map(authPriorityTreeVisitor::execute)
            .collect(Collectors.toList()));
    }
}
