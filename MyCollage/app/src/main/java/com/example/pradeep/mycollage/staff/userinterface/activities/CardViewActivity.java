package com.example.pradeep.mycollage.staff.userinterface.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.adapter.CardAdapter;
import com.example.pradeep.mycollage.staff.model.CardDataModel;
import com.example.pradeep.mycollage.staff.model.MyData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pradeep on 28/07/2016.
 */
public class CardViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<CardDataModel> DataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_fragment_news_event);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        DataList = new ArrayList<>();
        adapter = new CardAdapter(this, DataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
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
    }
}
