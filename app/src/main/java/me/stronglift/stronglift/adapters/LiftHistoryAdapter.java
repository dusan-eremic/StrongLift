package me.stronglift.stronglift.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import me.stronglift.stronglift.R;
import me.stronglift.stronglift.model.Lift;

/**
 * Adapter za prikaz liste istorije liftova. U metodi getView
 * je definisano kako se svaki red u tabeli popunjava podacima.
 * <p>
 * Created by Dusan Eremic.
 */
public class LiftHistoryAdapter extends BaseAdapter {

    /**
     * Format datuma za prikaz u listi
     */
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Instanca date formata
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

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
     * Konstruktor
     *
     * @param context  Application context
     * @param liftList Lista liftova koji će biti prikazani
     */
    public LiftHistoryAdapter(Context context, List<Lift> liftList) {
        this.context = context;
        this.liftList = liftList;
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
            theView = inflater.inflate(R.layout.row_lift_history, null);

        ((TextView) theView.findViewById(R.id.liftTextView)).setText(liftList.get(position).getLiftType().toString());
        ((TextView) theView.findViewById(R.id.repsTextView)).setText(String.valueOf(liftList.get(position).getRepetition()));
        ((TextView) theView.findViewById(R.id.weightTextView)).setText(String.valueOf(liftList.get(position).getWeight()));
        ((TextView) theView.findViewById(R.id.oneRepMaxTextView)).setText(String.valueOf(liftList.get(position).calcOneRepMax()));
        String date = sdf.format(liftList.get(position).getTime());
        ((TextView) theView.findViewById(R.id.dateTextView)).setText(date);

        return theView;
    }
}
