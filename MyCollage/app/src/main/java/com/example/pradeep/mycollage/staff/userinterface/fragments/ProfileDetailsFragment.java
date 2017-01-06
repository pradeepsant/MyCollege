package com.example.pradeep.mycollage.staff.userinterface.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pradeep.mycollage.R;

/**
 * Created by pradeep on 22/07/2016.
 */
public class ProfileDetailsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.staff_profile_details_layout, container, false);
        return rootView;
    }

}
