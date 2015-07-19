package me.stronglift.stronglift.rest;

import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftCollection;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * REST servis za pristup Lift resursu.
 * <p>
 * Created by Dusan Eremic.
 */
public interface LiftService {

    /**
     * Authorization header
     */
    String AUTHORIZATION = "Authorization";

    /**
     * Path za ovaj resurs
     */
    String LIFTS_PATH = "/lifts/";

    /**
     * Sub-path za listu rekorda
     */
    String RECORDS = "records";

    /**
     * Servisa za dodavanje novog Lifta.
     *
     * @param auth     Authorization header
     * @param lift     Lift koji se dodaje
     * @param callback Callback koji će biti pozvan nakon što Android
     *                 klijent dobije odgovor od servera.
     */
    @POST(LIFTS_PATH)
    void addLift(@Header(AUTHORIZATION) AuthBasic auth, @Body Lift lift, Callback<Lift> callback);

    /**
     * Servis za update postojećeg lifta koji ima ID.
     *
     * @param auth     Authorization header
     * @param liftId   ID lifta koji se dodaje.
     * @param lift     Lift koji se dodaje.
     * @param callback Callback koji će biti pozvan nakon što Android
     *                 klijent dobije odgovor od servera.
     */
    @POST(LIFTS_PATH + "{id}")
    void updateLift(@Header(AUTHORIZATION) AuthBasic auth, @Path("id") String liftId, @Body Lift lift, Callback<Lift> callback);

    /**
     * Servis za brisanje postojećeg lifta koji ima ID.
     *
     * @param auth     Authorization header
     * @param liftId   ID lifta koji se briše.
     * @param callback Callback koji će biti pozvan nakon što Android
     *                 klijent dobije odgovor od servera.
     */
    @DELETE(LIFTS_PATH + "{id}")
    void removeLift(@Header(AUTHORIZATION) AuthBasic auth, @Path("id") String liftId, Callback<Void> callback);

    /**
     * Servis koji vraća listu svih liftova.
     *
     * @param auth     Authorization header
     * @param callback Callback koji će biti pozvan nakon što Android
     *                 klijent dobije odgovor od servera.
     */
    @GET(LIFTS_PATH)
    void getAllLifts(@Header(AUTHORIZATION) AuthBasic auth, Callback<LiftCollection> callback);

    /**
     * Servis koji vraća listu rekorda.
     *
     * @param auth     Authorization header
     * @param callback Callback koji će biti pozvan nakon što Android
     *                 klijent dobije odgovor od servera.
     */
    @GET(LIFTS_PATH + RECORDS)
    void getRecords(@Header(AUTHORIZATION) AuthBasic auth, Callback<LiftCollection> callback);
}
