package com.example.pradeep.mycollage.staff.userinterface.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.adapter.CardAdapter;
import com.example.pradeep.mycollage.staff.model.CardDataModel;
import com.example.pradeep.mycollage.staff.model.MyData;

import java.util.ArrayList;
import java.util.List;

public class NewsAndEventFragment extends Fragment {
    public static final String TAG="NewsAndEventFragment";

    private RecyclerView recyclerView;
        private CardAdapter adapter;
        private List<CardDataModel> DataList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.staff_fragment_news_event, container, false);
        backButtonPressed(rootView);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        DataList = new ArrayList<>();
        adapter = new CardAdapter(getActivity(), DataList);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        for (int Position = 0; Position < MyData.NewsAndEventTitleArray.length; Position++) {
            DataList.add(new CardDataModel(
                    MyData.NewsAndEventTitleArray[Position],
                    MyData.NewsAndEventDateArray[Position],
                    MyData.drawableArray[Position]
            ));
        }

        // prepareCardList();



        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("News And Event");

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
                        getFragmentManager().beginTransaction().replace(R.id.main_contains,fragment).commit();
                        return true;
                    }
                }
                return false;
            }
        });
    }





   /* prepareCardList(){
            /*//******** Take Model Object in ArrayList **********//**//*
        DataList.add(new CardDataModel("A","AAAAAAAAAAAAAAAAA",R.drawable.image));
        DataList.add(new CardDataModel("B","BBBBBBBBBBBBBBBBB",R.drawable. abc));
        DataList.add(new CardDataModel("C","CCCCCCCCCCCCCCCCC",R.drawable.apeacefulsight));
        DataList.add(new CardDataModel("D","DDDDDDDDDDDDDDDDD",R.drawable.art));
        DataList.add(new CardDataModel("E","EEEEEEEEEEEEEEEEE",R.drawable.autumnleaves));
        DataList.add(new CardDataModel("F","FFFFFF",R.drawable.beautiful));
        DataList.add(new CardDataModel("G","GGGGGGGGGGGG",R.drawable.beautifulnature));
        DataList.add(new CardDataModel("H","HHHHHHHHHHHHHHHHHHHH",R.drawable.beautifulnaturescene));
        DataList.add(new CardDataModel("I","IIIIIIIIIIIIIIIIIIIIIII",R.drawable.beautifulscene));
        DataList.add(new CardDataModel("J","JJJJJJJJJJJJJJJJJJJJJJJJJJJJ",R.drawable.beautifulwaterfallpicture));
        DataList.add(new CardDataModel("K","KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK",R.drawable.bionic107));
        DataList.add(new CardDataModel("L","LLLLLLLLLL",R.drawable.becauseiloveyou));
        DataList.add(new CardDataModel("M","MMMMMMMMMMMMMMMMMM",R.drawable.bird));
        DataList.add(new CardDataModel("N","NNNNNNNNNNNNNNNNNNN",R.drawable.dock));
        DataList.add(new CardDataModel("O","OOOOOOOOOOOOOOOOOO",R.drawable.blackflower));
        DataList.add(new CardDataModel("P", "PPPPPPPPPPPPPPPPPPPPPPPP", R.drawable.falls2));
    }*/
    }


