package me.stronglift.stronglift.rest;

import android.util.Base64;

/**
 * Created by dusan on 16/07/15.
 */
class AuthBasic {

    public static final String BASIC = "Basic ";
    private String username;
    private String password;

    public AuthBasic(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {

        StringBuilder credentials = new StringBuilder();
        credentials.append(username).append(":").append(password);

        String encodedCredentials = Base64.encodeToString(credentials.toString().getBytes(), Base64.DEFAULT);

        return BASIC + encodedCredentials;
    }
}
