package com.example.emlakciciragi.UI.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;


import com.example.emlakciciragi.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by Yaren YARŞILIKAL on 17.04.2019.
 */
public class BaseActivity extends AppCompatActivity {

    public static final String ENTER_ANIMATION = "enterAnimation";
    private CharSequence pageTitle = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void openActivity(@Nullable Bundle extras, Class openClass) {
        Intent open = new Intent(this, openClass);
        if (extras != null) {
            open.putExtras(extras);
        }
        open.putExtra(ENTER_ANIMATION, R.anim.enter_from_bottom);
        startActivity(open);
        overridePendingTransition(R.anim.enter_from_bottom, R.anim.hold);
    }

    protected void closeFragment(String tag, int enterAnim, int exitAnim) {
        if (!TextUtils.isEmpty(pageTitle)) {
            setPageTitle(pageTitle.toString());
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment page = fragmentManager.findFragmentByTag(tag);

        if (page != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(enterAnim, exitAnim);
            fragmentTransaction.remove(page).commit();
        }
    }

    protected void setPageTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
