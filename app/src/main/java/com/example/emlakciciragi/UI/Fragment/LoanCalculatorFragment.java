package com.example.emlakciciragi.UI.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emlakciciragi.R;
import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.Fragment;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Yaren YARŞILIKAL on 10.05.2019.
 */
public class LoanCalculatorFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText installmentNumber;
    private TextInputEditText creditAmount;
    private TextInputEditText rateOfInterest;

    private Button calculate;
    private Button delete;
    private TextView installmentAmount;
    private TextView totalPaybackAmount;

    private double numberOfInstallment;
    private double amountOfCredit;
    private double rateOfInterestValue;

    private String tl = " TL";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loan_calculator, container, false);
        initializeResourses(v);

        calculate.setOnClickListener(this);
        delete.setOnClickListener(this);
        // Inflate the layout for this fragment
        return v;
    }

    private void initializeResourses(View v) {
        installmentNumber = v.findViewById(R.id.et_installment_number);
        creditAmount = v.findViewById(R.id.et_credit_amount);
        rateOfInterest = v.findViewById(R.id.et_rate_of_interest);
        calculate = v.findViewById(R.id.btn_credit_calculate);
        delete = v.findViewById(R.id.btn_credit_delete);

        installmentAmount = v.findViewById(R.id.tv_installment_value);
        totalPaybackAmount = v.findViewById(R.id.tv_total_credit_payback_amount);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_credit_calculate:
                if (!(TextUtils.isEmpty(installmentNumber.getText().toString()) &&
                        TextUtils.isEmpty(creditAmount.getText().toString()) &&
                        TextUtils.isEmpty(rateOfInterest.getText().toString()))) {
                    numberOfInstallment = Double.parseDouble(installmentNumber.getText().toString());
                    amountOfCredit = Double.parseDouble(creditAmount.getText().toString());
                    rateOfInterestValue = Double.parseDouble(rateOfInterest.getText().toString());
                    calculateCommissionOnRent(numberOfInstallment, amountOfCredit, rateOfInterestValue);
                } else {
                    Toast.makeText(getActivity(), getString(R.string.message_fill_all_fields), LENGTH_LONG).show();
                }
                break;
            case R.id.btn_credit_delete:
                deleteAllFields();
                break;
            default:
                break;
        }
    }

    private void calculateCommissionOnRent(double installment, double credit, double rate) {
        double amountOfInstallment = credit *
                ((rate * Math.pow((1 + rate), installment)) / (Math.pow((1 + rate), installment) - 1));
        installmentAmount.setText(String.valueOf(amountOfInstallment + tl));

        double totalPayback = amountOfInstallment * installment;
        totalPaybackAmount.setText(String.valueOf(totalPayback + tl));
    }

    private void deleteAllFields() {
        installmentNumber.setText("");
        creditAmount.setText("");
        rateOfInterest.setText("");
        installmentAmount.setText("");
        totalPaybackAmount.setText("");
    }
}
