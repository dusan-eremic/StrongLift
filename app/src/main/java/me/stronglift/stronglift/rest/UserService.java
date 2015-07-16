package me.stronglift.stronglift.rest;

import me.stronglift.stronglift.model.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface UserService {

    @POST("/users/")
    void registerUser(@Body User user, Callback<Void> callback);

}