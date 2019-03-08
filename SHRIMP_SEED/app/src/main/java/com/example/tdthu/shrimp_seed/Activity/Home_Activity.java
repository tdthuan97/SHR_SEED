package com.example.tdthu.shrimp_seed.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tdthu.shrimp_seed.Fragment.Fragment_Account;
import com.example.tdthu.shrimp_seed.Fragment.Fragment_Hatchery_List_of_Requests;
import com.example.tdthu.shrimp_seed.Fragment.Fragment_Hatchery_Request_Contract;
import com.example.tdthu.shrimp_seed.Fragment.Fragment_List_Of_Contracts;
import com.example.tdthu.shrimp_seed.Fragment.Fragment_List_Of_Producers;
import com.example.tdthu.shrimp_seed.Fragment.Fragment_List_Of_Traders;
import com.example.tdthu.shrimp_seed.Fragment.Fragment_Trader_List_Of_Request_Contracts;
import com.example.tdthu.shrimp_seed.R;

public class Home_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    String user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getDataIntent();
        init();

    }
    private void getDataIntent(){
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
    }
    private void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().clear();
        if(user.equals("hatchery"))
            navigationView.inflateMenu(R.menu.activity_hatchery_drawer);
        else if(user.equals("trader"))
            navigationView.inflateMenu(R.menu.activity_trader_drawer);

        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
        displaySelectedScreen(item.getItemId());
        return true;
    }
    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_account:
                fragment = new Fragment_Account();
                toolbar.setTitle("Account");
                break;
            case R.id.nav_hatchery_list_of_requests:
                fragment = new Fragment_Hatchery_List_of_Requests();
                toolbar.setTitle("List of request contracts");
                break;
            case R.id.nav_hatchery_trader:
                fragment = new Fragment_List_Of_Traders();
                toolbar.setTitle("List of traders");
                break;
            case R.id.nav_producer:
                fragment = new Fragment_List_Of_Producers();
                toolbar.setTitle("List of producers");
                break;
            case R.id.nav_hatchery_contract:
                fragment = new Fragment_List_Of_Contracts();
                toolbar.setTitle("List of contracts");
                break;
            case R.id.nav_trader_list_request:
                fragment = new Fragment_Trader_List_Of_Request_Contracts();
                toolbar.setTitle("List of request from hatchery");
                break;

        }
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
