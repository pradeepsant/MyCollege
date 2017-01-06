package com.example.pradeep.mycollage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pradeep.mycollage.parent.userinterface.activities.HomeActivity;
import com.example.pradeep.mycollage.staff.userinterface.activities.NavigationDrawerActivity;
import com.example.pradeep.mycollage.student.userinterface.activities.NavigationDrawer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pradeep on 02/08/2016.
 */
public class LoginActivity extends Activity
{
    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginButton;
    private TextView mForgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);;
        initializeViews();
        LoginButtonClick();
        mForgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri address= Uri.parse("https://www.google.co.in/");
                Intent browser= new Intent(Intent.ACTION_VIEW, address);
                startActivity(browser);

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        mPassword.setText("");
    }

    private void LoginButtonClick() {
        mLoginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mUsername.getText().toString().equals("student") && mPassword.getText().toString().equals("student"))
                        {
                            Intent intent = new Intent(LoginActivity.this,NavigationDrawer.class);
                            startActivity(intent);

                        }
                        else if(mUsername.getText().toString().equals("staff") && mPassword.getText().toString().equals("staff"))
                        {
                            Intent intent = new Intent(LoginActivity.this,NavigationDrawerActivity.class);
                            startActivity(intent);

                        }
                        else if (mUsername.getText().toString().equals("parent") && mPassword.getText().toString().equals("parent")) {
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Username and password is NOT correct",
                                    Toast.LENGTH_SHORT).show();
                        }

                        final String email = mUsername.getText().toString();
                        if (!isValidEmail(email)) {

                            //TODO uncomment it for email validation
                           // mUsername.setError("Invalid Email");
                        }

                        final String pass = mPassword.getText().toString();
                        if (!isValidPassword(pass)) {
                            mPassword.setError("Invalid Password");
                        }

                    }
                }
        );

    }


    private void initializeViews()
    {
        mUsername = (EditText) findViewById(R.id.UserNameEditText);
        mPassword = (EditText) findViewById(R.id.PasswordEditText);
        mLoginButton = (Button) findViewById(R.id.LoginButton);
        mForgotpassword= (TextView) findViewById(R.id.ForgotPasswordTextView);
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 4) {
            return true;
        }
        return false;
    }


}
