package com.perfomacelab.dbrowsgenerator.view;

import com.perfomacelab.dbrowsgenerator.DAO.UserDAO;
import com.perfomacelab.dbrowsgenerator.service.UserService;
import com.perfomacelab.dbrowsgenerator.service.interfaces.IUserService;

public class MainView {
    public static void main(String[] args) {

        IUserService userService = new UserService(new UserDAO());
    }
}
