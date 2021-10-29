package io.github.thinkframework.generator.core.internal.adapter;

import io.github.thinkframework.generator.core.internal.proxy.Remakrs;

/**
 * 注释字段
 */
public class AbstractAdapter implements Remakrs {

    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
