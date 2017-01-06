package com.example.pradeep.mycollage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pradeep.mycollage.staff.userinterface.fragments.AssignmentFragment;
import com.example.pradeep.mycollage.staff.userinterface.fragments.MyProfileFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG ="MainActivity" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_contains);
                String tag = currentFragment.getTag();
                switch (tag) {
                    case MyProfileFragment.TAG:
                        Snackbar.make(view, "Staff My Profile Fab clicked", Snackbar.LENGTH_LONG).show();
                        break;
                    case AssignmentFragment.TAG:
                        Snackbar.make(view, "Staff Assignement Fab clicked", Snackbar.LENGTH_LONG).show();
                        break;
                    default:
                        Log.e(TAG, "Unhandled FAB fragment tag " + tag);
                        Snackbar.make(view, "Not sure what to do...my bad", Snackbar.LENGTH_SHORT).show();
                        break;
               }
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.staff_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("AssignmentFragment");
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
