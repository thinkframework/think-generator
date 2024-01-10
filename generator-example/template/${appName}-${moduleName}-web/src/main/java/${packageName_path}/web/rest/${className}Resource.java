package ${packageName}.repository;

import ${packageName}.repository.criteria.${className}Criteria;
import ${packageName}.service.dto.${className}DTO;
import io.github.thinkframework.commons.web.rest.CriteriaResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ${clazz.remarks}.
 *
 * @author lixiaobin
 * @version 1.0.0
 * @since 1.0.0
 */
@RequestMapping("/api/${moduleName}/${moduleName}-${className}s")
public interface ${className}Resource extends CriteriaResource<${className}DTO, ${className}Criteria, ${id.type}> {

}
