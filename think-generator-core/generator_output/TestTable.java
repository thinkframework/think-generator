import lombok.Data;

package com.hdhxby.wrapper.pojo.dto.auth;

/**
 * .
 * @author unascribed
 * @since 1.0.0
 */
@Data
public class TestTable implements Serializable {

private static final long serialVersionUID = 1L;


    /**
     *
     */
    @Alias("")
    private String id;

    /**
     *
     */
    @Alias("缂栫爜")
    private String code;

    /**
     *
     */
    @Alias("鍚嶇О")
    private String name;
}
