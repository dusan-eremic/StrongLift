package me.stronglift.stronglift.rest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import me.stronglift.stronglift.model.RestError;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Implementacija failure metode iz Callback interfejsa. Ova
 * implementacija će pokušati da dobijenu grešku konvertuje u
 * RestError model.
 * <p>
 * Created by Dusan Eremic.
 */
public abstract class RestCallback<T> implements Callback<T> {

    /**
     * Tag koji će se koristiti za logovanje
     */
    protected String tag;

    /**
     * Aplikacioni kontekst
     */
    private Context context;

    /**
     * Konstruktor
     *
     * @param context Aplikacioni kontekst
     * @param tag     Tag koji će se koristiti za logovanje
     */
    public RestCallback(Context context, String tag) {
        this.context = context;
        this.tag = tag;
    }

    /**
     * Metoda će biti pozvana u slučaju bilo kakve greške u
     * komunikaciji sa REST serverom.
     */
    @Override
    public void failure(RetrofitError error) {
        String errorMessage;
        RestError restError = (RestError) error.getBodyAs(RestError.class);
        if (restError != null && restError.getErrorMessage() != null) {
            errorMessage = restError.getErrorMessage();
        } else {
            errorMessage = error.getMessage();
        }

        Log.e(tag + "#CB", errorMessage);
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
    }
}
