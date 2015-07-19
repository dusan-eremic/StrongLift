package me.stronglift.stronglift.model;

import java.util.UUID;

/**
 * User model
 * <p>
 * Created by Dusan Eremic.
 */
public class User {

    /**
     * ID
     */
    private String id;

    /**
     * Korisničko ime
     */
    private String username;

    /**
     * Lozinka
     */
    private String password;

    /**
     * Konstruktor bez parametara
     */
    public User() {
        id = UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Konstruktor sa parametrima
     *
     * @param username Korisničko ime
     * @param password Lozinka
     */
    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    // ##### GET i SET metode #####

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

    // ##### GET i SET metode #####

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
