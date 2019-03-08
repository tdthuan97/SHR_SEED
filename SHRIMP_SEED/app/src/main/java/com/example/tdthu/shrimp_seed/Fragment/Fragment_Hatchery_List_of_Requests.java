package com.example.tdthu.shrimp_seed.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tdthu.shrimp_seed.Activity.Hatchery_Create_New_Request_Activity;
import com.example.tdthu.shrimp_seed.R;

public class Fragment_Hatchery_List_of_Requests extends Fragment {

    View view;

    ListView listview;
    FloatingActionButton fabCreateReq;
    String[] req = {"Request A","Request B","Request C"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hatchery_list_of_requests, container, false);
        init();
        event();
        return view;
    }
    private void init(){
        listview = view.findViewById(R.id.listview_hatchery_list_of_reqs);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, req);
        listview.setAdapter(adapter);

        fabCreateReq = view.findViewById(R.id.floatingbutton_hatchery_create_request);
    }
    private void event(){
        fabCreateReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Hatchery_Create_New_Request_Activity.class);
                startActivity(intent);
            }
        });
    }
}
