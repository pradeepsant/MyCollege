package com.example.pradeep.mycollage.staff.userinterface.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pradeep.mycollage.R;

/**
 * Created by pradeep on 29/07/2016.
 */
public class CardDetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_card_details_layout);
        TextView mTitle= (TextView) findViewById(R.id.Card_Details_Title_Text_view);
        TextView mDescription= (TextView) findViewById(R.id.Card_Deatils_Descreiption_Text_View);
        ImageView mBackgroundImage= (ImageView) findViewById(R.id.Card_Details_Background_Image_View);
        Intent intent = getIntent();
        if (null != intent) {
            String StringData= intent.getStringExtra("titlekey");
            String DetailedDescription=intent.getStringExtra("deskey");
            mTitle.setText(StringData);
            mDescription.setText(DetailedDescription);

        }
    }
}
