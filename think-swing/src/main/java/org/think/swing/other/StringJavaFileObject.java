package org.think.swing.other;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * @author lixiaobin
 * @since 2017/3/24
 */
public class StringJavaFileObject extends SimpleJavaFileObject{
    /**
     * Construct a SimpleJavaFileObject of the given kind and with the
     * given URI.
     *
     * @param uri  the URI for this file object
     * @param kind the kind of this file object
     */
    protected StringJavaFileObject(URI uri, Kind kind) {
        super(uri, kind);
    }
}
