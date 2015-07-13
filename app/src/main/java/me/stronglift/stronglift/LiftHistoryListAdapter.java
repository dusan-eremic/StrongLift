package me.stronglift.stronglift;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftType;

/**
 * Created by Dusan Eremic.
 */
public class LiftHistoryListAdapter extends BaseAdapter {

    private Context context;
    private List<Lift> liftList;
    private static LayoutInflater inflater = null;

    public LiftHistoryListAdapter(Context context, List<Lift> liftList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View theView = convertView;
        if (theView == null)
            theView = inflater.inflate(R.layout.row_lift_history, null);

        Spinner liftSpinner = (Spinner) theView.findViewById(R.id.liftSpinner);
        ArrayAdapter<LiftType> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, Arrays.asList(LiftType.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liftSpinner.setAdapter(adapter);
        liftSpinner.setSelection(liftList.get(position).getLiftType().getId());

        ((TextView) theView.findViewById(R.id.repsText)).setText(String.valueOf(liftList.get(position).getRepetition()));
        ((TextView) theView.findViewById(R.id.weightText)).setText(String.valueOf(liftList.get(position).getWeight()));

        return theView;
    }
}
