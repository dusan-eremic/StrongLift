package me.stronglift.stronglift.adapters;

import android.content.Context;
import android.text.Editable;
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

        updateLiftSpinner(context, (Spinner) theView.findViewById(R.id.liftSpinner), liftList, position);
        updateRepsSpinner(context, (Spinner) theView.findViewById(R.id.repsSpinner), liftList, position);
        updateWeightTextView((TextView) theView.findViewById(R.id.weightText), liftList, position);
        updateRemoveButton((ImageButton) theView.findViewById(R.id.removeButton), liftList, position);

        return theView;
    }

    private void updateWeightTextView(final TextView weightText, List<Lift> list, int position) {

        if (weightText.getTag(R.integer.objectTag) == null) {

            new TextWatcherAdapter(weightText) {

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0)
                        ((Lift) this.getTextView().getTag(R.integer.objectTag)).setWeight(new BigDecimal(s.toString()));
                }
            };

            weightText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus && weightText.getText().length() == 0) {
                        weightText.setText("0.00");
                    }
                }
            });
        }

        weightText.setTag(R.integer.objectTag, list.get(position));
        weightText.setText(list.get(position).getWeight().toString());
    }

    private void updateRepsSpinner(Context context, final Spinner repsSpinner, List<Lift> list, int position) {

        if (repsSpinner.getTag(R.integer.objectTag) == null) {

            List<Integer> repsList = new ArrayList<>();
            for(int i = 1; i <=10; i++) {
                repsList.add(i);
            }
            ArrayAdapter<Integer> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, repsList);
            adapter.setDropDownViewResource(R.layout.spinner_item_small);
            repsSpinner.setAdapter(adapter);

            repsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ((Lift) repsSpinner.getTag(R.integer.objectTag)).setRepetition((int) id+1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        repsSpinner.setTag(R.integer.objectTag, list.get(position));
        repsSpinner.setSelection(liftList.get(position).getRepetition()-1);
    }

    private void updateLiftSpinner(Context context, final Spinner liftSpinner, List<Lift> list, int position) {

        if (liftSpinner.getTag(R.integer.objectTag) == null) {

            ArrayAdapter<LiftType> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, Arrays.asList(LiftType.values()));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            liftSpinner.setAdapter(adapter);

            liftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ((Lift) liftSpinner.getTag(R.integer.objectTag)).setLiftType((int) id);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        liftSpinner.setTag(R.integer.objectTag, list.get(position));
        liftSpinner.setSelection(liftList.get(position).getLiftType().getId());
    }

    private void updateRemoveButton(final ImageButton removeButton, final List<Lift> liftList, int position) {

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
    }
}
