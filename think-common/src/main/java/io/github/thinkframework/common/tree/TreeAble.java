package io.github.thinkframework.common.tree;

import java.io.Serializable;
import java.util.Collection;

public interface TreeAble<T,ID extends Serializable> {
    ID getId();
    ID getParentId();
    Collection<T> getChildren();

    default void add(T t){
        getChildren().add(t);
    }
}
