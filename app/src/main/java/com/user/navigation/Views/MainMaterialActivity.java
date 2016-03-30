package com.user.navigation.Views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.user.navigation.Fragments.AboutFragment;
import com.user.navigation.Fragments.ContactsFragment;
import com.user.navigation.Fragments.FavoritesFragment;
import com.user.navigation.Fragments.HomeFragment;
import com.user.navigation.R;

public class MainMaterialActivity extends ActionBarActivity implements MaterialFragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainMaterialActivity.class.getSimpleName();

    private boolean isFavorite = false;
    private Toolbar mToolbar;
    private MaterialFragmentDrawer drawerFragment;
    // Progress bar
    public ProgressDialog progressDlg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (MaterialFragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        isFavorite = false;
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.nav_title_home);
                break;

            case 1:
                isFavorite = true;
                fragment = new FavoritesFragment();
                invalidateOptionsMenu();
                title = getString(R.string.nav_title_favorites);
                break;

            case 2:
                fragment = new ContactsFragment();
                title = getString(R.string.nav_title_contacts);
                break;

            case 3:
                fragment = new AboutFragment();
                title = getString(R.string.nav_title_about);
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if(isFavorite) {
            inflater.inflate(R.menu.favorite_menu, menu);
        } else {
            inflater.inflate(R.menu.default_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(MainMaterialActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_purchase:
                Intent favoriteIntent = new Intent(MainMaterialActivity.this, PurchaseActivity.class);
                startActivity(favoriteIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
