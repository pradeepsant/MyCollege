package com.example.pradeep.mycollage.student.userinterface.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pradeep.mycollage.R;
import com.example.pradeep.mycollage.student.userinterface.fragments.AssignmentFragment;
import com.example.pradeep.mycollage.student.userinterface.fragments.HomeFragment;
import com.example.pradeep.mycollage.student.userinterface.fragments.MyProfileFragment;
import com.example.pradeep.mycollage.student.userinterface.fragments.NewsAndEventFragment;
import com.example.pradeep.mycollage.student.userinterface.fragments.NotesFragment;
import com.example.pradeep.mycollage.student.userinterface.fragments.ProfileDetailsFragment;
import com.example.pradeep.mycollage.student.userinterface.fragments.SyllabusFragment;
import com.example.pradeep.mycollage.student.userinterface.fragments.TimeTableFragment;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment = new HomeFragment();
        getFragmentManager().beginTransaction().replace(R.id.Student_main_contains,fragment).commit();
        setTitle("Welcome student name");
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
        getMenuInflater().inflate(R.menu.student_navigation_drawer, menu);
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
        Fragment fragment = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.student_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.student_nav_my_profile) {
            fragment = new MyProfileFragment();
        } else if (id == R.id.student_nav_assignment) {
            fragment = new AssignmentFragment();
        } else if (id == R.id.student_nav_notes) {
            fragment = new NotesFragment();
        } else if (id == R.id.student_nav_news_and_events) {
            fragment = new NewsAndEventFragment();
        } else if (id == R.id.student_nav_time_table) {
            fragment = new TimeTableFragment();
        } else if (id == R.id.student_nav_syllabus) {
            fragment = new SyllabusFragment();
        } else if (id == R.id.nav_Logout) {
            finish();
        }
        else if (id == R.id.image_view) {
            fragment = new ProfileDetailsFragment();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.Student_main_contains, fragment).commit();
            setTitle(item.getTitle());

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
