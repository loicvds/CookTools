package com.cooktools.lo.cooktools.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cooktools.lo.cooktools.services.NavigationDrawerCallbacks;
import com.cooktools.lo.cooktools.modules.NavigationDrawerFragment;
import com.cooktools.lo.cooktools.R;

/**
 * MainActivity contenant le Navigation drawer et l'action bar.
 */

public class Home extends AppCompatActivity
        implements NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment;
        switch(position){
            case 0:
                fragment = getSupportFragmentManager().findFragmentByTag(Calculatrice.TAG);
                if (fragment == null) {
                    fragment = new Calculatrice();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, Calculatrice.TAG).commit();
                break;
            case 1:
                fragment = getSupportFragmentManager().findFragmentByTag(Timer.TAG);
                if (fragment == null) {
                    fragment = new Timer();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, Timer.TAG).commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


}
