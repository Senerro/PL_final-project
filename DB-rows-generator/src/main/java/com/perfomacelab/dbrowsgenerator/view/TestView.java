package com.perfomacelab.dbrowsgenerator.view;

import com.perfomacelab.dbrowsgenerator.DAO.TestUserDao;
import com.perfomacelab.dbrowsgenerator.DAO.UserDAO;
import com.perfomacelab.dbrowsgenerator.DAO.helpers.Generator;
import com.perfomacelab.dbrowsgenerator.DAO.helpers.QueryUtils;
import com.perfomacelab.dbrowsgenerator.model.User;
import com.perfomacelab.dbrowsgenerator.service.TestUserService;
import com.perfomacelab.dbrowsgenerator.service.UserService;
import com.perfomacelab.dbrowsgenerator.service.interfaces.IUserService;

public class TestView {
    public static void main(String[] args) {
        checkGeneratedUser();
    }

    private static void checkQueryGenerator(){
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

        System.out.println(QueryUtils.generateFullInsertQueryForBoomqUser(user));
    }

    public static void checkRowInserting(){
        IUserService service = new TestUserService(new UserDAO());
        service.insertUser(service.generateUser());
    }

    public static void checkGeneratedUser(){
        IUserService service = new UserService(new TestUserDao());
        System.out.println(service.generateUser());
    }
}
