package org.think.tools;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonToolsTest {
    @Test
    public void test(){
        String s = "{\"id\":\"integer\"}";
        new JsonTools().json(s);
    }

    @Test
    public void test2(){
        String sr = "dada ada adad adsda ad asdda adr3 fas daf fas fdsf 234 adda";
        //包含两个匹配组，一个是三个字符，一个是匹配四个字符
        Pattern pet = Pattern.compile("\\b(\\w{3}) *(\\w{4})\\b");
        Matcher match = pet.matcher(sr);
        int countAll = match.groupCount();//2
        while (match.find()) {
            System.out.print("匹配组结果：");
            for (int i = 0; i < countAll; i++) {
                System.out.print(String.format("\n\t第%s组的结果是:%s",i+1,match.group(i + 1)));
            }
            System.out.print("\n匹配的整个结果:");
            System.out.println(match.group());
        }
    }

    @Test
    public void test3(){
        String sr = "dada ada adad adsda ad asdda adr3 fas daf fas fdsf 234 adda";
        Pattern pet = Pattern.compile("\\b(\\w{3}) *(\\w{4})\\b");
        Matcher match = pet.matcher(sr);
        MatchResult ms = null;
        while (match.find()) {
            ms = match.toMatchResult();
            System.out.print("匹配对象的组结果：");
            for (int i = 0; i < ms.groupCount(); i++) {
                System.out.print(String.format("\n\t第%s组的结果是:%s",i+1,ms.group(i + 1)));
            }
            System.out.print("\n匹配的整个结果:");
            System.out.println(ms.group());
        }
    }

    @Test
    public void test4(){
        String filenameStr = "aa/haha.exe";
        Pattern filenameP = Pattern.compile("^.+/(?<filenamePart>.+)$");
        Matcher filenameMatcher = filenameP.matcher(filenameStr);
        boolean foundFilename = filenameMatcher.matches();

        System.out.println(filenameMatcher);
        System.out.println(foundFilename);

        String onlyFilename = filenameMatcher.replaceFirst("${filenamePart}");
        System.out.println(onlyFilename);
    }

    public void test5(){
        List list = new ArrayList();

//        list.subList()
    }
}
