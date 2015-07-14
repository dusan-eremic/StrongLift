package me.stronglift.stronglift.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import me.stronglift.stronglift.R;
import me.stronglift.stronglift.interfaces.TextWatcherAdapter;
import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftType;

/**
 * Created by Dusan Eremic.
 */
public class LiftInputListAdapter extends BaseAdapter {

    private Context context;
    private List<Lift> liftList;
    private static LayoutInflater inflater = null;

    public LiftInputListAdapter(Context context, List<Lift> liftList) {
        this.context = context;
        this.liftList = liftList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Log.d("#test", "count:" + liftList.size());
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
        Log.d("#test", "getView position: " + position);
        View theView = convertView;
        if (theView == null)
            theView = inflater.inflate(R.layout.row_lift_input, null);

        Spinner liftSpinner = (Spinner) theView.findViewById(R.id.liftSpinner);
        ArrayAdapter<LiftType> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, Arrays.asList(LiftType.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liftSpinner.setAdapter(adapter);
        liftSpinner.setSelection(liftList.get(position).getLiftType().getId());

        updateRepsTextView((TextView) theView.findViewById(R.id.repsText), liftList, position);

        ((TextView) theView.findViewById(R.id.weightText)).setText(String.valueOf(liftList.get(position).getWeight()));


        final Button removeButton = (Button) theView.findViewById(R.id.removeButton);
        removeButton.setTag(R.integer.objectTag, liftList.get(position));

        if (!removeButton.hasOnClickListeners()) {
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liftList.remove(removeButton.getTag(R.integer.objectTag));
                    notifyDataSetChanged();
                }
            });
        }

        return theView;
    }

    private void updateRepsTextView(final TextView repsText, List<Lift> list, int position) {

        if (repsText.getTag(R.integer.objectTag) == null) {

            new TextWatcherAdapter(repsText) {

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0)
                        ((Lift) this.getTextView().getTag(R.integer.objectTag)).setRepetition(Integer.valueOf(s.toString()));
                }
            };

            repsText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus && repsText.getText().length() == 0) {
                        repsText.setText("0");
                    }
                }
            });
        }

        repsText.setTag(R.integer.objectTag, list.get(position));
        repsText.setText(String.valueOf(list.get(position).getRepetition()));
    }
}
