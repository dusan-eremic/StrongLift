package me.stronglift.stronglift.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.stronglift.stronglift.R;


/**
 * Created by Dusan Eremic.
 */
public class LiftInputActivityFragment extends Fragment {

    public LiftInputActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lift_input, container, false);
    }
}
