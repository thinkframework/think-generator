package io.github.thinkframework.generator.core.design.visitor;

import java.io.File;
import java.io.IOException;

public interface GeneratorVisitor {

    void vistor(File file) throws IOException;
}
