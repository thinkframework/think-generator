package io.github.thinkframework.swing.log;

import ch.qos.logback.core.OutputStreamAppender;

import java.io.OutputStream;

public class SwingAppender<E> extends OutputStreamAppender<E> {

    protected SwingTarget target;
    protected boolean withJansi;

    public SwingAppender() {
        this.target = SwingTarget.SystemOut;
        this.withJansi = false;
    }

    public void start() {
        OutputStream targetStream = this.target.getStream();
        this.setOutputStream(targetStream);
        super.start();
    }
}
