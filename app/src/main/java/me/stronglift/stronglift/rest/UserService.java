package me.stronglift.stronglift.rest;

import me.stronglift.stronglift.model.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * REST servis za pristup User resursu.
 * <p>
 * Created by Dusan Eremic.
 */
public interface UserService {

    /**
     * Servis registruje novog korisnika na serveru.
     *
     * @param user     Korisnik koji se registruje.
     * @param callback Callback koji će biti pozvan nakon što Android
     *                 klijent dobije odgovor od servera.
     */
    @POST("/users/")
    void registerUser(@Body User user, Callback<Void> callback);

}