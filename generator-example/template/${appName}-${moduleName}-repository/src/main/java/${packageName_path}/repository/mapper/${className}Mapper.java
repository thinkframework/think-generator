package ${packageName}.repository.criteria;

import io.github.thinkframework.commons.repository.criteria.AbstractCriteria;
import io.github.thinkframework.commons.repository.filter.Filter;
import io.github.thinkframework.commons.repository.filter.InstantFilter;
import io.github.thinkframework.commons.repository.filter.LongFilter;
import io.github.thinkframework.commons.repository.filter.StringFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 *
 * @author
 * @version 1.0.0
 * @since 1.0.0
 */
public interface  ${className}Mapper {

    int insert(${className} ${className?uncap_first});

    int update(${className} ${className?uncap_first});

    int delete(${className} ${className?uncap_first});

    int deleteAll();

    int findById(${id.type} id);

    int existsById(${id.type} id);

    List<$className> findAll();

    List<$className> findAllSorted();

    List<$className> findAllPaged();

}
