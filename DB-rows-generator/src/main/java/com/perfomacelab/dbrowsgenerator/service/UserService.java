package com.perfomacelab.dbrowsgenerator.service;

import com.perfomacelab.dbrowsgenerator.DAO.interfaces.IUserDAO;
import com.perfomacelab.dbrowsgenerator.model.User;
import com.perfomacelab.dbrowsgenerator.service.interfaces.IUserService;

public class UserService implements IUserService {
    private final IUserDAO dao;
    private int usersCount;

    public UserService(IUserDAO dao) {
        this.dao = dao;
    }

    @Override
    public int getUsersCount() {
        usersCount = dao.getCountOfUsers();
        return usersCount;
    }

    @Override
    public boolean insertUser(User user) {
        return dao.insertUser(user);
    }

    @Override
    public boolean deleteTestUsers() {
        return dao.deleteTestUsers() == usersCount;
    }

    @Override
    public User generateUser() {
        return null;
    }
}
