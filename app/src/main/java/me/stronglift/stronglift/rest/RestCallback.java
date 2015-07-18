package me.stronglift.stronglift.rest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import me.stronglift.stronglift.model.RestError;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 *
 *
 * Created by Dusan Eremic.
 */
public abstract class RestCallback<T> implements Callback<T> {

    private Context context;
    protected String tag;

    public RestCallback(Context context, String tag) {
        this.context = context;
        this.tag = tag;
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

        Log.e(tag+"#CB", errorMessage);
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
