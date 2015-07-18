package me.stronglift.stronglift.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import me.stronglift.stronglift.R;
import me.stronglift.stronglift.adapters.LiftHistoryAdapter;
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
public class LiftRecordsListFragment extends Fragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LiftRecordsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lift_records, container, false);

        final ListView mListView = (ListView) view.findViewById(R.id.liftRecordsListView);

        RestService.getLiftService().getRecords(AuthManager.getUser(), new RestCallback<LiftCollection>(getActivity(), "#getRecords") {
            @Override
            public void success(LiftCollection liftCollection, Response response) {
                Log.d("#LiftRecordsList", "Loaded records: " + liftCollection.getItems().size());
                mListView.setAdapter(new LiftHistoryAdapter(getActivity(), liftCollection.getItems()));
            }
        });

        return view;
    }
}
