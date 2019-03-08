package com.example.tdthu.shrimp_seed.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tdthu.shrimp_seed.R;

public class Fragment_List_Of_Traders extends Fragment{

    View view;
    ListView listviewTraders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String[] traders = {"Trader A","Trader B","Trader C"};
        view = inflater.inflate(R.layout.fragment_list_of_traders, container, false);
        listviewTraders = view.findViewById(R.id.listviewTraders);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, traders);
        listviewTraders.setAdapter(adapter);
        return view;
    }
}
