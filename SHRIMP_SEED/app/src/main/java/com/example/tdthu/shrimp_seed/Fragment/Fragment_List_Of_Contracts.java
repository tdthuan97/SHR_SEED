package com.example.tdthu.shrimp_seed.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tdthu.shrimp_seed.Activity.Process_Of_Contract_Activity;
import com.example.tdthu.shrimp_seed.R;


public class Fragment_List_Of_Contracts extends Fragment{

    View view;
    ListView listviewContracts;
    String[] con = {"Contract A","Contract B","Contract C"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_of_contracts, container, false);
        listviewContracts = view.findViewById(R.id.listviewContracts);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, con);
        listviewContracts.setAdapter(adapter);

        final int process = 1, step = 2;
        listviewContracts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Process_Of_Contract_Activity.class);
                intent.putExtra("contract",con[i]);
                intent.putExtra("process",process);
                intent.putExtra("step",step);
                startActivity(intent);
            }
        });
        return view;
    }
}
