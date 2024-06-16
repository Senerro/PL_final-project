package com.perfomacelab.dbrowsgenerator.DAO.conection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConnectionSettings {
    private final String url;
    private final String password;
    private final String username;

    public ConnectionSettings(String path) {
        var props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(path))) {
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.url = props.getProperty("url");
        this.username = props.getProperty("username");
        this.password = props.getProperty("password");
    }

    public String password() {
        return password;
    }

    public String url() {
        return url;
    }

    public String username(){
        return username;
    }

}
