package me.stronglift.stronglift.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import me.stronglift.stronglift.util.DateToUTCAdapter;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 *
 *
 * Created by Dusan Eremic.
 */
public class RestService {

    private static String BASE_URL = "http://10.0.2.2:8080";
    private static Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateToUTCAdapter()).create();
    private static RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .setConverter(new GsonConverter(gson))
            .build();
    private static LiftService liftService;
    private static UserService userService;

    public static LiftService getLiftService() {

        if (liftService == null) {
            liftService = restAdapter.create(LiftService.class);
        }

        return liftService;
    }

    public static UserService getUserService() {

        if (userService == null) {
            userService = restAdapter.create(UserService.class);
        }

        return userService;
    }

}
