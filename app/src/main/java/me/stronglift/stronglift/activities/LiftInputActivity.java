package me.stronglift.stronglift.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import me.stronglift.stronglift.R;
import me.stronglift.stronglift.fragments.LiftInputListFragment;
import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftType;
import me.stronglift.stronglift.rest.AuthManager;
import me.stronglift.stronglift.rest.RestCallback;
import me.stronglift.stronglift.rest.RestService;
import retrofit.client.Response;


/**
 * Created by Dusan Eremic.
 */
public class LiftInputActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLiftSpinner();
        setupWeightSpinner();
        setupAddButton();
    }

    private void setupAddButton() {
        Button addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    final Lift lift = getLiftEntered();
                    RestService.getLiftService().addLift(AuthManager.getUser(), lift, new RestCallback<Lift>(LiftInputActivity.this) {
                        @Override
                        public void success(Lift lift, Response response) {
                            addLiftToList(lift);
                            Log.d("#LiftInputActivity", "Lift added: " + lift.toString());
                        }
                    });
                } catch (NumberFormatException nfe) {
                    Toast.makeText(LiftInputActivity.this, getString(R.string.invalid_rep_or_weight), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void setupLiftSpinner() {
        setContentView(R.layout.activity_lift_input);
        Spinner liftSpinner = (Spinner) findViewById(R.id.liftSpinner);
        ArrayAdapter<LiftType> liftSpinnerAdapter = new ArrayAdapter<LiftType>(this, android.R.layout.simple_spinner_item, Arrays.asList(LiftType.values()));
        liftSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liftSpinner.setAdapter(liftSpinnerAdapter);
    }

    private void setupWeightSpinner() {
        List<Integer> repsList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            repsList.add(i);
        }

        Spinner repsSpinner = (Spinner) findViewById(R.id.repsSpinner);
        ArrayAdapter<Integer> repsSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, repsList);
        repsSpinnerAdapter.setDropDownViewResource(R.layout.spinner_item_small);
        repsSpinner.setAdapter(repsSpinnerAdapter);
    }

    private Lift getLiftEntered() {
        Lift lift = new Lift();

        Spinner liftSpinner = (Spinner) findViewById(R.id.liftSpinner);
        lift.setLiftType((LiftType) liftSpinner.getSelectedItem());

        Spinner repsSpinner = (Spinner) findViewById(R.id.repsSpinner);
        lift.setRepetition((int) repsSpinner.getSelectedItemId() + 1);

        TextView weightText = (TextView) findViewById(R.id.weightText);
        lift.setWeight(new BigDecimal(weightText.getText().toString()));

        lift.setTime(new Date());

        return lift;
    }

    private void addLiftToList(Lift lift) {
        ((LiftInputListFragment) getFragmentManager()//
                .findFragmentById(R.id.liftHistoryFragment)) //
                .addLift(lift);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input, menu);
        return true;
    }

    /**
     * Metoda poziva odgovarajuci Activity nakon nakon selekcije u meniju.
     *
     * @param item Selektovana stavka u meniju.
     * @return true ako je selekcija obradjena, false ako nije
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_item_input) {
            startActivity(new Intent(this, LiftInputActivity.class));
            finish();
            return true;
        } else if (id == R.id.menu_item_history) {
            startActivity(new Intent(this, LiftHistoryActivity.class));
            finish();
            return true;
        } else if (id == R.id.menu_item_records) {
            startActivity(new Intent(this, LiftRecordsActivity.class));
            finish();
            return true;
        } else if (id == R.id.menu_item_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
