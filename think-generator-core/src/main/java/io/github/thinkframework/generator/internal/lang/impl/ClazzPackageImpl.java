package io.github.thinkframework.generator.internal.lang.impl;

import io.github.thinkframework.generator.internal.lang.ClazzPackage;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class ClazzPackageImpl implements ClazzPackage {
    private String name;

    public ClazzPackageImpl() {
    }

    public ClazzPackageImpl(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
