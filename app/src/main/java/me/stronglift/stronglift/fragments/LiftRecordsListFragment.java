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
import me.stronglift.stronglift.rest.RestServiceFactory;
import retrofit.client.Response;

/**
 * Fragment koji prikazuje listu rekorda.
 * <p>
 * Created by Dusan Eremic.
 */
public class LiftRecordsListFragment extends Fragment {

    /**
     * Obavezni konstruktor bez parametara
     */
    public LiftRecordsListFragment() {
    }

    /**
     * Metoda se poziva kada se kreira view (korisnički interfejs) ovog fragmenta.
     * <p>
     * Poziva se REST servis koji učitava podatke sa servera.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lift_records, container, false);

        final ListView mListView = (ListView) view.findViewById(R.id.liftRecordsListView);

        RestServiceFactory.getLiftService().getRecords(AuthManager.getUser(), new RestCallback<LiftCollection>(getActivity(), "#getRecords") {
            @Override
            public void success(LiftCollection liftCollection, Response response) {
                Log.d("#LiftRecordsList", "Loaded records: " + liftCollection.getItems().size());
                mListView.setAdapter(new LiftHistoryAdapter(getActivity(), liftCollection.getItems()));
            }
        });

        return view;
    }
}
