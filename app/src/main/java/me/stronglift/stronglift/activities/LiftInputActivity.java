package me.stronglift.stronglift.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Date;

import me.stronglift.stronglift.fragments.LiftHistoryFragment;
import me.stronglift.stronglift.fragments.dummy.DummyContent;
import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftType;

import me.stronglift.stronglift.R;
import me.stronglift.stronglift.model.User;


/**
 * Created by Dusan Eremic.
 */
public class LiftInputActivity extends Activity implements LiftHistoryFragment.OnFragmentInteractionListener {

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
                DummyContent.ITEMS.add(lift);
                LiftHistoryFragment liftHistoryFragment = (LiftHistoryFragment) getFragmentManager().findFragmentById(R.id.liftHistoryFragment);
                liftHistoryFragment.refreshData();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item_exit) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {
        Log.wtf("#test", "onFragmentInteraction");
    }
}
