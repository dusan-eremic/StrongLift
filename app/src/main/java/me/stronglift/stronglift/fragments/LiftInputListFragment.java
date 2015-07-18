package me.stronglift.stronglift.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;

import java.util.List;

import me.stronglift.stronglift.adapters.LiftInputListAdapter;
import me.stronglift.stronglift.callbacks.LiftUpdatedCallback;
import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.rest.AuthManager;
import me.stronglift.stronglift.model.LiftCollection;
import me.stronglift.stronglift.rest.RestCallback;
import me.stronglift.stronglift.rest.RestService;
import retrofit.client.Response;

/**
 *
 *
 * Created by Dusan Eremic.
 */
public class LiftInputListFragment extends ListFragment {

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private BaseAdapter mAdapter;

    private List<Lift> liftList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LiftInputListFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RestService.getLiftService().getAllLifts(AuthManager.getUser(), new RestCallback<LiftCollection>(getActivity(), "#getAllLifts") {
            @Override
            public void success(LiftCollection liftCollection, Response response) {
                liftList = liftCollection.getItems();
                mAdapter = new LiftInputListAdapter(getActivity(), liftList, getLiftUpdatedCallback());
                setListAdapter(mAdapter);
            }
        });
    }

    private LiftUpdatedCallback getLiftUpdatedCallback() {
        return new LiftUpdatedCallback() {
            @Override
            public void update(Lift lift) {
                RestService.getLiftService().updateLift(AuthManager.getUser(), lift.getId(), lift, new RestCallback<Lift>(getActivity(), "#updateLift") {
                    @Override
                    public void success(Lift lift, Response response) {
                        Log.d("#LiftUpdatedCallback", "Lift updated: " + lift);
                    }
                });
            }

            @Override
            public void remove(final Lift lift) {
                RestService.getLiftService().removeLift(AuthManager.getUser(), lift.getId(), new RestCallback<Void>(getActivity(), "#removeLift") {
                    @Override
                    public void success(Void v, Response response) {
                        Log.d("#LiftUpdatedCallback", "Lift deleted: " + lift);
                    }
                });
            }
        };
    }

    public void addLift(Lift lift) {
        liftList.add(0, lift);
        mAdapter.notifyDataSetChanged();
    }
}
