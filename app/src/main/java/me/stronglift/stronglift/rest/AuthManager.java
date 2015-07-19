package me.stronglift.stronglift.rest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.UUID;

import me.stronglift.stronglift.callbacks.AuthManagerCallback;
import me.stronglift.stronglift.model.User;
import retrofit.client.Response;

/**
 * Menadžer za autorizaciju korisnika sa REST API-jem.
 * 1. Obavlja automatsku registraciju korisnika ukoliko
 * korisnik prvi put koristi aplikaciju.
 * <p>
 * 2. Skladišti i učitva korisničke kredencijale u
 * Andorid SharedPreferences fajlu.
 * <p>
 * <p>
 * Created by Dusan Eremic.
 */
public class AuthManager {

    /**
     * Naziv SharedPreferences fajla
     */
    public static final String PREFS_FILE = "StrongLift";

    /**
     * Key za username u SharedPreferences
     */
    public static final String USERNAME = "username";

    /**
     * Key za password u SharedPreferences
     */
    public static final String PASSWORD = "password";

    /**
     * Generisani authBasic za korisnika aplikacije.
     */
    private static AuthBasic authBasic;

    /**
     * Metoda inicijalizuje AuthManager
     *
     * @param context             Aplikacioni kontekst.
     * @param authManagerCallback Callback koji će biti pozvan kada je
     *                            inicijalizacija završena.
     */
    public static void init(final Context context, final AuthManagerCallback authManagerCallback) {
        if (authBasic == null) {

            Log.d("#AuthManager", "Initializing AuthManager");

            SharedPreferences preferences = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);

            if (preferences.contains(USERNAME) && preferences.contains(PASSWORD)) {
                String username = preferences.getString(USERNAME, null);
                String password = preferences.getString(PASSWORD, null);
                authBasic = new AuthBasic(username, password);
                authManagerCallback.authManagerInitialized();
                Log.d("#AuthManager", "User is found in the preferences file, username: " + username);
            } else {
                Log.d("#AuthManager", "User is not found in the preferences file.");

                final User user = new User();
                user.setUsername(String.valueOf(UUID.randomUUID().toString().replace("-", "").hashCode()));
                user.setPassword(String.valueOf(UUID.randomUUID().toString().replace("-", "").hashCode()));
                Log.d("#AuthManager", "New user is generated, username: " + user.getUsername());

                Log.d("#AuthManager", "Calling REST service to register the new user...");
                RestServiceFactory.getUserService().registerUser(user, new RestCallback<Void>(context, "#registerUser") {
                    @Override
                    public void success(Void aVoid, Response response) {
                        Log.d("#AuthManager", "User is registered successfully!");
                        Log.d("#AuthManager", "Saving new user into the preferences file.");
                        SharedPreferences settings = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(USERNAME, user.getUsername());
                        editor.putString(PASSWORD, user.getPassword());
                        editor.commit();
                        authBasic = new AuthBasic(user);
                        authManagerCallback.authManagerInitialized();
                    }
                });
            }
        } else {
            authManagerCallback.authManagerInitialized();
        }
    }

    /**
     * Vraća AuthBasic instancu koja pozivom na toString vraća Base64 enkodirani
     * string koji sadrži korisnički username i password.
     *
     * @return
     */
    public static AuthBasic getUser() {
        if (authBasic == null) {
            Log.e("#AuthManager", "AuthManager is not initialized!");
        }
        Log.d("#AuthManager", "Returning the AuthBasic: " + authBasic);
        return authBasic;
    }

}
