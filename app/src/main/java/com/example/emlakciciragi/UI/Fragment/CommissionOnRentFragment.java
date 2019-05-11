package com.example.emlakciciragi.UI.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.emlakciciragi.R;
import com.example.emlakciciragi.Util.PreferencesUtil;
import com.google.android.material.textfield.TextInputEditText;

public class CommissionOnRentFragment extends Fragment implements View.OnClickListener {

    private static final String KEY_RENT_RATE = "RENT_RATE";

    private TextInputEditText rentValue;
    private Button calculate;
    private Button delete;
    private TextView renterPayment;
    private TextView renterPaymentKDV;

    private double valueOfRent;
    private String tl = " TL";

    private PreferencesUtil preferencesUtil;


    public CommissionOnRentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_commission_on_rent, container, false);
        initializeResourses(v);

        calculate.setOnClickListener(this);
        delete.setOnClickListener(this);

        preferencesUtil = new PreferencesUtil(getActivity());

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rent_calculate:
                if (!TextUtils.isEmpty(rentValue.getText().toString())) {
                    valueOfRent = Double.parseDouble(rentValue.getText().toString());
                    calculateCommissionOnRent(valueOfRent);
                }
                break;
            case R.id.btn_rent_delete:
                deleteAllFields();
                break;
            default:
                break;
        }
    }

    private void initializeResourses(View v) {
        rentValue = v.findViewById(R.id.et_rent_value);
        calculate = v.findViewById(R.id.btn_rent_calculate);
        delete = v.findViewById(R.id.btn_rent_delete);

        renterPayment = v.findViewById(R.id.tv_renter_without_KDV);
        renterPaymentKDV = v.findViewById(R.id.tv_renter_include_KDV);
    }

    private void calculateCommissionOnRent(double value) {

        double valueWithoutKDV;
        String savedRate = preferencesUtil.getData(KEY_RENT_RATE, "10");
        if (savedRate == null) {
            valueWithoutKDV = (value * 12) * 10 / 100;
        } else {
            double rate = Double.parseDouble(preferencesUtil.getData(KEY_RENT_RATE, "10"));
            valueWithoutKDV = (value * 12) * rate / 100;
        }
        renterPayment.setText(String.valueOf(valueWithoutKDV + tl));

        double valueWithKDV = valueWithoutKDV + (valueWithoutKDV * 18 / 100);
        renterPaymentKDV.setText(String.valueOf(valueWithKDV + tl));
    }

    private void deleteAllFields() {
        rentValue.setText("");
        renterPayment.setText("");
        renterPaymentKDV.setText("");
    }
}
