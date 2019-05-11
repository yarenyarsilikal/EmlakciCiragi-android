package com.example.emlakciciragi.UI.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.emlakciciragi.R;
import com.example.emlakciciragi.Util.PreferencesUtil;

public class SettingsActivity extends BaseActivity{
    private static final String KEY_SALE_RATE = "SALE_RATE";
    private static final String KEY_RENT_RATE = "RENT_RATE";
    private static final String KEY_FEES_RATE = "FEES_RATE";


    private EditText saleRate;
    private EditText rentRate;
    private EditText feesRate;

    private PreferencesUtil preferencesUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeResources();
        preferencesUtil = new PreferencesUtil(this);
        checkField(saleRate, KEY_SALE_RATE);
        checkField(rentRate, KEY_RENT_RATE);
        checkField(feesRate, KEY_FEES_RATE);
    }

    private void initializeResources() {
        saleRate = findViewById(R.id.tv_sale_rate);
        rentRate = findViewById(R.id.tv_rent_rate);
        feesRate = findViewById(R.id.tv_fees_rate);

        saleRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    preferencesUtil.removeData(KEY_SALE_RATE);
                } else {
                    saveNewRates();
                }
            }
        });
        rentRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    preferencesUtil.removeData(KEY_RENT_RATE);
                } else {
                    saveNewRates();
                }
            }
        });
        feesRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    preferencesUtil.removeData(KEY_FEES_RATE);
                } else {
                    saveNewRates();
                }
            }
        });
    }

    private void checkField(EditText editText, String key) {
        if (preferencesUtil.getData(key, null) != null) {
            editText.setText(preferencesUtil.getData(key, null));
        }
    }

    private void saveNewRates() {
        if ((!TextUtils.isEmpty(saleRate.getText().toString()))) {
            preferencesUtil.saveData(KEY_SALE_RATE, saleRate.getText().toString());
        }
        if (!TextUtils.isEmpty(rentRate.getText().toString())) {
            preferencesUtil.saveData(KEY_RENT_RATE, rentRate.getText().toString());
        }
        if (!TextUtils.isEmpty(feesRate.getText().toString())) {
            preferencesUtil.saveData(KEY_FEES_RATE, feesRate.getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            //saveNewRates();
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
