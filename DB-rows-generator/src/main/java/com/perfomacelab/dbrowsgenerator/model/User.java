package com.perfomacelab.dbrowsgenerator.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class User {
    private int id;
    private String email;
    private String password;
    private String nick;
    private String language;
    private String date;
    private boolean isEnable;
    private boolean isNotificationEnable;
}
