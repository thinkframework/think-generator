package io.github.thinkframework.common.tree;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SimpleTree implements TreeAble<SimpleTree,Integer>  {
    private Integer id;
    private Integer parentId;
    private List<SimpleTree> children;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getParentId() {
        return parentId;
    }

    @Override
    public List<SimpleTree> getChildren() {
        return children;
    }
}
