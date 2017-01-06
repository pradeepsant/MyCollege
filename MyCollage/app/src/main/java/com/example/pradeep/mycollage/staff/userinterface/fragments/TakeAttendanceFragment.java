package com.example.pradeep.mycollage.staff.userinterface.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.adapter.TakeAttendanceCustomAdapter;
import com.example.pradeep.mycollage.staff.model.RollNumberModel;

import java.util.ArrayList;

/**
 * Created by pradeep on 21/07/2016.
 */
public class TakeAttendanceFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public static final String TAG="TakeAttendanceFragment";

    Spinner mClassSpinner,mSubjectSpinner;

    GridView mgridview;
    TakeAttendanceCustomAdapter adapter;
    RollNumberModel rollnumber;
    public ArrayList<RollNumberModel> CustomGridViewValuesArr = new ArrayList<RollNumberModel>();


    /******
     * Add setHasOptionMenu(true) for adding different option menu for take attendance fragment
     *****/
    public TakeAttendanceFragment() {
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.staff_fragment_take_attendance, container, false);
        backButtonPressed(rootView);
        setGridData();
        initializeViews(rootView);
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Take Attendance");

    }
    //Back pressed Logic for fragment
    private void backButtonPressed(View rootView) {
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        Fragment fragment = new HomeFragment();
                        getFragmentManager().beginTransaction().replace(R.id.main_contains,fragment).commit();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     *
     */
    public void setGridData() {

        for (int i = 1; i <= 50; i++) {
            rollnumber = new RollNumberModel();
            /******* Firstly take data in model object ******/
            rollnumber.setmRollNumber(i);
            /******** Take Model Object in ArrayList **********/
            CustomGridViewValuesArr.add(rollnumber);
        }

    }
    /**
     *
     * @param rootView
     */
    private void initializeViews(View rootView) {
        mClassSpinner= (Spinner) rootView.findViewById(R.id.attendance_class_spinner);
        mSubjectSpinner=(Spinner) rootView.findViewById(R.id.attendance_subject_spinner);
        mSubjectSpinner.setOnItemSelectedListener(this);
        mClassSpinner.setOnItemSelectedListener(this);
        mgridview = (GridView) rootView.findViewById(R.id.grid);
        adapter = new TakeAttendanceCustomAdapter(getActivity(), CustomGridViewValuesArr);
        mgridview.setAdapter(adapter);
        mgridview.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        mgridview.setDrawSelectorOnTop(true);
        mgridview.setOnItemSelectedListener(this);
        setMultiChoiceModeListener();
    }

    /**
     *
     */
    private void setMultiChoiceModeListener() {
        mgridview.setMultiChoiceModeListener(new GridView.MultiChoiceModeListener() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate a menu resource providing context menu items
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                mode.setTitle("Attendance ");
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_Add:
                        addCurrentItem();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mode = null;

            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                int count = mgridview.getCheckedItemCount();
                if (count == 1) {
                    mode.setSubtitle("1 student present.");
                } else if (count != 0) {
                    mode.setSubtitle(count + " students present.");
                } else {
                    mode.setSubtitle(null);
                }

            }
        });
    }

    private void addCurrentItem() {
        String selected = "";
        int cntChoice = mgridview.getCount();

        SparseBooleanArray sparseBooleanArray = mgridview.getCheckedItemPositions();

        for(int i = 0; i < cntChoice; i++){

            if(sparseBooleanArray.get(i)) {

                selected += ((RollNumberModel)mgridview.getItemAtPosition(i)).getmRollNumber()+ "\n";
            }
        }
        Toast.makeText(getActivity(),"selected items:\n"+ selected,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(getActivity(), "item selected at "+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}