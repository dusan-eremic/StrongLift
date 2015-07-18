package me.stronglift.stronglift.rest;

import android.util.Base64;

import me.stronglift.stronglift.model.User;

/**
 *
 *
 * Created by Dusan Eremic.
 */
class AuthBasic {

    public static final String BASIC = "Basic ";
    private String username;
    private String password;

    public AuthBasic(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthBasic(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    @Override
    public String toString() {

        StringBuilder credentials = new StringBuilder();
        credentials.append(username).append(":").append(password);

        String encodedCredentials = Base64.encodeToString(credentials.toString().getBytes(), Base64.DEFAULT);

        return BASIC + encodedCredentials;
    }
}
