package io.github.thinkframework.swing.log;

import java.io.IOException;
import java.io.OutputStream;

public enum  SwingTarget  {
    SystemOut("System.out", new OutputStream() {
        public void write(int b) throws IOException {
            System.out.write(b);
        }

        public void write(byte[] b) throws IOException {
            System.out.write(b);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            System.out.write(b, off, len);
        }

        public void flush() throws IOException {
            System.out.flush();
        }
    }),
    SystemErr("System.err", new OutputStream() {
        public void write(int b) throws IOException {
            System.err.write(b);
        }

        public void write(byte[] b) throws IOException {
            System.err.write(b);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            System.err.write(b, off, len);
        }

        public void flush() throws IOException {
            System.err.flush();
        }
    });

    private final String name;
    private final OutputStream stream;

    public static SwingTarget findByName(String name) {
        SwingTarget[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            SwingTarget target = arr$[i$];
            if (target.name.equalsIgnoreCase(name)) {
                return target;
            }
        }

        return null;
    }

    private SwingTarget(String name, OutputStream stream) {
        this.name = name;
        this.stream = stream;
    }

    public String getName() {
        return this.name;
    }

    public OutputStream getStream() {
        return this.stream;
    }

    public String toString() {
        return this.name;
    }
}
