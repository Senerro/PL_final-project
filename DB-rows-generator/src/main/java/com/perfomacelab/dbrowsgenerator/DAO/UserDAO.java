package com.perfomacelab.dbrowsgenerator.DAO;

import com.perfomacelab.dbrowsgenerator.DAO.conection.ConnectionPool;
import com.perfomacelab.dbrowsgenerator.DAO.conection.ConnectionSettings;
import com.perfomacelab.dbrowsgenerator.DAO.interfaces.IUserDAO;
import com.perfomacelab.dbrowsgenerator.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO implements IUserDAO {
    private final ConnectionSettings connectionSettings = new ConnectionSettings("src//main//resources//database.properties");
    private final ConnectionPool connectionPool = ConnectionPool.getConnectionPool(5, connectionSettings);
    private Connection connection;

    @Override
    public int getCountOfUsers() {
        String query = "select count(*)  from public.boomq_user bu";
        connection = connectionPool.getConnection();
        int rows = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                rows = Integer.parseInt(resultSet.getString("count"));
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            connectionPool.returnConnection(connection);
            throw new RuntimeException(e);
        }
        return rows;
    }

    @Override
    public boolean insertUser(User user) {
        return false;
    }

    @Override
    public int deleteTestUsers() {
        return 0;
    }
}
