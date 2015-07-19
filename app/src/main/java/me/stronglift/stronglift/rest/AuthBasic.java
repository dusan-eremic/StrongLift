package me.stronglift.stronglift.rest;

import android.util.Base64;

import me.stronglift.stronglift.model.User;

/**
 * Pomoćna klasa za generisanje AuthBasic autorizacije.
 * <p>
 * Created by Dusan Eremic.
 */
class AuthBasic {

    public static final String BASIC = "Basic ";

    /**
     * Korisničko ime
     */
    private String username;

    /**
     * Lozinka
     */
    private String password;

    public AuthBasic(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Konstruktor sa parametrima
     *
     * @param user Korisnik
     */
    public AuthBasic(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    /**
     * Metoda vraća username i password kao base64 enkodirani string.
     *
     * @return
     */
    @Override
    public String toString() {

        StringBuilder credentials = new StringBuilder();
        credentials.append(username).append(":").append(password);

        String encodedCredentials = Base64.encodeToString(credentials.toString().getBytes(), Base64.DEFAULT);

        return BASIC + encodedCredentials;
    }
}
