package com.perfomacelab.dbrowsgenerator.view;

import com.perfomacelab.dbrowsgenerator.DAO.UserDAO;
import com.perfomacelab.dbrowsgenerator.service.UserService;
import com.perfomacelab.dbrowsgenerator.service.interfaces.IUserService;

public class MainView {
    private static final int DEFAULT_USERS_COUNT = 20000;
    public static void main(String[] args) {
        addRows();
    }

    private static void addRows(){
        IUserService userService = new UserService(new UserDAO());
        int usersCountToAdd = DEFAULT_USERS_COUNT - userService.getUsersCount();
        if(usersCountToAdd > 0)
            while (usersCountToAdd > 0) {
                var user = userService.generateUser();
                userService.insertUser(user);
                usersCountToAdd--;
            }
    }

    private static void delete(){
        IUserService userService = new UserService(new UserDAO());
        userService.deleteTestUsers();
    }
}
