package com.perfomacelab.dbrowsgenerator.view;

import com.perfomacelab.dbrowsgenerator.DAO.UserDAO;
import com.perfomacelab.dbrowsgenerator.service.UserService;
import com.perfomacelab.dbrowsgenerator.service.interfaces.IUserService;
import com.perfomacelab.dbrowsgenerator.threads.InsertThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainView {
    private static final int DEFAULT_USERS_COUNT = 123000;
    private static final IUserService userService = new UserService(new UserDAO());
    private static AtomicInteger atom;
    public static void main(String[] args) {
        addRows();
    }

    private static void addRows(){
        int usersCountToAdd = DEFAULT_USERS_COUNT - userService.getUsersCount();
        atom = new AtomicInteger(usersCountToAdd);
        Runnable runt = MainView::run;

        if(atom.get() > 0)
            while (atom.get() > 0)
                runt.run();

    }

    private static void run() {
        var user = userService.generateUser();
        userService.insertUser(user);
        atom.set(atom.addAndGet(-1));
    }
}
