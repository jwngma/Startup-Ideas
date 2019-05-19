package com.startupideas.startupideas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    private CardView business_idea_btn,Indian_Startup_Procedures_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("StartUp Ideas");

        navigationView=findViewById(R.id.navigation_nav);
        drawerLayout = findViewById(R.id.navigation_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_ope, R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);


        /* init Activities*/

        initAllIdeas();

    }

    private void initAllIdeas() {

        business_idea_btn=findViewById(R.id.Business_Ideas);
        Indian_Startup_Procedures_btn=findViewById(R.id.Indian_Startup_Procedures);






        business_idea_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToBusinessideaActivity();
            }
        });
        Indian_Startup_Procedures_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendtoIndian_Startup_Procedures();
            }
        });
    }

    private void sendtoIndian_Startup_Procedures() {
        Intent intent= new Intent(MainActivity.this,IndianStartupProceduresActivity.class);
        startActivity(intent);
    }

    private void sendToBusinessideaActivity() {
        Intent intent= new Intent(MainActivity.this,BusinessIdeasActivity.class);
        startActivity(intent);
    }

    private void setupNavigation() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.settings:
                        setFragment(new SettingsFragment());
                        getSupportActionBar().setTitle("Settings");
                        drawerLayout.closeDrawers();
                        menuItem.setChecked(true);
                        break;

                }
                return true;
            }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentManager manager= getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.search_menu:
                ;
        }
        return true;
    }
}
