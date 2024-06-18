package com.perfomacelab.dbrowsgenerator.DAO.helpers;

import java.time.Instant;
import java.util.Random;

public class Generator {
    private static final String testMarker = "testGen";

    private static String generateLettersByLimits(int limit) {
        return new Random().ints('a', 'z')
                .limit(limit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static String testGenString(String string){
        return testMarker + string;
    }

    private static String generateUserEmail() {
        return  generateLettersByLimits(10) + '@' +
                generateLettersByLimits(5) + '.' +
                generateLettersByLimits(3);
    }

    private static String generatePassword(){
        return generateLettersByLimits(128-testMarker.length());
    }

    public static int userId() {
        return 0;
    }

    public static String userEmail() {
        return (testGenString(generateUserEmail()));
    }

    public static String userPass(){
        return (testGenString(generatePassword()));
    }

    public static String displayName(){
        return (testGenString(generateLettersByLimits(10)));
    }

    public static String userLanguage(){
        return "TG";
    }

    public static boolean randomBool(){
        return Math.random() > 0.5;
    }

    public static String date(){
        return Instant.now().toString()
                .replace('T', ' ')
                .substring(0, 23);
    }
}
