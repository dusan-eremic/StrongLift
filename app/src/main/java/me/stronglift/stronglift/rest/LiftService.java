package me.stronglift.stronglift.rest;

import me.stronglift.stronglift.model.Lift;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by dusan on 16/07/15.
 */
public interface LiftService {

    String AUTHORIZATION = "Authorization";
    String LIFTS_PATH = "/lifts/";

    @POST(LIFTS_PATH)
    void addLift(@Header(AUTHORIZATION) AuthBasic auth, @Body Lift lift, Callback<Lift> callback);

    @POST(LIFTS_PATH + "{id}")
    void updateLift(@Header(AUTHORIZATION) AuthBasic auth, @Path("id") String liftId, @Body Lift lift, Callback<Lift> callback);

    @DELETE(LIFTS_PATH + "{id}")
    void removeLift(@Header(AUTHORIZATION) AuthBasic auth, @Path("id") String liftId, Callback<Void> callback);

    @GET(LIFTS_PATH)
    void getAllLifts(@Header(AUTHORIZATION) AuthBasic auth, Callback<LiftCollection> callback);
}
