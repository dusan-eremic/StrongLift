package me.stronglift.stronglift.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import me.stronglift.stronglift.R;

/**
 * Created by Dusan Eremic.
 */
public class LiftRecordsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lift_records);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_records, menu);
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
}
