package org.think.generator.lang.reflect.impl;

import org.think.generator.lang.reflect.ClazzMember;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class ClazzMemberImpl implements ClazzMember {
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
