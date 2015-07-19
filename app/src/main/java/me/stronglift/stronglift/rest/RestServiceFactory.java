package me.stronglift.stronglift.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import me.stronglift.stronglift.util.DateToUTCAdapter;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * REST factory će fabrikovati konkretne REST servise
 * kao što su LiftService, UserService
 * <p>
 * Created by Dusan Eremic.
 */
public class RestServiceFactory {

    /**
     * Osnovni put do servera. Ova IP adresa je neophodna ukoliko se
     * aplikacija pokreće na emulatoru a server je localhost.
     */
    private static String BASE_URL = "http://10.0.2.2:8080";

    /**
     * Registracija custom konvertera za type Date jer želimo da koristimo
     * UTC time format.
     */
    private static Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateToUTCAdapter()).create();

    /**
     * Kreiranje static instance Retrofit REST adaptera.
     */
    private static RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(BASE_URL).setConverter(new GsonConverter(gson)).build();

    /**
     * Instanca LiftService-a
     */
    private static LiftService liftService;

    /**
     * Instanca UserService-a
     */
    private static UserService userService;

    /**
     * Metoda vraća instancu LiftService-a
     */
    public static LiftService getLiftService() {

        if (liftService == null) {
            liftService = restAdapter.create(LiftService.class);
        }

        return liftService;
    }

    /**
     * Metoda vraća instancu UserService-a
     */
    public static UserService getUserService() {

        if (userService == null) {
            userService = restAdapter.create(UserService.class);
        }

        return userService;
    }

}
