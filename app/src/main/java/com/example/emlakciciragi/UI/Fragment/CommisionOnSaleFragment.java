package com.example.emlakciciragi.UI.Fragment;


import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emlakciciragi.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CommisionOnSaleFragment extends Fragment{

    private TextInputEditText textInputEditText;
    private TextInputLayout textInputLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_commission_on_sale, container, false);

        textInputEditText = v.findViewById(R.id.et_first);
        textInputLayout = v.findViewById(R.id.il_satis_tutari);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onResume() {
        textInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    textInputLayout.setHint("Value");
                }else{
                    textInputLayout.setHint("");
                    textInputEditText.setHint("Please enter a value");
                }
            }
        });
        super.onResume();
    }
}
