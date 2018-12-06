package org.think.generator.util;

import org.think.generator.context.GeneratorContext;
import org.think.generator.exception.GeneratorRuntimeException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库类型映射
 * 做一些适当的修改
 * @author lixiaobin
 */
public class TypesUtils {

	private final static Map<Integer,Class> map = new HashMap<Integer, Class>();
	private final static Map<Class,Class> classConvert = new HashMap<Class, Class>();

	/**
	 * 日期时间类型转换为JDK8的类型.
	 */
	static {

		map.put(Types.CHAR,String.class);
		map.put(Types.VARCHAR,String.class);
		map.put(Types.LONGVARCHAR,String.class);

		map.put(Types.NUMERIC,BigDecimal.class);
		map.put(Types.DECIMAL,BigDecimal.class);

		map.put(Types.BIT,Boolean.class);

		map.put(Types.TINYINT,Integer.class);
		map.put(Types.SMALLINT,Integer.class);
		map.put(Types.INTEGER,Integer.class);

		map.put(Types.BIGINT,Long.class);

		map.put(Types.REAL,Float.class);

		map.put(Types.FLOAT,Double.class);
		map.put(Types.DOUBLE,Double.class);

		map.put(Types.BINARY,byte[].class);
		map.put(Types.VARBINARY,byte[].class);
		map.put(Types.LONGVARBINARY,byte[].class);

		map.put(Types.DATE,Date.class);

		map.put(Types.TIME,Time.class);

		map.put(Types.TIMESTAMP,Timestamp.class);
//不支持的数据类型
//		next.put(Types.NULL,String.class);
//		next.put(Types.OTHER,String.class);
//		next.put(Types.JAVA_OBJECT,String.class);
//		next.put(Types.DISTINCT,String.class);
//		next.put(Types.STRUCT,String.class);
//		next.put(Types.ARRAY,String.class);
//		next.put(Types.BLOB,String.class);
//		next.put(Types.CLOB,String.class);
//		next.put(Types.REF,String.class);
//		next.put(Types.DATALINK,String.class);
//		next.put(Types.BOOLEAN,String.class);
//		next.put(Types.ROWID,String.class);
//		next.put(Types.NCHAR,String.class);
//		next.put(Types.NVARCHAR,String.class);
//		next.put(Types.LONGNVARCHAR,String.class);
//		next.put(Types.NCLOB,String.class);
//		next.put(Types.SQLXML,String.class);
//		next.put(Types.REF_CURSOR,String.class);
//		next.put(Types.TIME_WITH_TIMEZONE,String.class);
//		next.put(Types.TIMESTAMP_WITH_TIMEZONE,String.class);

		//JDK8的时间类型
		map.put(Types.DATE,LocalDate.class);
//		next.put(Types.TIME,LocalTime.class);
		map.put(Types.TIMESTAMP,Instant.class);
	}

	public static Class dataType(Integer dataType){
		Class clazz = map.get(dataType);
		return clazz != null ? clazz : Object.class;
	}

	protected static Class convertJavaClass(Class clazz){
		Properties properties = GeneratorContext.getContext().getProperties();
		Iterator iterator = properties.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();
			if(key.startsWith("types")){
				try {
					Class keyClass = Class.forName(key.substring("types".length()));
					Class valueClass = Class.forName(value);
					classConvert.put(keyClass,valueClass);
				}catch (ClassNotFoundException e){
					throw new GeneratorRuntimeException("类不存在",e);
				}
			}
		}
		Class tmp = classConvert.get(clazz);
		if (tmp != null) {
			return tmp;
		}else{
			return clazz;
		}
	}

	protected static Class convertTSClass(Class clazz){
		Properties properties = GeneratorContext.getContext().getProperties();
		Iterator iterator = properties.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry entry = (Map.Entry)iterator.next();
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();
			if(key.startsWith("types")){
				try {
					Class keyClass = Class.forName(key.substring("types".length()));
					Class valueClass = Class.forName(value);
					classConvert.put(keyClass,valueClass);
				}catch (ClassNotFoundException e){
					throw new GeneratorRuntimeException("类不存在",e);
				}
			}
		}
		Class tmp = classConvert.get(clazz);
		if (tmp != null) {
			return tmp;
		}else{
			return clazz;
		}
	}

	public static String ConvertTypeScript(Integer dataType){
		Map<Integer,String> typescript = new HashMap<Integer, String>();

		typescript.put(Types.CHAR,"string");
		typescript.put(Types.VARCHAR,"string");
		typescript.put(Types.LONGVARCHAR,"string");

		typescript.put(Types.BIGINT,"number");
		typescript.put(Types.NUMERIC,"number");
		typescript.put(Types.DECIMAL,"number");

		typescript.put(Types.DATE,"Date");

		String clazz = typescript.get(dataType);
		//其他数据类型
		return clazz != null ? clazz : "any";

	}
}
