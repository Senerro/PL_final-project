package com.perfomacelab.dbrowsgenerator.DAO;

import com.perfomacelab.dbrowsgenerator.DAO.interfaces.IUserDAO;
import com.perfomacelab.dbrowsgenerator.DAO.helpers.QueryUtils;
import com.perfomacelab.dbrowsgenerator.model.User;
import lombok.extern.slf4j.Slf4j;
import java.sql.*;

@Slf4j
public class UserDAO extends AbstractDAO implements IUserDAO {
    @Override
    public int getCountOfUsers() {
        String query = "select count(*) from public.boomq_user bu";
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

        String query =  QueryUtils.generateFullInsertQueryForBoomqUser(user);
        connection = connectionPool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            connectionPool.returnConnection(connection);
            ps.close();
            return true;
        } catch (SQLException e) {
            log.error("Some error with table " + "\n"
                    + "query is " + query + "\n"
                    + "Exception is " + e);
            return false;
        }
    }
}
