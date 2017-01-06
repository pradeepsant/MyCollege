package com.example.pradeep.mycollage.student.userinterface.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pradeep.mycollage.R;

/**
 * Created by pradeep on 02/08/2016.
 */
public class HomeFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.student_home_layout, container, false);
        return  rootView;
    }
}
