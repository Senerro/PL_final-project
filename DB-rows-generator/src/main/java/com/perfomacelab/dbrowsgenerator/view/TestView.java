package com.perfomacelab.dbrowsgenerator.view;

import com.perfomacelab.dbrowsgenerator.DAO.UserDAO;
import com.perfomacelab.dbrowsgenerator.service.UserService;
import com.perfomacelab.dbrowsgenerator.sometests.MyTests;

public class TestView {
    public static void main(String[] args) {
        checkRowCount();
    }
    private static void checkConnection(){
        boolean isConnection = MyTests.checkConnectionToDB();
        if (isConnection)
            System.out.println("Everything is fine, connection is correctly working");
        else System.out.println("Hmmm... Connection isn't working");
    }
    private static void checkRowCount(){
        if (MyTests.checkRowCountIs1890(new UserService(new UserDAO()).getUsersCount()))
            System.out.println("Everything is fine, service check the rows correctly");
        else System.out.println("Hmmm... service check the rows uncorrected");
    }
}
