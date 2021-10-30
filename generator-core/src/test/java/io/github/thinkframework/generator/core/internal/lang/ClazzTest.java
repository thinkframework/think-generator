package io.github.thinkframework.generator.core.internal.lang;

import io.github.thinkframework.generator.core.internal.ClazzIntrospector;
import org.junit.Assert;
import org.junit.Test;

public class ClazzTest {

    @Test
    public void test(){
        Clazz clazz = ClazzIntrospector.getClazz(Person.class);
        Assert.assertEquals(2,clazz.getFields().size());
        Assert.assertEquals(4,clazz.getMethods().size());
    }

    /**
     * aperson
     */
    class Person{

        /**
         * aid
         */
        private Integer id;
        /**
         * aname
         */
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
