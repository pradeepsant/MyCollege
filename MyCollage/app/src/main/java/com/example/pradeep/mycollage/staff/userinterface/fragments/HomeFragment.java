package com.example.pradeep.mycollage.staff.userinterface.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.adapter.HomeCardAdapter;
import com.example.pradeep.mycollage.staff.model.HomeDataModel;
import com.example.pradeep.mycollage.staff.model.MyData;

import java.util.ArrayList;
import java.util.List;

/**
 * Staff home screen.
 */

public class HomeFragment extends Fragment
{
    public static final String TAG ="HomeFragment" ;
    private ListView listview;
    private HomeCardAdapter adapter;
    private List<HomeDataModel> DataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.staff_home_layout, container, false);

        listview = (ListView) rootView.findViewById(R.id.Staff_home_list_view);
        listview.setDivider(null);
        listview.setDividerHeight(0);
        DataList = new ArrayList<>();
        adapter = new HomeCardAdapter(getActivity(), DataList);
        listview.setAdapter(adapter);
        for (int Position = 0; Position < MyData.StaffHomeTitleArray.length; Position++)
        {
            DataList.add(new HomeDataModel(
                    MyData.StaffHomeTitleArray[Position],
                    MyData.StaffHomeDescriptionArray[Position]
            ));
        }
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Home");

    }


}
