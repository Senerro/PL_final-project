package com.perfomacelab.dbrowsgenerator.threads;

import com.perfomacelab.dbrowsgenerator.DAO.UserDAO;
import com.perfomacelab.dbrowsgenerator.service.UserService;
import com.perfomacelab.dbrowsgenerator.service.interfaces.IUserService;

public class InsertThread implements Runnable{
    @Override
    public void run() {
        IUserService userService = new UserService(new UserDAO());
        var user = userService.generateUser();
        userService.insertUser(user);
    }
}
