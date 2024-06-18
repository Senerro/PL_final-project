package com.perfomacelab.dbrowsgenerator.DAO.conection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedDeque;

@Slf4j
public class ConnectionPool {
    private final String url;
    private final String username;
    private final String password;
    private final ConcurrentLinkedDeque<Connection> freeConnections = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<Connection> occupiedConnections = new ConcurrentLinkedDeque<>();
    private static ConnectionPool connectionPool;
    public int maxSize;

    public static synchronized ConnectionPool getConnectionPool(int size, ConnectionSettings setting) {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool(size, setting);
        }
        return connectionPool;
    }

    private ConnectionPool(int size, ConnectionSettings setting) {
        this.maxSize = size;
        if (size <= 1)
            this.maxSize = 15;
        this.url = setting.getUrl();
        this.username = setting.getUsername();
        this.password = setting.getPassword();
        connectionPool = this;
    }

    public synchronized Connection getConnection() {
        Connection connection;

        connection = getPoolConnection();


        if (connection == null && occupiedConnections.size() < this.maxSize) {
            System.out.println("need to get connection from stack");
            connection = createPoolConnection();
            System.out.println("connecttion created");
        }

        if (connection == null) {
            try {
                System.out.println("wait connection");
                wait();
            } catch (InterruptedException e) {
                log.error("thread " + Thread.currentThread() + " wasn't able to wait free connection");
                throw new RuntimeException(e);
            }
            connection = getPoolConnection();
        }
        return connection;
    }

    private Connection createPoolConnection() {
        var connection = createConnection();
        occupiedConnections.add(connection);
        return connection;
    }

    private Connection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error("fail connection to database:\n"
                    + "url " + url + "\n"
                    + "username " + username + "\n"
                    + "password " + password);
            throw new RuntimeException(e);
        }
        return connection;
    }

    private Connection getPoolConnection() {
        Connection connection = null;
        if (!freeConnections.isEmpty()) {
            System.out.println("connecttion become free");
            connection = freeConnections.pop();
            occupiedConnections.add(connection);
        }
        return connection;
    }

    public synchronized void returnConnection(Connection connection) {
        occupiedConnections.remove(connection);
        freeConnections.push(connection);
        notify();
        if(occupiedConnections.isEmpty())
            notifyAll();
    }
}
