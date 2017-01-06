package com.example.pradeep.mycollage.staff.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.model.MyBatchModel;
import com.example.pradeep.mycollage.staff.userinterface.activities.PresencyDetail;

import java.util.List;

/**
 * Created by pradeep on 03/08/2016.
 */
public class MyBatchCustomAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    public MyBatchModel mMybatchmodel = null;
    List<MyBatchModel> data;

    public MyBatchCustomAdapter(Context c, List<MyBatchModel> d) {
        mContext = c;
        this.data = d;
    }

    @Override
    public int getCount() {
        if (data.size() <= 0)
            return 1;
        return data.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.presency_indicator_text_view) {
            Intent presencyDetail = new Intent(mContext, PresencyDetail.class);
            mContext.startActivity(presencyDetail);

        } else if (v.getId() == R.id.student_call_image_button) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:123456789"));
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mContext.startActivity(callIntent);

        }

    }


    static class ViewHolderItem {

        TextView name;
        TextView roll;
        TextView email;
        ImageButton Call;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.staff_my_batch_list_singal_view_layout, null);
            viewHolder = new ViewHolderItem();
            viewHolder.name = (TextView) convertView.findViewById(R.id.prasency_student_name_text_view);
            viewHolder.roll = (TextView) convertView.findViewById(R.id.presency_indicator_text_view);
            viewHolder.email = (TextView) convertView.findViewById(R.id.prasency_email_id_text_view);
            viewHolder.Call=(ImageButton)convertView.findViewById(R.id.student_call_image_button);

            // store the holder with the view.
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolderItem) convertView.getTag();

        }
        mMybatchmodel= (MyBatchModel)data.get(position);
        if(mMybatchmodel!=null) {
            viewHolder.name.setText(mMybatchmodel.getName());
            viewHolder.roll.setText(mMybatchmodel.getRoll_number());
            viewHolder.email.setText(mMybatchmodel.getEmailid());
            viewHolder.roll.setOnClickListener(this);
            viewHolder.Call.setOnClickListener(this);
        }
        return convertView;
    }

}
