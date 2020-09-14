package io.github.thinkframework.generator.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * jsontomap
 * 不支持嵌套
 * @author hdhxby
 */
public class JsonUtils {

    static String regex = "\\s*(\\\"(?<key>\\w+)\\\")\\s*:\\s*(\\\"(?<value>\\w+)\\\"|\\d+|null)\\s*\\,?";

    public static Map<String,String> toMap(String raw){
        Map<String,String> map = new HashMap<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(raw);
        while(matcher.find()){
            map.put(matcher.group("key"),matcher.group("value"));
        }
        return map;
    }

}
