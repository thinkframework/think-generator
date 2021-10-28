package io.github.thinkframework.generator.core.util;

import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 下换线转小驼峰
     * @param underScore 下划线
     * @return
     */
    public static String lowerCamel(String underScore) {
        return
            Stream.of(underScore.split("_"))
            .skip(1)
            .map(arrays -> arrays.length() <= 1 ? arrays.toUpperCase() : arrays.substring(0, 1).toUpperCase() + arrays.substring(1).toLowerCase())
            .reduce(Stream.of(underScore.toLowerCase().split("_")).findFirst().orElse("").toLowerCase(),String::concat);
    }

    /**
     * 下换线转大驼峰
     * @param underScore 下划线
     * @return
     */
    public static String upperCamel(String underScore) {
        return Stream.of(underScore.split("_"))
            .map(arrays -> arrays.length() <= 1 ? arrays.toUpperCase() : arrays.substring(0, 1).toUpperCase() + arrays.substring(1).toLowerCase())
            .collect(Collectors.joining());
    }

    /**
     * 下划线
     * @param upperCamel
     * @return
     */
    public static String lowerUnderScore(String upperCamel) {
        return Optional.ofNullable(upperCamel).orElse("")
            .replaceAll("[A-Z]","_$0").toLowerCase();
    }
    /**
     * 连接线
     * @param upperCamel
     * @return
     */
    public static String lowerHyphen(String upperCamel) {
        return Optional.ofNullable(upperCamel).orElse("")
            .replaceAll("[A-Z]","-$0").toLowerCase();
    }
}

