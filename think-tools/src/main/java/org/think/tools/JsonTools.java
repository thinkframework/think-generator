package org.think.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class JsonTools {
    private final Logger log = LoggerFactory.getLogger(JsonTools.class);

    public void json(String json){
        Pattern pattern = Pattern.compile("(\\{.*\\})");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()){
            log.debug(matcher.group());
            log.debug(matcher.group(0));
            log.debug(matcher.group(1));
        }
        json.replace("decimal")
    }

}
