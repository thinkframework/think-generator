package io.github.thinkframework.generator.swing.core.util;

import java.io.File;
import java.io.IOException;

/**
 * 跨平台文件打开工具
 * @author lixiaobin
 */
public class GeneratorFileUtil {

    /**
     * 打开目录
     */
    public static void openDirectory(File file) throws RuntimeException {
        if (file == null){
            throw new NullPointerException("目录不存在");
        }
        if (!file.exists()){
            throw new NullPointerException("目录不存在");
        }
        String osName = System.getProperty("os.name");
        try {
            if ("Mac OS X".equals(osName)) {
                Runtime.getRuntime().exec("open "+file.getAbsolutePath());
            } else {
                Runtime.getRuntime().exec("cmd /c start "+file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 打开文件
     * @param file
     */
    public static void openFile(File file) throws RuntimeException {
        if (file == null){
            throw new NullPointerException("文件不存在");
        }
        if (!file.exists()){
            throw new NullPointerException("文件不存在");
        }
        String osName = System.getProperty("os.name");
        try {
            if ("Mac OS X".equals(osName)) {
                Runtime.getRuntime().exec("open "+file.getAbsolutePath());
            } else {
                Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler "+file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
