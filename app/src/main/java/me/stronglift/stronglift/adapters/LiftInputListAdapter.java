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
            theView = inflater.inflate(R.layout.row_lift_input, null);

        Spinner liftSpinner = (Spinner) theView.findViewById(R.id.liftSpinner);
        ArrayAdapter<LiftType> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, Arrays.asList(LiftType.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liftSpinner.setAdapter(adapter);
        liftSpinner.setSelection(liftList.get(position).getLiftType().getId());

        final TextView repsText = (TextView) theView.findViewById(R.id.repsText);
        repsText.setText(String.valueOf(liftList.get(position).getRepetition()));

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("#test", "position: " + position + " string: " + s.toString());
                liftList.get(position).setRepetition(Integer.valueOf(s.toString()));
            }
        };

        repsText.addTextChangedListener(textWatcher);

        ((TextView) theView.findViewById(R.id.weightText)).setText(String.valueOf(liftList.get(position).getWeight()));

        Button removeButton = (Button) theView.findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repsText.removeTextChangedListener(textWatcher);
                liftList.remove(position);
                notifyDataSetChanged();
            }
        });

        return theView;
    }
}
