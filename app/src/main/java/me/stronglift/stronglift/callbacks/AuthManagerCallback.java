package me.stronglift.stronglift.callbacks;

/**
 * Callback za inicijalizaciju AuthManagera.
 * <p>
 * Created by Dusan Eremic.
 */
public interface AuthManagerCallback {

    /**
     * Metoda ce biti pozvana nakon što je AuthManager
     * završio sa inicijalizacijom.
     */
    void authManagerInitialized();
}
