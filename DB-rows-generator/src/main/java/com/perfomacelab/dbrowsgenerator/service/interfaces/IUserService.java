package com.perfomacelab.dbrowsgenerator.service.interfaces;

import com.perfomacelab.dbrowsgenerator.DAO.interfaces.IUserDAO;
import com.perfomacelab.dbrowsgenerator.model.User;

public interface IUserService {
    int getUsersCount();
    boolean insertUser(User user);

    boolean deleteTestUsers();

    User generateUser();

}
