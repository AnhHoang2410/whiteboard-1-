package com.example.chiro.aaaaa.models;

public class UserAccountSettings {
    private String description;
    private String Course;
    private String display_name;
    private long Friend;
    private long posts;
    private String profile_photo;
    private String email;
    private String username;

    public UserAccountSettings(String description, String course,
                               long friend, long posts, String profile_photo, String email, String username) {
        this.description = description;
        Course = course;
        this.display_name = display_name;
        Friend = friend;
        this.posts = posts;
        this.profile_photo = profile_photo;
        this.email = email;
        this.username = username;
    }



    public UserAccountSettings() { }
    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public long getFriend() {
        return Friend;
    }

    public void setFriend(long friend) {
        Friend = friend;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserAccountSettings{" +
                "description='" + description + '\'' +
                ", Course='" + Course + '\'' +
                ", display_name='" + display_name + '\'' +
                ", Friend=" + Friend +
                ", profile_photo='" + profile_photo + '\'' +
                ", email='" + email + '\'' +
                ", posts='" + posts + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
