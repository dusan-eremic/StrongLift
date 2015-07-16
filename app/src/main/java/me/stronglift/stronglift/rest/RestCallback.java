package me.stronglift.stronglift.rest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by dusan on 16/07/15.
 */
public abstract class RestCallback<T> implements Callback<T> {

    private Context context;

    public RestCallback(Context context) {
        this.context = context;
    }

    @Override
    public void failure(RetrofitError error) {
        String errorMessage;
        RestError restError = (RestError) error.getBodyAs(RestError.class);
        if (restError != null && restError.getErrorMessage() != null) {
            errorMessage = restError.getErrorMessage();
        } else {
            errorMessage = error.getMessage();
        }

        Log.e("#RestCallback", errorMessage);
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
