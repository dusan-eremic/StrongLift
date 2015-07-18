package me.stronglift.stronglift.model;

import java.util.UUID;

/**
 *
 *
 * Created by Dusan Eremic.
 */
public class User {

    private String id;
    private String username;
    private String password;

    public User() {
        id = UUID.randomUUID().toString().replace("-", "");
    }

    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
