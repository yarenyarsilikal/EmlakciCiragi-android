package com.example.emlakciciragi.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.emlakciciragi.R;
import com.example.emlakciciragi.UI.Fragment.CommisionOnSaleFragment;
import com.example.emlakciciragi.UI.Fragment.CommissionOnRentFragment;
import com.example.emlakciciragi.UI.Fragment.LoanCalculatorFragment;
import com.example.emlakciciragi.UI.Fragment.TitleDeedFeesFragment;
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
        loadFragment(new CommisionOnSaleFragment());
        setPageTitle(getString(R.string.commission_on_sale));

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
            case R.id.action_commission_on_sale:
                setPageTitle(getString(R.string.commission_on_rent));
                fragment = new CommisionOnSaleFragment();
                loadFragment(fragment);
                return true;
            case R.id.action__commission_on_rent:
                setPageTitle(getString(R.string.commission_on_rent));
                fragment = new CommissionOnRentFragment();
                loadFragment(fragment);
                return true;
            case R.id.action_title_deed_fees:
                setPageTitle(getString(R.string.title_deed_fees));
                fragment = new TitleDeedFeesFragment();
                loadFragment(fragment);
                return true;
            case R.id.action_loan_calculator:
                setPageTitle(getString(R.string.loan_calculator));
                fragment = new LoanCalculatorFragment();
                loadFragment(fragment);
                return true;
            default:
                return false;
        }
    }

    //endregion
}
