package io.github.thinkframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanDescriptor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SQL	MongoDB	Cassandra	Validations
 * String	String	String	required, minlength, maxlength, pattern
 * Integer	Integer	Integer	required, min, max
 * Long	Long	Long	required, min, max
 * BigDecimal	BigDecimal	BigDecimal	required, min, max
 * Float	Float	Float	required, min, max
 * Double	Double	Double	required, min, max
 * Enum	Enum		required
 * Boolean	Boolean	Boolean	required
 * LocalDate	LocalDate		required
 * Date	required
 * ZonedDateTime	ZonedDateTime		required
 * UUID	required
 * Blob	Blob		required, minbytes, maxbytes
 * AnyBlob	AnyBlob		required, minbytes, maxbytes
 * ImageBlob	ImageBlob		required, minbytes, maxbytes
 * TextBlob	TextBlob		required, minbytes, maxbytes
 * Instant	Instant	Instant	required
 */
public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Java类转JSON
     * @param clazz
     * @return
     */
    public static String clazzString(Class clazz) {
        BeanDescriptor beanDescriptor = new BeanDescriptor(clazz);
        StringBuilder stringBuilder = new StringBuilder("{");
//        try {
//            stringBuilder.append(Stream.of(Introspector.getBeanInfo(clazz).getPropertyDescriptors())
//                .filter(property -> !"class".equals(property.getName()))
//                .flatMap(property -> Stream.of(String.format("\"%s\":\"\"", property.getName(), "")))
//                .collect(Collectors.joining(","))
//            );;
//        }catch (IntrospectionException e){
//            log.error(e.getMessage(),e);
//        }

        stringBuilder.append(
            Stream.of(clazz.getDeclaredFields())
                .flatMap(field -> {
                    return Stream.of(String.format("\"%s\":\"%s\"", field.getName(), ""));
                })
                .collect(Collectors.joining(","))
        );
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    /**
     * 格式化Json
     * @param clazz
     * @return
     */
    public static String format(Class clazz){
        return clazzString(clazz).replaceAll("\\{","{\r\n")
            .replaceAll(",",",\r\n")
            .replaceAll("\\}","\r\n}");
    }


    public void json(String json) {
        Pattern pattern = Pattern.compile("(\")(\\w*\\d*})(\")\\s*:\\s(\"?)(\\w*\\d*})(\"?)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            log.debug(matcher.group());
            log.debug(matcher.group(0));
            log.debug(matcher.group(1));
        }
//        json.replace("decimal")
    }

}
