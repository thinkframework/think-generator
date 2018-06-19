package org.think.swing.jdbc.pdm;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by lixiaobin on 2017/4/25.
 */
public class PDMDataSources implements DataSource{
    private InputStream inputStream;
    private String fileName;
    private File file;
    public PDMDataSources(){

    }

    public PDMDataSources(String fileName){

    }

    public PDMDataSources(File file){
        try {
            this.inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PDMDataSources(InputStream inputStream){
        this.inputStream = inputStream;
    }
    @Override
    public Connection getConnection() throws SQLException {
        if(fileName != null){
            return new PDMConnection(fileName);
        }
        return new PDMConnection(inputStream);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        try {
            setInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        setFile(new File(fileName));
    }
}
