package com.example.pradeep.mycollage.staff.userinterface.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.pradeep.mycollage.LoginActivity;
import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.staff.MediaUtils;
import com.example.pradeep.mycollage.staff.Utils;
import com.example.pradeep.mycollage.staff.userinterface.fragments.AssignmentFragment;
import com.example.pradeep.mycollage.staff.userinterface.fragments.HomeFragment;
import com.example.pradeep.mycollage.staff.userinterface.fragments.MyBatchFragment;
import com.example.pradeep.mycollage.staff.userinterface.fragments.MyProfileFragment;
import com.example.pradeep.mycollage.staff.userinterface.fragments.NewsAndEventFragment;
import com.example.pradeep.mycollage.staff.userinterface.fragments.NotesFragment;
import com.example.pradeep.mycollage.staff.userinterface.fragments.TakeAttendanceFragment;

import java.io.File;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    //public static final String TAG="StaffNavigationDrawer";

    Fragment mFragment = null;
    ImageView ivHeaderPhoto;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = mFragment.getTag();
                switch (tag) {
                    case HomeFragment.TAG:
                        Snackbar.make(view, "Staff Home Screen Fab clicked", Snackbar.LENGTH_LONG).show();
                        break;
                    case MyProfileFragment.TAG:
                        Snackbar.make(view, "Staff My Profile Fab clicked", Snackbar.LENGTH_LONG).show();
                        break;
                    case AssignmentFragment.TAG:
                        Snackbar.make(view, "Staff Assignment Fab clicked", Snackbar.LENGTH_LONG).show();
                        break;
                    case MyBatchFragment.TAG:
                        Snackbar.make(view, "Staff My Batch Fab clicked", Snackbar.LENGTH_LONG).show();
                        break;
                    case NewsAndEventFragment.TAG:
                        Snackbar.make(view, "Staff News And Event Fab clicked", Snackbar.LENGTH_LONG).show();
                        break;
                    case NotesFragment.TAG:
                        Snackbar.make(view, "Staff Notes Fab clicked", Snackbar.LENGTH_LONG).show();
                        break;
                    case TakeAttendanceFragment.TAG:
                        Snackbar.make(view, "Staff Take Attendance Fab clicked", Snackbar.LENGTH_LONG).show();
                        break;
                    default:
                        //Log.e(NavigationDrawerActivity.TAG, "Unhandled FAB fragment tag " + tag);
                        Snackbar.make(view, "Not sure what to do...my bad", Snackbar.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);
        ivHeaderPhoto= (ImageView) headerLayout.findViewById(R.id.image_view);


        Fragment fragment = new HomeFragment();
        getFragmentManager().beginTransaction().replace(R.id.main_contains,fragment).addToBackStack(null).commit();
        setTitle("Welcome staff name");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        this.mFragment = fragment;
        if(fragment instanceof HomeFragment){
            fab.setImageResource(android.R.drawable.ic_input_add);
        } else if(fragment instanceof MyProfileFragment){
            fab.setImageResource(android.R.drawable.ic_input_add);
        } else if(fragment instanceof AssignmentFragment){
            fab.setImageResource(android.R.drawable.ic_menu_add);
        } else if(fragment instanceof MyBatchFragment){
            fab.setImageResource(android.R.drawable.ic_input_get);
        } else if(fragment instanceof NewsAndEventFragment){
            fab.setImageResource(android.R.drawable.ic_input_delete);
        } else if(fragment instanceof NotesFragment){
            fab.setImageResource(android.R.drawable.ic_btn_speak_now);
        } else if(fragment instanceof TakeAttendanceFragment){
                fab.setImageResource(android.R.drawable.ic_dialog_email);
            }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.staff_navigation_drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String tag="";
        if (id == R.id.nav_home) {
            mFragment = new HomeFragment();
            tag= HomeFragment.TAG;
        } else if (id == R.id.nav_my_profile) {
            mFragment = new MyProfileFragment();
            tag= MyProfileFragment.TAG;
        }else if (id == R.id.nav_take_attendance) {
            mFragment = new TakeAttendanceFragment();
            tag= TakeAttendanceFragment.TAG;
        } else if (id == R.id.nav_mybatch) {
            mFragment = new MyBatchFragment();
            tag=MyBatchFragment.TAG;
        } else if (id == R.id.nav_assignment) {
            mFragment = new AssignmentFragment();
            tag= AssignmentFragment.TAG;
        } else if (id == R.id.nav_notes) {
            mFragment = new NotesFragment();
            tag=NotesFragment.TAG;
        } else if (id == R.id.nav_News) {
            mFragment = new NewsAndEventFragment();
            tag=NewsAndEventFragment.TAG;
        } else if (id == R.id.nav_Logout) {
            mFragment = null;
            Intent logOut = new Intent(this, LoginActivity.class);
            tag="null";
            startActivity(logOut);
            finish();
        }
        if (mFragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_contains, mFragment,tag).commit();
            setTitle(item.getTitle());

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
