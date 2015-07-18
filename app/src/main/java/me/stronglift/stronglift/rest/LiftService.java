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
 *
 *
 * Created by Dusan Eremic.
 */
public interface LiftService {

    String AUTHORIZATION = "Authorization";
    String LIFTS_PATH = "/lifts/";
    String RECORDS = "records";

    @POST(LIFTS_PATH)
    void addLift(@Header(AUTHORIZATION) AuthBasic auth, @Body Lift lift, Callback<Lift> callback);

    @POST(LIFTS_PATH + "{id}")
    void updateLift(@Header(AUTHORIZATION) AuthBasic auth, @Path("id") String liftId, @Body Lift lift, Callback<Lift> callback);

    @DELETE(LIFTS_PATH + "{id}")
    void removeLift(@Header(AUTHORIZATION) AuthBasic auth, @Path("id") String liftId, Callback<Void> callback);

    @GET(LIFTS_PATH)
    void getAllLifts(@Header(AUTHORIZATION) AuthBasic auth, Callback<LiftCollection> callback);

    @GET(LIFTS_PATH + RECORDS)
    void getRecords(@Header(AUTHORIZATION) AuthBasic auth, Callback<LiftCollection> callback);
}
