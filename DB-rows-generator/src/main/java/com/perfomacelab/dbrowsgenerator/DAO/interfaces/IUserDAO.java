package com.perfomacelab.dbrowsgenerator.DAO.interfaces;

import com.perfomacelab.dbrowsgenerator.model.User;

public interface IUserDAO {
    int getCountOfUsers();
    boolean insertUser(User user);
}
