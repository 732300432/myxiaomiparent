package com.cp.pojo;

/**
 * cp 2019-09-14  12:39
 */
public class UserInfo {
    private String email;
    private String username;
    private String gender;
    private String role;
    private String id;

    public UserInfo() {
    }

    public UserInfo(String email, String username, String gender, String role, String id) {
        this.email = email;
        this.username = username;
        this.gender = gender;
        this.role = role;
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
