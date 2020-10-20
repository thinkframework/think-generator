package io.github.thinkframework.jdbc.pdm;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库类型映射
 * 做一些适当的修改
 * @author lixiaobin
 */
public class TypesHelper {

	private final static Map<String,String> typenames = new HashMap();
	private final static Map<String,Integer> datetypes = new HashMap();

	/**
	 * 日期时间类型转换为JDK8的类型.
	 */
	static {
		typenames.put("1","TINYINT");//布尔型
		datetypes.put("1", Types.TINYINT);//布尔型
		typenames.put("int","INT");//4字节有符号整数
		datetypes.put("int",Types.INTEGER);//4字节有符号整数
		typenames.put("3","TINYINT");//1字节有符号整数
		datetypes.put("3",Types.TINYINT);//1字节有符号整数
		typenames.put("4","TINYINT");//1字节无符号整数
		datetypes.put("4",Types.TINYINT);
		typenames.put("5","SMALLINT");//2字节有符号整数
		datetypes.put("5",Types.SMALLINT);
		typenames.put("6","SMALLINT");//2字节无符号整数
		datetypes.put("6",Types.SMALLINT);//2字节无符号整数
		typenames.put("7","DOUBLE");//双精度浮点数
		datetypes.put("7",Types.DOUBLE);//双精度浮点数
		typenames.put("numeric","DECIMAL");//固定小数位数浮点数
		datetypes.put("numeric",Types.DECIMAL);//固定小数位数浮点数
		typenames.put("varchar","VARCHAR");//日期时间型
		datetypes.put("varchar",Types.VARCHAR);//日期时间型
		typenames.put("10","TEXT");//文本备注
		datetypes.put("10",Types.LONGVARCHAR);//文本备注
//		typenames.put("11",String.class);//二进制OLE
//		datetypes.put("11",String.class);//二进制OLE
//		typenames.put("12",String.class);//表
//		datetypes.put("12",String.class);//表
		typenames.put("13","VARCHAR");//长整数
		datetypes.put("13",Types.VARCHAR);//长整数
//		typenames.put("14",String.class);//GUID
//		datetypes.put("14",String.class);//GUID
		typenames.put("15","VARCHAR");//无符号长整数
		datetypes.put("15",Types.VARCHAR);//无符号长整数
		typenames.put("","VARCHAR");//字符串
		datetypes.put("",Types.VARCHAR);//字符串
	}

	public static Integer datetypes(String type){
		return datetypes.get(type);
	}
    public static String typenames(String type){
        return typenames.get(type);
    }
}
