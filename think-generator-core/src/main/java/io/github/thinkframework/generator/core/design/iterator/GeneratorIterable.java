package io.github.thinkframework.generator.core.design.iterator;

import java.util.Iterator;

public class GeneratorIterable implements Iterable {
    @Override
    public Iterator iterator() {
        return new Iterator(){

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
        };
    }
}
