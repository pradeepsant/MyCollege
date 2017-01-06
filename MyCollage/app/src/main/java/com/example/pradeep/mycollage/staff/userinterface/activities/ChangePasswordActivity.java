package com.example.pradeep.mycollage.staff.userinterface.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pradeep.mycollage.R;

/**
 * Created by pradeep on 27/07/2016.
 */
public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mOldpassword,mNewpassword,mConfirmpassword;
    Button mSaveButton,mCancleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_menu_activity_change_password);
        mOldpassword= (EditText) findViewById(R.id.Old_Password_Edit_text);
        mNewpassword=(EditText) findViewById(R.id.New_Password_Edit_text);
        mConfirmpassword=(EditText) findViewById(R.id.Confirm_Password_Edit_text);
        mSaveButton=(Button) findViewById(R.id.Save_button_for_change_password);
        mCancleButton=(Button) findViewById(R.id.Cancle_button_for_change_password);
        mSaveButton.setOnClickListener(this);
        mCancleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Save_button_for_change_password:
                break;
            case R.id.Cancle_button_for_change_password:
                finish();
                break;

        }


    }
}
