package com.example.pradeep.mycollage.staff.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.model.CardDataModel;
import com.example.pradeep.mycollage.staff.userinterface.activities.CardDetailsActivity;

import java.util.List;

/**
 * Created by pradeep on 28/07/2016.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private Context mContext;
    List<CardDataModel> DataList;
    // constructor with two parameters
    public CardAdapter(Context mContext, List<CardDataModel> DataList) {
        this.mContext = mContext;
        this.DataList = DataList;
    }
    //Inflate custom items of card view
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.staff_card_single_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(DataList.get(position).getTitel());
        holder.description.setText(DataList.get(position).getDescription());
        holder.image.setImageResource(DataList.get(position).getmImage());
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }
    //ViewHolder calss
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private static final String LOGCAT = "MyViewHolder";
        public TextView title;
        public TextView description;
        public ImageView image;



        public MyViewHolder( final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Log.d(LOGCAT, "tvSubjectName onClick at" + getPosition());
                    int id=itemView.getId();
                    Intent i=new Intent(mContext,CardDetailsActivity.class);
                    String titleintent=title.getText().toString();
                    String descriptionintent=description.getText().toString();
                    i.putExtra("titlekey", titleintent);
                    i.putExtra("deskey", descriptionintent);
                    i.setType("image");
                    //Fire that second activity
                    mContext.startActivity(i);
                }
            });
            image= (ImageView) itemView.findViewById(R.id.Card_background_image_view);
            title=(TextView) itemView.findViewById(R.id.Title_Text_view);
            description= (TextView) itemView.findViewById(R.id.Descreiption_Text_View);
        }
    }
}
