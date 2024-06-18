package com.perfomacelab.dbrowsgenerator.DAO;

import com.perfomacelab.dbrowsgenerator.DAO.conection.ConnectionPool;
import com.perfomacelab.dbrowsgenerator.DAO.conection.ConnectionSettings;
import com.perfomacelab.dbrowsgenerator.DAO.interfaces.IUserDAO;
import com.perfomacelab.dbrowsgenerator.model.User;

import java.sql.Connection;

public class TestUserDao extends AbstractDAO implements IUserDAO {
    @Override
    public int getCountOfUsers() {
        return 1890;
    }

    @Override
    public boolean insertUser(User user) {
        return true;
    }
}
