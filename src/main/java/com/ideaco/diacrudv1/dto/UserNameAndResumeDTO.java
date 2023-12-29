package com.ideaco.diacrudv1.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserNameAndResumeDTO {
    private String userName;
    private String userResume;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserResume() {
        return userResume;
    }

    public void setUserResume(String userResume) {
        this.userResume = userResume;
    }
}
