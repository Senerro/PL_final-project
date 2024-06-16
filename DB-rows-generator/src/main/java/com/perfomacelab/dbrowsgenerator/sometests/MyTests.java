package com.perfomacelab.dbrowsgenerator.sometests;

import com.perfomacelab.dbrowsgenerator.DAO.conection.ConnectionPool;
import com.perfomacelab.dbrowsgenerator.DAO.conection.ConnectionSettings;

import java.sql.Connection;

public class MyTests {
    public static boolean checkConnectionToDB(){
        ConnectionSettings connectionSettings = new ConnectionSettings("src//main//resources//database.properties");
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool(5, connectionSettings);
        Connection connection = connectionPool.getConnection();
        return connection != null;
    }

    public static boolean checkRowCountIs1890(int size){
        return size==1890;
    }
}
