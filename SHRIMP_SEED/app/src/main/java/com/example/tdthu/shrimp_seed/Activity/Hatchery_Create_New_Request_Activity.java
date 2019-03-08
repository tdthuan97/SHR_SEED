package com.example.tdthu.shrimp_seed.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.tdthu.shrimp_seed.R;

import java.util.ArrayList;
import java.util.List;

public class Hatchery_Create_New_Request_Activity extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatchery__create__new__request_);
        actionBar();
        init();
    }
    private void actionBar(){
        toolbar = findViewById(R.id.toolbar_hatchery_create_req);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void init(){
        spinner = findViewById(R.id.spinner_trader_in_req_hatchery);
        List<String> traders = new ArrayList<String>();
        traders.add("Trader A");
        traders.add("Trader B");
        traders.add("Trader C");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, traders);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        datePicker = findViewById(R.id.datepicker_hatchery_expected);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

    }
}
