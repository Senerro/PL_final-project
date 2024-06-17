package com.perfomacelab.dbrowsgenerator.DAO;

import com.perfomacelab.dbrowsgenerator.DAO.conection.ConnectionPool;
import com.perfomacelab.dbrowsgenerator.DAO.conection.ConnectionSettings;

import java.sql.Connection;

public abstract class AbstractDAO {
    protected final ConnectionSettings connectionSettings = new ConnectionSettings("src//main//resources//database.properties");
    protected final ConnectionPool connectionPool = ConnectionPool.getConnectionPool(5, connectionSettings);
    protected Connection connection;
}
