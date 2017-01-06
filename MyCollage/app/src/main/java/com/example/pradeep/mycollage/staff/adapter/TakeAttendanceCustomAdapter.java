package com.example.pradeep.mycollage.staff.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.model.RollNumberModel;

import java.util.ArrayList;

/**
 * Created by pradeep on 26/07/2016.
 */
public class TakeAttendanceCustomAdapter extends BaseAdapter {
    private Context mContext;
    public ArrayList<RollNumberModel> data;
    public TakeAttendanceCustomAdapter(Context c, ArrayList d) {
        mContext = c;
        this.data = d;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public RollNumberModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }



    static class ViewHolderItem {

        TextView textViewItem;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.staff_take_attendance_grid_single_view,parent,false);
            viewHolder = new ViewHolderItem();
            viewHolder.textViewItem = (TextView) convertView.findViewById(R.id.grid_number_text_view);
            // store the holder with the view.
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolderItem) convertView.getTag();

        }
        /*mRollnumber = (RollNumberModel) data.get(position);
        if(mRollnumber!=null) {
          viewHolder.textViewItem.setText(String.valueOf(mRollnumber.getmRollNumber()));
        }*/
        viewHolder.textViewItem.setText(String.valueOf(((RollNumberModel) data.get(position)).getmRollNumber()));
        return convertView;
    }


}



