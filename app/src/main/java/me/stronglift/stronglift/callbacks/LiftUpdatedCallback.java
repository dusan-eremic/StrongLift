package me.stronglift.stronglift.callbacks;

import me.stronglift.stronglift.model.Lift;

/**
 * Callback koji će biti pozvan nakon što korisnik izvrši
 * izmenu lifta kroz korisnički interfejs.
 * <p>
 * Created by Dusan Eremic.
 */
public interface LiftUpdatedCallback {

    /**
     * Metoda će biti pozvana ako je lift izmenjen.
     *
     * @param lift Instanca izmenjenog lifta.
     */
    void update(Lift lift);

    /**
     * Metoda će biti pozvana ako je lift uklonjen iz liste.
     *
     * @param lift Instanca obirsanog lifta.
     */
    void remove(Lift lift);
}
