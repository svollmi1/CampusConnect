package org.campusconnect.campusconnect.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import org.campusconnect.campusconnect.R;
import org.campusconnect.campusconnect.fragments.AssignmentFragment;
import org.campusconnect.campusconnect.fragments.CalendarFragment;
import org.campusconnect.campusconnect.fragments.ChatFragment;
import org.campusconnect.campusconnect.fragments.CoursesFragment;
import org.campusconnect.campusconnect.fragments.MapFragment;
import org.campusconnect.campusconnect.fragments.ProfileFragment;
import org.campusconnect.campusconnect.fragments.SettingsFragment;
import org.campusconnect.campusconnect.fragments.SocialFeedsFragment;

public class AssignmentFirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private final AppCompatActivity activity = AssignmentFirstActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignmentsfirst);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.design_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Button button4 = findViewById(R.id.btn1_1);
        Button button5 = findViewById(R.id.btn1_2);


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(AssignmentFirstActivity.this,AssignmentCa1Activity.class);
                startActivity(int1);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2=new Intent(AssignmentFirstActivity.this,AssignmentCa2Activity.class);
                startActivity(int2);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_assignment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AssignmentFragment()).commit();
                finish();
                // TODO: Kill activity and load new fragment
                break;
            case R.id.nav_calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalendarFragment()).commit();
                finish();
                // TODO: Kill activity and load new fragment
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChatFragment()).commit();
                finish();
                // TODO: Kill activity and load new fragment
                break;
            case R.id.nav_courses:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CoursesFragment()).commit();
                finish();
                // TODO: Kill activity and load new fragment
                break;
            case R.id.nav_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MapFragment()).commit();
                finish();
                // TODO: Kill activity and load new fragment
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                finish();
                // TODO: Kill activity and load new fragment
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                finish();
                // TODO: Kill activity and load new fragment
                break;
            case R.id.nav_socialfeed:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SocialFeedsFragment()).commit();
                finish();
                // TODO: Kill activity and load new fragment
                break;
            case R.id.nav_signout:
                FirebaseAuth.getInstance().signOut();
                Intent loginIntent = new Intent(activity, LoginActivity.class);
                startActivity(loginIntent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();}
    }
}
