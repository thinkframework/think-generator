package io.github.thinkframework.generator.command;

/**
 * 命令接口
 *
 * @author lixiaobin
 */
public interface Command {
    /**
     * 执行操作
     */
    void execute();

    /**
     * 撤销操作
     */
    void unDo();
}
