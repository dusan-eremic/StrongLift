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

import java.util.Arrays;
import java.util.Date;

import me.stronglift.stronglift.R;
import me.stronglift.stronglift.dao.DummyContent;
import me.stronglift.stronglift.fragments.LiftInputListFragment;
import me.stronglift.stronglift.interfaces.OnFragmentInteractionListener;
import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftType;
import me.stronglift.stronglift.model.User;


/**
 * Created by Dusan Eremic.
 */
public class LiftInputActivity extends Activity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift_input);
        Spinner liftSpinner = (Spinner) findViewById(R.id.liftSpinner);
        ArrayAdapter<LiftType> adapter = new ArrayAdapter<LiftType>(this, android.R.layout.simple_spinner_item, Arrays.asList(LiftType.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liftSpinner.setAdapter(adapter);

        Button addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lift lift = getLift();
                DummyContent.ITEMS.add(0, lift);
                LiftInputListFragment liftinputListFragment = (LiftInputListFragment) getFragmentManager().findFragmentById(R.id.liftHistoryFragment);
                liftinputListFragment.refreshData();
                Log.d("#LiftInputActivity", "Added new lift: " + lift.toString());
            }
        });
    }

    private Lift getLift() {
        Lift lift = new Lift();

        Spinner spinner = (Spinner) findViewById(R.id.liftSpinner);
        lift.setLiftType((LiftType) spinner.getSelectedItem());

        TextView repsText = (TextView) findViewById(R.id.repsText);
        lift.setRepetition(Integer.valueOf(repsText.getText().toString()));

        TextView weightText = (TextView) findViewById(R.id.weightText);
        lift.setWeight(Integer.valueOf(weightText.getText().toString()));

        lift.setTime(new Date());
        lift.setOwner(new User());

        return lift;
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
        }
        else if (id == R.id.menu_item_history) {
            startActivity(new Intent(this, LiftHistoryActivity.class));
            finish();
            return true;
        }
        else if (id == R.id.menu_item_records) {
            startActivity(new Intent(this, LiftRecordsActivity.class));
            finish();
            return true;
        }
        else if (id == R.id.menu_item_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {
        Log.wtf("#test", "onFragmentInteraction");
    }
}
