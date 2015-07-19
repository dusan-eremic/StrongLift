package me.stronglift.stronglift.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.stronglift.stronglift.R;
import me.stronglift.stronglift.callbacks.LiftUpdatedCallback;
import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftType;

/**
 * Adapter za prikaz liste za editovanje liftova. U metodi getView
 * je definisano kako se svaki red u tabeli popunjava podacima.
 * <p>
 * Created by Dusan Eremic.
 */
public class LiftInputListAdapter extends BaseAdapter {

    /**
     * Instanca layout inflatera
     */
    private static LayoutInflater inflater = null;
    /**
     * Application context
     */
    private Context context;
    /**
     * Lista liftova koji će biti prikazani
     */
    private List<Lift> liftList;
    /**
     * Callback koji će biti pozvan svaki put kada korisnik izvrši
     * izemnu lifta prikazanog u listi.
     */
    private LiftUpdatedCallback liftUpdatedCallback;

    /**
     * Konstruktor
     *
     * @param context             Application context
     * @param liftList            Lista liftova koji će biti prikazani
     * @param liftUpdatedCallback Callback koji će biti pozvan svaki put kada korisnik izvrši
     *                            izemnu lifta prikazanog u listi.
     */
    public LiftInputListAdapter(Context context, List<Lift> liftList, LiftUpdatedCallback liftUpdatedCallback) {
        this.context = context;
        this.liftList = liftList;
        this.liftUpdatedCallback = liftUpdatedCallback;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Vraca broj liftova u listu
     *
     * @return
     */
    @Override
    public int getCount() {
        return liftList.size();
    }

    /**
     * Vraca jedan lift iz liste
     *
     * @param position Pozicija lifta u listi koji ce biti vracen.
     * @return Lift prikazan u listi na zadatoj poziciji.
     */
    @Override
    public Object getItem(int position) {
        return liftList.get(position);
    }

    /**
     * Vraca ID lifta na zadatoj poziciji.
     *
     * @param position Pozicija u listi.
     * @return ID lifta na zadatoj poziciji.
     */
    @Override
    public long getItemId(int position) {
        return liftList.get(position).getId().hashCode();
    }

    /**
     * Vraca jedan red u listi na određenoj pozicji.
     *
     * @param position    Pozicija lifta u listi.
     * @param convertView View je XML definicija reda u tabeli.
     * @param parent
     * @return Red koji prikazuje lift na zadatoj poziciji u listi sa svim popunjenim podacima.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View theView = convertView;

        if (theView == null)
            theView = inflater.inflate(R.layout.row_lift_input, null);

        updateLiftSpinner(context, (Spinner) theView.findViewById(R.id.liftSpinner), liftList, position);
        updateRepsSpinner(context, (Spinner) theView.findViewById(R.id.repsSpinner), liftList, position);
        updateWeightTextView((TextView) theView.findViewById(R.id.weightText), liftList, position);
        updateRemoveButton((ImageButton) theView.findViewById(R.id.removeButton), liftList, position);

        return theView;
    }

    /**
     * Podešava text view koji se koristi za editovanje težine lifta.
     *
     * @param weightText Instanca UI kontrole.
     * @param list       Lista svih liftova u listi.
     * @param position   Pozicija lifta u listi koji prosleđena text view
     *                   kontrola treba da edituje.
     */
    private void updateWeightTextView(final TextView weightText, List<Lift> list, int position) {

        if (weightText.getTag(R.integer.objectTag) == null) {

            weightText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        Lift lift = (Lift) weightText.getTag(R.integer.objectTag);

                        if (weightText.getText().length() == 0) {
                            weightText.setText(lift.getWeight().toString());
                        } else {
                            BigDecimal newValue = new BigDecimal(weightText.getText().toString());
                            if (!newValue.equals(lift.getWeight())) {
                                lift.setWeight(newValue);
                                liftUpdatedCallback.update(lift);
                            }
                        }
                    }
                }
            });
        }

        weightText.setTag(R.integer.objectTag, list.get(position));
        weightText.setText(list.get(position).getWeight().toString());
    }

    /**
     * Podešava padajuću listu koja će biti korišćena za editovanje broj ponavljanja.
     *
     * @param context     Aplikacioni kontekst.
     * @param repsSpinner Instanca padajuće liste.
     * @param list        Lista liftova koji su prikazani na ekranu.
     * @param position    Pozicija lifta u prosleđenoj listi koji ova kontrola treba da edituje.
     */
    private void updateRepsSpinner(Context context, final Spinner repsSpinner, List<Lift> list, int position) {

        if (repsSpinner.getTag(R.integer.objectTag) == null) {

            List<Integer> repsList = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                repsList.add(i);
            }
            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, repsList);
            adapter.setDropDownViewResource(R.layout.spinner_item_small);
            repsSpinner.setAdapter(adapter);

            repsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Lift lift = (Lift) repsSpinner.getTag(R.integer.objectTag);
                    int newValue = (int) id + 1;
                    if (lift.getRepetition() != newValue) {
                        lift.setRepetition(newValue);
                        liftUpdatedCallback.update(lift);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        repsSpinner.setTag(R.integer.objectTag, list.get(position));
        repsSpinner.setSelection(liftList.get(position).getRepetition() - 1);
    }

    /**
     * Podešava padajuću listu koja će biti korišćena za editovanje tipa lifta.
     *
     * @param context     Aplikacioni kontekst.
     * @param liftSpinner Instanca padajuće liste.
     * @param list        Lista liftova koji su prikazani na ekranu.
     * @param position    Pozicija lifta u prosleđenoj listi koji ova kontrola treba da edituje.
     */
    private void updateLiftSpinner(Context context, final Spinner liftSpinner, List<Lift> list, int position) {

        if (liftSpinner.getTag(R.integer.objectTag) == null) {

            ArrayAdapter<LiftType> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, Arrays.asList(LiftType.values()));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            liftSpinner.setAdapter(adapter);

            liftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Lift lift = (Lift) liftSpinner.getTag(R.integer.objectTag);
                    int newValue = (int) id;
                    if (newValue != lift.getLiftType().getId()) {
                        lift.setLiftType(newValue);
                        liftUpdatedCallback.update(lift);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        liftSpinner.setTag(R.integer.objectTag, list.get(position));
        liftSpinner.setSelection(liftList.get(position).getLiftType().getId());
    }

    /**
     * Podešava dugme za brisanje lifta iz liste.
     *
     * @param removeButton Instanca dugmenta.
     * @param liftList     Lista liftova koji su prikazani u listi.
     * @param position     Pozicija lifta koji ovo dugme briše iz liste.
     */
    private void updateRemoveButton(final ImageButton removeButton, final List<Lift> liftList, int position) {

        removeButton.setTag(R.integer.objectTag, liftList.get(position));

        if (!removeButton.hasOnClickListeners()) {
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Lift lift = (Lift) removeButton.getTag(R.integer.objectTag);
                    liftList.remove(lift);
                    liftUpdatedCallback.remove(lift);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
