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
import me.stronglift.stronglift.callbacks.AuthManagerCallback;
import me.stronglift.stronglift.fragments.LiftInputListFragment;
import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftType;
import me.stronglift.stronglift.rest.AuthManager;
import me.stronglift.stronglift.rest.RestCallback;
import me.stronglift.stronglift.rest.RestService;
import retrofit.client.Response;

/**
 * Activity koji omogućava unos novih liftova.
 * <p/>
 * Ovo je osnovni ekran aplikacije.
 * <p/>
 * Created by Dusan Eremic.
 */
public class LiftInputActivity extends Activity {

    /**
     * Nakon kreiranja Activity-ja, setovaćemo kontent da koristi
     * layout koji je definisan u XML fajlu.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AuthManager.init(this, new AuthManagerCallback() {
            @Override
            public void authManagerInitialized() {
                setContentView(R.layout.activity_lift_input);

                // Podešavanje obe padajuće liste i polja za unos težine
                setupLiftSpinner();
                setupWeightSpinner();
                setupAddButton();
            }
        });
    }

    /**
     * Metoda setuje click listener na Add dugme.
     */
    private void setupAddButton() {
        Button addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addLiftToList();
                } catch (NumberFormatException nfe) {
                    Toast.makeText(LiftInputActivity.this, getString(R.string.invalid_weight), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Metoda setuje listu u padajuću listu za izbor tipa lifta.
     */
    private void setupLiftSpinner() {
        Spinner liftSpinner = (Spinner) findViewById(R.id.liftSpinner);
        ArrayAdapter<LiftType> liftSpinnerAdapter = new ArrayAdapter<LiftType>(this, android.R.layout.simple_spinner_item, Arrays.asList(LiftType.values()));
        liftSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liftSpinner.setAdapter(liftSpinnerAdapter);
    }

    /**
     * Metoda setuje listu u padajuću listu za izbor težine.
     */
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

    /**
     * Metoda kreira Lift instancu na osnovu unetih podataka.
     *
     * @return Novi Lift.
     */
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

    /**
     * Metoda poziva REST service addList i ako je poziv bio uspešan dodaje
     * novi Lift u listu prikazanu u fragmentu liftListFragment
     * LiftInputList fragmentu
     */
    private void addLiftToList() {
        final Lift lift = getLiftEntered();
        RestService.
                getLiftService().
                addLift(AuthManager.getUser(), lift, new RestCallback<Lift>(LiftInputActivity.this, "#addLift") {
                    @Override
                    public void success(Lift lift, Response response) {
                        ((LiftInputListFragment) getFragmentManager()//
                                .findFragmentById(R.id.liftInputListFragment)) //
                                .addLift(lift);
                        Log.d("#LiftInputActivity", "Lift added: " + lift.toString());
                    }
                });
    }

    /**
     * Nakon kreiranje menija, setovaćemo sadržaj meni a da koristi
     * stavke koje su definisane u XML fajlu.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input, menu);
        return true;
    }

    /**
     * Metoda poziva odgovarajući Activity nakon nakon selekcije akcije na ActionBar-u.
     *
     * @param item Selektovana stavka u meniju ili akcija na ActionBar-u.
     * @return true ako je selekcija obrađena, false ako nije
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
