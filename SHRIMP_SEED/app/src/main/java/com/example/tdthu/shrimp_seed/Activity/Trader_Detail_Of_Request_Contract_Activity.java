package com.example.tdthu.shrimp_seed.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.widget.EditText;

import com.example.tdthu.shrimp_seed.Model.NumberTextWatcher;
import com.example.tdthu.shrimp_seed.R;

public class Trader_Detail_Of_Request_Contract_Activity extends AppCompatActivity {

    Toolbar toolbar;
    EditText edtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader__detail__of__request__contract_);
        actionBar();
        init();

    }
    private void init(){
        edtPrice = findViewById(R.id.edittext_trader_request_price);
        edtPrice.addTextChangedListener(new NumberTextWatcher(edtPrice));

    }


    private void actionBar(){
        toolbar = findViewById(R.id.toolbar_trader_req_contract);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
