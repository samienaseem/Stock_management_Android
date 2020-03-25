package com.akki.stock.stock;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity  {

    Toolbar tb;
    ActionBarDrawerToggle toggle;
    MenuItem prevItem;
    DrawerLayout drawer;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tb=(Toolbar)findViewById(R.id.toolbar);




        NavigationView navigationView = (NavigationView) findViewById(R.id.navigator);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("Stock");
        tb.setTitleTextColor(0XFFFFFFFF);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if (prevItem != null)
                    prevItem.setChecked(false);

                item.setCheckable(true);
                item.setChecked(true);

                prevItem = item;


                switch (item.getItemId()) {

                    case R.id.dashboard:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.home_layout_id, new HomeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Stock");
                        drawer.closeDrawers();
                        break;

                    case R.id.add_item:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.home_layout_id, new FragmentAddItem());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Add Item");
                        drawer.closeDrawers();
                        break;

                   case R.id.activity_log:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.home_layout_id, new LogFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Activity Log");
                        drawer.closeDrawers();
                        break;

                }
                return false;


            }

        });

        MenuItem item = navigationView.getMenu().findItem(R.id.dashboard);
        item.setCheckable(true);
        item.setChecked(true);
        prevItem=item;
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.home_layout_id, new HomeFragment());
        fragmentTransaction.commit();
        toggle = new ActionBarDrawerToggle(
                this, drawer,tb,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        drawer.closeDrawers();

    }

}
