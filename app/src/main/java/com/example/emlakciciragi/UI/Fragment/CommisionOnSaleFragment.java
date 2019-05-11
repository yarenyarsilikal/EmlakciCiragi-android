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

public class CommisionOnSaleFragment extends Fragment implements View.OnClickListener {

    private static final String KEY_SALE_RATE = "SALE_RATE";

    private TextInputEditText saleValue;
    private Button calculate;
    private Button delete;
    private TextView customerPayment;
    private TextView customerPaymentKDV;
    private TextView sellerPayment;
    private TextView sellerPaymentKDV;
    private TextView totalPayment;
    private TextView totalPaymentKDV;

    private double valueOfSale;
    private String tl = " TL";

    private PreferencesUtil preferencesUtil;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_commission_on_sale, container, false);

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
            case R.id.btn_sale_calculate:
                if (!TextUtils.isEmpty(saleValue.getText().toString())) {
                    valueOfSale = Double.parseDouble(saleValue.getText().toString());
                    calculateCommissionOnSale(valueOfSale);
                }
                break;
            case R.id.btn_sale_delete:
                deleteAllFields();
                break;
            default:
                break;
        }
    }

    private void initializeResourses(View v) {
        saleValue = v.findViewById(R.id.et_sale_value);
        calculate = v.findViewById(R.id.btn_sale_calculate);
        delete = v.findViewById(R.id.btn_sale_delete);

        customerPayment = v.findViewById(R.id.tv_cp_without_KDV);
        customerPaymentKDV = v.findViewById(R.id.tv_cp_include_KDV);
        sellerPayment = v.findViewById(R.id.tv_sp_without_KDV);
        sellerPaymentKDV = v.findViewById(R.id.tv_sp_include_KDV);
        totalPayment = v.findViewById(R.id.tv_tp_without_KDV);
        totalPaymentKDV = v.findViewById(R.id.tv_tp_include_KDV);
    }

    private void calculateCommissionOnSale(double value) {
        double valueWithoutKDV;
        String savedRate = preferencesUtil.getData(KEY_SALE_RATE, "2");
        if (savedRate == null) {
            valueWithoutKDV = (value * 2 / 100);
        } else {
            double rate = Double.parseDouble(preferencesUtil.getData(KEY_SALE_RATE, "2"));
            valueWithoutKDV = value * rate / 100;
        }

        customerPayment.setText(String.valueOf(valueWithoutKDV + tl));
        sellerPayment.setText(String.valueOf(valueWithoutKDV + tl));

        double valueWithKDV = valueWithoutKDV + (valueWithoutKDV * 18 / 100);
        customerPaymentKDV.setText(String.valueOf(valueWithKDV + tl));
        sellerPaymentKDV.setText(String.valueOf(valueWithKDV + tl));

        double totalPaymentValue = 2 * valueWithoutKDV;
        totalPayment.setText(String.valueOf(totalPaymentValue + tl));

        double totalPaymentValueKDV = 2 * valueWithKDV;
        totalPaymentKDV.setText(String.valueOf(totalPaymentValueKDV + tl));
    }

    private void deleteAllFields() {
        saleValue.setText("");
        customerPayment.setText("");
        customerPaymentKDV.setText("");
        sellerPayment.setText("");
        sellerPaymentKDV.setText("");
        totalPayment.setText("");
        totalPaymentKDV.setText("");

    }
}
