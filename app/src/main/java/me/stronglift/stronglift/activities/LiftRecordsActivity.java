package me.stronglift.stronglift.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import me.stronglift.stronglift.R;

/**
 * Activity koji prikazuje listu postignutih rekorda.
 * <p>
 * Created by Dusan Eremic.
 */
public class LiftRecordsActivity extends Activity {

    /**
     * Nakon kreiranja Activity-ja, setovaćemo kontent da koristi
     * layout koji je definisan u XML fajlu.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift_records);
    }


    /**
     * Nakon kreiranje menija, setovaćemo sadržaj menija da koristi
     * stavke koje su definisane u XML fajlu.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
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
