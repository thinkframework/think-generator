package io.github.thinkframework.common.tree;


import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeBuild {

    private TreeBuild(){

    }

    public static <T extends TreeAble> void build(Collection<T> collectors){
        Map<Serializable,T> map = collectors.stream().collect(Collectors.toMap(TreeAble::getId, Function.identity()));
        collectors.forEach(child ->
            Optional.ofNullable(map.get(child.getParentId())).ifPresent(parent -> parent.add(child))
        );
    }


    public static <T extends TreeAble> Optional<T> build2(Collection<T> collectors){
        Map<Serializable,TreeAble> map = collectors.stream().collect(Collectors.toMap(TreeAble::getId, Function.identity()));
        return collectors.stream().map(child -> {
            Optional<TreeAble> optional = Optional.ofNullable(map.get(child.getParentId()));
            optional.ifPresent(parent -> parent.add(child));
            return child;
        }).filter(treeabel -> "".equals(treeabel.getId())).findAny();
    }

    public static void build2(Function function){

    }
}
