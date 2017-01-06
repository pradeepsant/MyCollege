package com.example.pradeep.mycollage.staff.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.model.HomeDataModel;

import java.util.List;

/**
 * Created by pradeep on 05/08/2016.
 */
public class HomeCardAdapter extends BaseAdapter
{
    HomeDataModel mstaffhomemodel;
    private Context mContext;
    List<HomeDataModel> DataList;

    // constructor with two parameters
    public HomeCardAdapter(Context mContext, List<HomeDataModel> DataList)
    {
        this.mContext = mContext;
        this.DataList = DataList;
    }


    @Override
    public int getCount()
    {
        if (DataList.size() <= 0)
            return 1;
        return DataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem {

        TextView title;
        TextView description;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolderItem viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.staff_home_single_list_item, null);
            viewHolder = new ViewHolderItem();
            viewHolder.title = (TextView) convertView.findViewById(R.id.Title_Text_view);
            viewHolder.description = (TextView) convertView.findViewById(R.id.Descreiption_Text_View);

            // store the holder with the view.
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();}
        mstaffhomemodel = (HomeDataModel) DataList.get(position);
        if (mstaffhomemodel != null) {
            viewHolder.title.setText(mstaffhomemodel.getTitle());
            viewHolder.description.setText(mstaffhomemodel.getDescription());}
        return convertView;
    }

}



