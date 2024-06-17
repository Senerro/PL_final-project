package com.perfomacelab.dbrowsgenerator.service;

import com.perfomacelab.dbrowsgenerator.DAO.interfaces.IUserDAO;
import com.perfomacelab.dbrowsgenerator.model.User;
import com.perfomacelab.dbrowsgenerator.service.interfaces.IUserService;

public class TestUserService implements IUserService {
    private final IUserDAO dao;

    public TestUserService(IUserDAO dao) {
        this.dao = dao;
    }

    @Override
    public int getUsersCount() {
        return 0;
    }

    @Override
    public boolean insertUser(User user) {
        return dao.insertUser(user);
    }

    @Override
    public boolean deleteTestUsers() {
        return false;
    }

    @Override
    public User generateUser() {
        User user = User.builder()
                .id(4500)
                .email("test email")
                .password("test password")
                .language("EN")
                .isNotificationEnable(true)
                .date("test creation Date")
                .isEnable(false)
                .nick("test display Name")
                .build();
        dao.insertUser(user);
        return user;
    }
}
