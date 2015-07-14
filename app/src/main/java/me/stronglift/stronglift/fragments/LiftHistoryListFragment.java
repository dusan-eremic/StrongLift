package me.stronglift.stronglift.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.stronglift.stronglift.R;
import me.stronglift.stronglift.adapters.LiftHistoryAdapter;
import me.stronglift.stronglift.dao.DummyContent;
import me.stronglift.stronglift.interfaces.OnFragmentInteractionListener;
import me.stronglift.stronglift.model.Lift;
import me.stronglift.stronglift.model.LiftType;

/**
 * A fragment representing a list of Items.
 * <p>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class LiftHistoryListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private ListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private BaseAdapter mAdapter;

    private List<Lift> liftList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LiftHistoryListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        liftList = new ArrayList<>(DummyContent.ITEMS);
        mAdapter = new LiftHistoryAdapter(getActivity(), liftList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lifthistory, container, false);

        // Set the adapter
        mListView = (ListView) view.findViewById(R.id.liftHistoryListView);
        mListView.setAdapter(mAdapter);

        setupLiftSpinnerFilter((Spinner) view.findViewById(R.id.liftSpinner), liftList);

        return view;
    }

    private void setupLiftSpinnerFilter(final Spinner liftSpinner, final List<Lift> list) {

            List<String> liftTypes = new ArrayList<>();
            liftTypes.add(getString(R.string.filter_all));

            for(LiftType liftType : Arrays.asList(LiftType.values())) {
                liftTypes.add(liftType.toString());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, liftTypes);
            adapter.setDropDownViewResource(R.layout.spinner_item);
        liftSpinner.setAdapter(adapter);

            liftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    list.clear();
                    if(id == 0) {
                        list.addAll(DummyContent.ITEMS);
                    }
                    else {
                        for (Lift lift : DummyContent.ITEMS) {
                            if (lift.getLiftType().getId() == id-1) {
                                list.add(lift);
                            }
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void refreshData() {
        mAdapter.notifyDataSetChanged();
    }

}
