package ${packageName}.web.rest.impl;

import ${packageName}.service.${className}PriorityService;
import ${packageName}.service.dto.${className}PriorityDTO;
import ${packageName}.visitor.${className}PriorityVisitorFactory;
import ${packageName}.visitor.impl.${className}PriorityTreeVisitor;
import ${packageName}.web.rest.${className}Resource;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ${clazz.remarks}.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@Api(tags = {"${clazz.remarks}"})
@RequestMapping("/api")
@RestController
public class ${className}ControlerImpl implements ${className}Controler {

    @Autowired
    private ${className}PriorityService authPriorityService;

    @Autowired
    private ${className}PriorityVisitorFactory authPriorityVisitorFactory;

    /**
     * 权限树
     *
     * @param id 权限ID
     * @return 权限树
     */
    @Override
    public ResponseEntity<List<${className}PriorityDTO>> findPriorityById(@PathVariable(name = "id") Long id) {
        ${className}PriorityTreeVisitor authPriorityTreeVisitor = authPriorityVisitorFactory.authPriorityTreeVisitor();
        return ResponseEntity.ok(authPriorityService.findAll()
            .stream().map(authPriorityTreeVisitor::execute)
            .collect(Collectors.toList()));
    }
}
