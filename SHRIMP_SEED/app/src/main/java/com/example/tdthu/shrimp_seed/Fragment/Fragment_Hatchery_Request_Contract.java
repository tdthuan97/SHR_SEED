package com.example.tdthu.shrimp_seed.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.tdthu.shrimp_seed.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Hatchery_Request_Contract extends Fragment {

    View view;

    Spinner spinner;
    DatePicker datePicker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hatchery_request_contract, container, false);
        init();
        return view;
    }
    private void init(){
        spinner = view.findViewById(R.id.spinner_trader_in_req_hatchery);
        List<String> traders = new ArrayList<String>();
        traders.add("Trader A");
        traders.add("Trader B");
        traders.add("Trader C");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, traders);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        datePicker = view.findViewById(R.id.datepicker_hatchery_expected);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

    }
}
