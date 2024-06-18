package com.perfomacelab.dbrowsgenerator.DAO.helpers;


import com.perfomacelab.dbrowsgenerator.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class QueryUtils {
    public static String generateFullInsertQueryForBoomqUser(User user) {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO public.boomq_user VALUES ");
        addUserValuesToBuilder(user, builder);
        return builder.toString();
    }

    private static void addUserValuesToBuilder(User user, StringBuilder builder) {
        addValuesToBuilder(new ArrayList<>(Arrays.asList(
                String.valueOf(user.getId()), user.getEmail(), user.getPassword(),
                user.getLanguage(), String.valueOf(user.isNotificationEnable()),
                user.getDate(), String.valueOf(user.isNotificationEnable()),
                user.getNick())), builder);
    }

    private static void addValuesToBuilder(List<String> values, StringBuilder builder) {
        builder.append("( ");
        for (int i = 0; i < values.size(); i++) {
            if (Objects.equals(values.get(i), String.valueOf(true)))
                builder.append(true);
            else if (Objects.equals(values.get(i), String.valueOf(false)))
                builder.append(false);
            else
                try {
                    int tmp = Integer.parseInt(values.get(i));
                    builder.append(tmp);
                } catch (Exception e) {
                    builder.append("'").append(values.get(i)).append("'");
                }
            if (i + 1 < values.size())
                builder.append(", ");
        }

        builder.append(" )");
    }
}
