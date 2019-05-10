package com.example.emlakciciragi.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.emlakciciragi.R;
import com.example.emlakciciragi.UI.Fragment.FirstFragment;
import com.example.emlakciciragi.UI.Fragment.SecondFragment;
import com.example.emlakciciragi.UI.Fragment.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment;

    //region LIFECYCLE METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiliazeViews();
    }



    //endregion

    //region FLOW

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    //endregion

    //region INITIALIZATION METHODS

    public void initiliazeViews() {
        loadFragment(new FirstFragment());
        setPageTitle(getString(R.string.first_fragment));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    //endregion

    //region STORY METHODS
    private void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    //endregion

    //region HANDLE MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Bundle bundle = new Bundle();
            openActivity(bundle, SettingsActivity.class);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.action_first_fragment:
                setPageTitle(getString(R.string.first_fragment));
                fragment = new FirstFragment();
                loadFragment(fragment);
                return true;
            case R.id.action_second_fragment:
                setPageTitle(getString(R.string.second_fragment));
                fragment = new SecondFragment();
                loadFragment(fragment);
                return true;
            case R.id.action_third_fragment:
                setPageTitle(getString(R.string.third_fragment));
                fragment = new ThirdFragment();
                loadFragment(fragment);
                return true;
            default:
                return false;
        }
    }

    //endregion
}
