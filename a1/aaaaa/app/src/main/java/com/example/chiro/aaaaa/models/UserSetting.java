package com.example.chiro.aaaaa.models;

public class UserSetting {
    private User user;
    private UserAccountSettings settings;

    public UserSetting(User user, UserAccountSettings settings) {
        this.user = user;
        this.settings = settings;
    }

    public UserSetting() {

    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAccountSettings getSettings() {
        return settings;
    }

    public void setSettings(UserAccountSettings settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        return "UserSettings{" +
                "user=" + user +
                ", settings=" + settings +
                '}';
    }
}

