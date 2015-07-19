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
import me.stronglift.stronglift.rest.RestServiceFactory;
import retrofit.client.Response;

/**
 * Fragment koji prikazuje listu za editovanje liftova.
 * <p>
 * Created by Dusan Eremic.
 */
public class LiftInputListFragment extends ListFragment {

    /**
     * Instanca UI komponente koja prikazuje listu.
     */
    private BaseAdapter mAdapter;

    /**
     * Lista svih liftova koji se prikazuju u listi.
     */
    private List<Lift> liftList;

    /**
     * Obavezni konstruktor bez parametara
     */
    public LiftInputListFragment() {
    }

    /**
     * Metoda se poziva kada je završeno kreiranje view-a (korisničkog interfejsa) ovog fragmenta.
     * <p>
     * Poziva se REST servis koji učitava podatke sa servera.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RestServiceFactory.getLiftService().getAllLifts(AuthManager.getUser(), new RestCallback<LiftCollection>(getActivity(), "#getAllLifts") {
            @Override
            public void success(LiftCollection liftCollection, Response response) {
                liftList = liftCollection.getItems();
                mAdapter = new LiftInputListAdapter(getActivity(), liftList, getLiftUpdatedCallback());
                setListAdapter(mAdapter);
            }
        });
    }

    /**
     * Metoda kreira instancu LiftUpdated callback-a koji će se proslediti
     * list adapteru i biće pozivan svaki put kada korisnik izmeni ili
     * obriše lift.
     *
     * @return LiftUpdatedCallback koji poziva REST servis i ažurira podatke na serveru.
     */
    private LiftUpdatedCallback getLiftUpdatedCallback() {
        return new LiftUpdatedCallback() {
            @Override
            public void update(Lift lift) {
                RestServiceFactory.getLiftService().updateLift(AuthManager.getUser(), lift.getId(), lift, new RestCallback<Lift>(getActivity(), "#updateLift") {
                    @Override
                    public void success(Lift lift, Response response) {
                        Log.d("#LiftUpdatedCallback", "Lift updated: " + lift);
                    }
                });
            }

            @Override
            public void remove(final Lift lift) {
                RestServiceFactory.getLiftService().removeLift(AuthManager.getUser(), lift.getId(), new RestCallback<Void>(getActivity(), "#removeLift") {
                    @Override
                    public void success(Void v, Response response) {
                        Log.d("#LiftUpdatedCallback", "Lift deleted: " + lift);
                    }
                });
            }
        };
    }

    /**
     * Metodu poziva parent Activity da doda novi lift u listu.
     *
     * @param lift Novi lift koji je korisnik kreirao.
     */
    public void addLift(Lift lift) {
        liftList.add(0, lift);
        mAdapter.notifyDataSetChanged();
    }
}
