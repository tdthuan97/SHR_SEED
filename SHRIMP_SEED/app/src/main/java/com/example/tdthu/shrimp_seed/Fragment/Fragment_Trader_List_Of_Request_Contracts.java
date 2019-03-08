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

import com.example.tdthu.shrimp_seed.Activity.Trader_Detail_Of_Request_Contract_Activity;
import com.example.tdthu.shrimp_seed.R;

public class Fragment_Trader_List_Of_Request_Contracts extends Fragment {

    View view;
    ListView listviewReq;
    String[] req = {"Request A","Request B","Request C"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trader_list_of_request_contracts, container, false);
        listviewReq = view.findViewById(R.id.listview_trader_list_request_contract);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, req);
        listviewReq.setAdapter(adapter);

        listviewReq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Trader_Detail_Of_Request_Contract_Activity.class);
                startActivity(intent);

            }
        });

        return view;
    }
}
