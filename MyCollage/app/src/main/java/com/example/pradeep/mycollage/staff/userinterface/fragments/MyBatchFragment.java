package com.example.pradeep.mycollage.staff.userinterface.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.adapter.MyBatchCustomAdapter;
import com.example.pradeep.mycollage.staff.model.MyBatchModel;
import com.example.pradeep.mycollage.staff.model.MyData;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 */
public class MyBatchFragment extends android.app.Fragment implements AdapterView.OnItemClickListener {
    public static final String TAG = "MyBatchFragment";

    private ListView listview;
    private MyBatchCustomAdapter adapter;
    private List<MyBatchModel> DataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.staff_fragment_my_batch, container, false);
        backButtonPressed(rootView);

        listview = (ListView) rootView.findViewById(R.id.My_batch_list_view);
        DataList = new ArrayList<>();
        adapter = new MyBatchCustomAdapter(getActivity(), DataList);
        listview.setAdapter(adapter);
        for (int Position = 0; Position < MyData.Name.length; Position++) {
            DataList.add(new MyBatchModel(
                    MyData.Name[Position],
                    MyData.Rollnumber[Position],
                    MyData.EmailID[Position]
            ));
        }
        listview.setDividerHeight(0);
        listview.setOnItemClickListener(this);
        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("MyBatch");

    }
    private void backButtonPressed(View rootView) {
        //Back pressed Logic for fragment
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        //getActivity().finish();
                        Fragment fragment = new HomeFragment();
                        getFragmentManager().beginTransaction().replace(R.id.main_contains, fragment).commit();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
