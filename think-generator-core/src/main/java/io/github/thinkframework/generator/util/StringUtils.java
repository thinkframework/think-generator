package io.github.thinkframework.generator.util;

import java.util.Optional;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String camelCase(String sqlName) {
        if (sqlName == null || sqlName.length() == 0) {
            //fixme 外键 空指针的情况 lixiaobin
            return "";
        }
        String[] strs = sqlName.toLowerCase().split("_");
        String result = "";
        if (strs.length > 1) {
            for (int i = 0; i < strs.length; i++) {
                if (strs[i] == null || strs[i].length() == 0) {
                    continue;
                }
                result += strs[i].substring(0, 1).toUpperCase();
                result += strs[i].substring(1).toLowerCase();
            }
        } else {
            result = sqlName.substring(0, 1).toUpperCase() + sqlName.substring(1).toLowerCase();
        }
        return result;
    }

    public static String underScoreCase(String className) {
        if (!Optional.ofNullable(className).isPresent()) {
            return "";
        }
        if(className.indexOf("_") > -1 || className.matches("^[A-Z]+$")){
            return className;
        }
        return className.replaceAll("[A-Z]","_$0").toUpperCase();
    }


    public static String classNameWithSpace(String sqlName) {
        String[] strs = sqlName.toLowerCase().split("_");
        String result = "";
        if (strs.length > 1) {
            for (int i = 0; i < strs.length; i++) {
                if (i != 0) {
                    result += " ";
                }
                result += strs[i].substring(0, 1).toUpperCase();
                result += strs[i].substring(1).toLowerCase();
            }
        } else {
            result = sqlName.substring(0, 1).toUpperCase() + sqlName.substring(1).toLowerCase();
        }
        return result;
    }


    public static String fieldName(String sqlName) {
        String[] strs = sqlName.toLowerCase().split("_");
        String result = "";
        if (strs.length > 1) {
            if (strs[0].length() > 0) {
                result += strs[0].substring(0, 1).toLowerCase();
                result += strs[0].substring(1).toLowerCase();
            }
            for (int i = 1; i < strs.length; i++) {
                result += strs[i].substring(0, 1).toUpperCase();
                result += strs[i].substring(1).toLowerCase();
            }
        } else {
            Pattern a = Pattern.compile("[a-z]");
            Pattern b = Pattern.compile("[A-Z]");
            if (a.matcher(sqlName).find() && b.matcher(sqlName).find()) {
                result = sqlName.substring(0, 1).toLowerCase() + sqlName.substring(1);
            } else {
                result = sqlName.substring(0, 1).toLowerCase() + sqlName.substring(1).toLowerCase();
            }
        }
        return result;
    }


    public static String methodName(String sqlName) {
        String[] strs = sqlName.toLowerCase().split("_");
        String result = "";
        if (strs.length > 1) {
            for (int i = 0; i < strs.length; i++) {
                result += strs[i].substring(0, 1).toUpperCase();
                result += strs[i].substring(1).toLowerCase();
            }
        } else {
            Pattern a = Pattern.compile("[a-z]");
            Pattern b = Pattern.compile("[A-Z]");
            if (a.matcher(sqlName).find() && b.matcher(sqlName).find()) {
                result = sqlName.substring(0, 1).toUpperCase() + sqlName.substring(1);
            } else {
                result = sqlName.substring(0, 1).toUpperCase() + sqlName.substring(1).toLowerCase();
            }
        }
        return result;
    }



    public static String capitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final char firstChar = str.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            // already capitalized
            return str;
        }

        return new StringBuilder(strLen)
            .append(Character.toTitleCase(firstChar))
            .append(str.substring(1))
            .toString();
    }

    //
    public static String uncapitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final char firstChar = str.charAt(0);
        if (Character.isLowerCase(firstChar)) {
            // already uncapitalized
            return str;
        }

        return new StringBuilder(strLen)
            .append(Character.toLowerCase(firstChar))
            .append(str.substring(1))
            .toString();
    }

    /**
     * 表名称的前缀
     *
     * @param prefixs
     * @param string
     * @return
     */
    public static String replacePrefix(String[] prefixs, String string) {
        if (string == null) {
            return "";
        }
        for (String prefix : prefixs) {
            if (string.toUpperCase().startsWith(prefix)) {
                //忽略大小写,不能用replace
                string = string.substring(prefix.length());
                break;
            }
        }
        return string;
    }
//
//
//
//    public static String getSimpleClassName(String clazz) {
//		if(clazz == null) return null;
//		if(clazz.lastIndexOf(".") >= 0) {
//			return clazz.substring(clazz.lastIndexOf(".")+1);
//		}
//		return clazz;
//	}
//
//    public static String removePrefix(String str,String prefix,boolean ignoreCase) {
//		if(str == null) return null;
//		if(prefix == null) return str;
//		if(ignoreCase) {
//			if(str.toLowerCase().startsWith(prefix.toLowerCase())) {
//				return str.substring(prefix.length());
//			}
//		}else {
//			if(str.startsWith(prefix)) {
//				return str.substring(prefix.length());
//			}
//		}
//		return str;
//	}
//
//    public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
//		for (int j = 0; j < substring.length(); j++) {
//			int i = index + j;
//			if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//    public static String toUnicode(String str){
//        String result="";
//        if(str != null){
//	        for (int i = 0; i < str.length(); i++){
//	            int chr1 = (char) str.charAt(i);
//	            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)
//	                result+="\\u" + Integer.toHexString(chr1);
//	            }else{
//	                result+=str.charAt(i);
//	            }
//	        }
//        }
//        return result;
//    }

}

