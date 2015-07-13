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
 * Created by Dusan Eremic.
 */
public class LiftHistoryAdapter extends BaseAdapter {

    private Context context;
    private List<Lift> liftList;
    private static LayoutInflater inflater = null;

    public LiftHistoryAdapter(Context context, List<Lift> liftList) {
        this.context = context;
        this.liftList = liftList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return liftList.size();
    }

    @Override
    public Object getItem(int position) {
        return liftList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return liftList.get(position).getId().hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View theView = convertView;

        if (theView == null)
            theView = inflater.inflate(R.layout.row_lift_history, null);

        ((TextView) theView.findViewById(R.id.liftTextView)).setText(liftList.get(position).getLiftType().toString());
        ((TextView) theView.findViewById(R.id.repsTextView)).setText(String.valueOf(liftList.get(position).getRepetition()));
        ((TextView) theView.findViewById(R.id.weightTextView)).setText(String.valueOf(liftList.get(position).getWeight()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(liftList.get(position).getTime());
        ((TextView) theView.findViewById(R.id.dateTextView)).setText(date);

        return theView;
    }
}
