package me.stronglift.stronglift.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.stronglift.stronglift.R;
import me.stronglift.stronglift.adapters.LiftHistoryAdapter;
import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftType;
import me.stronglift.stronglift.rest.AuthManager;
import me.stronglift.stronglift.model.LiftCollection;
import me.stronglift.stronglift.rest.RestCallback;
import me.stronglift.stronglift.rest.RestServiceFactory;
import retrofit.client.Response;

/**
 * Fragment koji prikazuje listu istorije liftova.
 * <p>
 * Created by Dusan Eremic.
 */
public class LiftHistoryListFragment extends Fragment {

    /**
     * Instanca UI komponente koja prikazuje listu.
     */
    private ListView mListView;

    /**
     * Lista svih liftova koji se prikazuju u listi.
     */
    private List<Lift> liftList;

    /**
     * Lista filtriranih liftova. Popunjava se ako korisnik izabere
     * filter po tipu vežbe.
     */
    private List<Lift> liftListFiltered = new ArrayList<>();

    /**
     * Obavezni konstruktor bez parametara
     */
    public LiftHistoryListFragment() {
    }

    /**
     * Metoda se poziva kada se kreira view (korisnički interfejs) ovog fragmenta.
     * <p>
     * Poziva se REST servis koji učitava podatke sa servera.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_lift_history, container, false);

        mListView = (ListView) view.findViewById(R.id.liftHistoryListView);

        RestServiceFactory.getLiftService().getAllLifts(AuthManager.getUser(), new RestCallback<LiftCollection>(getActivity(), "#getAllLifts") {
            @Override
            public void success(LiftCollection liftCollection, Response response) {
                Log.d("#LiftHistoryList", "Loaded lifts: " + liftCollection.getItems().size());
                liftList = liftCollection.getItems();
                setupLiftSpinnerFilter((Spinner) view.findViewById(R.id.liftSpinner));
            }
        });

        return view;
    }

    /**
     * Podešava padajuću listu koja će biti korišćena kao filter po tipu vežbe.
     *
     * @param liftSpinner Instanca UI komponente.
     */
    private void setupLiftSpinnerFilter(final Spinner liftSpinner) {

        List<String> liftTypes = new ArrayList<>();
        liftTypes.add(getString(R.string.filter_all));

        for (LiftType liftType : Arrays.asList(LiftType.values())) {
            liftTypes.add(liftType.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, liftTypes);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        liftSpinner.setAdapter(adapter);

        liftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("#LiftSpinnerFilter", "Filter selected: " + id);
                liftListFiltered.clear();
                if (id == 0) {
                    setList(liftList);
                } else {
                    for (Lift lift : liftList) {
                        if (lift.getLiftType().getId() == id - 1) {
                            liftListFiltered.add(lift);
                        }
                    }
                    setList(liftListFiltered);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    /**
     * Setuje novu (filtriranu) listu za prikaz.
     *
     * @param liftList Nova lista koja se setuje nakon premene filtera.
     */
    private void setList(List<Lift> liftList) {
        mListView.setAdapter(new LiftHistoryAdapter(getActivity(), liftList));
    }
}
