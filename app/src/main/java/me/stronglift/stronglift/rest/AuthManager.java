package me.stronglift.stronglift.rest;

/**
 * Created by dusan on 16/07/15.
 */
public class AuthManager {

    public static AuthBasic getUser() {
        return new AuthBasic("dusan", "test123");
    }
}
