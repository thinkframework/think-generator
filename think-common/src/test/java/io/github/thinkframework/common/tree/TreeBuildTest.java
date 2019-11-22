package io.github.thinkframework.common.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TreeBuildTest {

    @Test
    public void test(){
        List<SimpleTree> list = new ArrayList<>();
        list.add(SimpleTree.builder().id(0).parentId(null).children(new ArrayList<>()).build());
        list.add(SimpleTree.builder().id(1).parentId(0).children(new ArrayList<>()).build());
        list.add(SimpleTree.builder().id(10).parentId(1).children(new ArrayList<>()).build());
        list.add(SimpleTree.builder().id(101).parentId(10).children(new ArrayList<>()).build());

        TreeBuild.build(list);

        TreeBuild.build2(list);
        log.debug("");


    }
}
