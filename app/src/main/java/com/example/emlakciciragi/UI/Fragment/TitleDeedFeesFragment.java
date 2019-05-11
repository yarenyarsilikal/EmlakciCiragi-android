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


public class TitleDeedFeesFragment extends Fragment implements View.OnClickListener {

    private static final String KEY_FEES_RATE = "FEES_RATE";

    private TextInputEditText currentValue;
    private Button calculate;
    private Button delete;
    private TextView customerFees;
    private TextView sellerFees;
    private TextView totalFees;

    private double valueOfFees;
    private String tl = " TL";

    private PreferencesUtil preferencesUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_title_deed_fees, container, false);
        initializeResourses(v);

        calculate.setOnClickListener(this);
        delete.setOnClickListener(this);

        preferencesUtil = new PreferencesUtil(getActivity());

        // Inflate the layout for this fragment
        return v;
    }

    private void initializeResourses(View v) {
        currentValue = v.findViewById(R.id.et_current_price);
        calculate = v.findViewById(R.id.btn_fees_calculate);
        delete = v.findViewById(R.id.btn_fees_delete);

        customerFees = v.findViewById(R.id.tv_customer_fees_amount);
        sellerFees = v.findViewById(R.id.tv_seller_fees_amount);
        totalFees = v.findViewById(R.id.tv_tf_amount);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fees_calculate:
                if (!TextUtils.isEmpty(currentValue.getText().toString())) {
                    valueOfFees = Double.parseDouble(currentValue.getText().toString());
                    calculateTitleDeedFees(valueOfFees);
                }
                break;
            case R.id.btn_fees_delete:
                deleteAllFields();
                break;
            default:
                break;
        }

    }

    private void calculateTitleDeedFees(double value) {

        double valueOfFee;
        String savedRate = preferencesUtil.getData(KEY_FEES_RATE, "4");
        if (savedRate == null) {
            valueOfFee = (value * 2 / 100);
        } else {
            double rate = Double.parseDouble(preferencesUtil.getData(KEY_FEES_RATE, "4")) / 2;
            valueOfFee = (value * rate / 100);
        }
        customerFees.setText(String.valueOf(valueOfFee + tl));
        sellerFees.setText(String.valueOf(valueOfFee + tl));

        double totalFee = 2 * valueOfFee;
        totalFees.setText(String.valueOf(totalFee + tl));
    }

    private void deleteAllFields() {
        currentValue.setText("");
        customerFees.setText("");
        sellerFees.setText("");
        totalFees.setText("");
    }
}
