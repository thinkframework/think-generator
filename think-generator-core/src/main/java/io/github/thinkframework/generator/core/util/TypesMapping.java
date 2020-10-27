package io.github.thinkframework.generator.core.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库类型映射
 * 做一些适当的修改
 *
 * @author hdhxby
 */

class TypesMapping {

    private static TypesMapping instance = new TypesMapping();

    public  static TypesMapping getInstance(){
        return instance;
    }

    public final static Map<Integer, Class> map = new HashMap<Integer, Class>();

    /**
     * 日期时间类型转换为JDK8的类型.
     */
    static {
        map.put(Types.CHAR, String.class);
        map.put(Types.VARCHAR, String.class);
        map.put(Types.LONGVARCHAR, String.class);

        map.put(Types.NUMERIC, BigDecimal.class);
        map.put(Types.DECIMAL, BigDecimal.class);

        map.put(Types.BIT, Boolean.class);

        map.put(Types.TINYINT, Integer.class);
        map.put(Types.SMALLINT, Integer.class);
        map.put(Types.INTEGER, Integer.class);

        map.put(Types.BIGINT, Long.class);

        map.put(Types.REAL, Float.class);

        map.put(Types.FLOAT, Double.class);
        map.put(Types.DOUBLE, Double.class);

        map.put(Types.BINARY, byte[].class);
        map.put(Types.VARBINARY, byte[].class);
        map.put(Types.LONGVARBINARY, byte[].class);

        map.put(Types.DATE, Date.class);

        map.put(Types.TIME, Time.class);

        map.put(Types.TIMESTAMP, Timestamp.class);
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

    }

    public Class dataType(Integer dataType) {
        return map.getOrDefault(dataType,Object.class);
    }
}
