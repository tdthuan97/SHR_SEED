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

public class Fragment_List_Of_Producers extends Fragment{

    View view;
    ListView listviewProducers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String[] producers = {"Producer A","Producer B","Producer C"};
        view = inflater.inflate(R.layout.fragment_list_of_producers, container, false);
        listviewProducers = view.findViewById(R.id.listviewProducers);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, producers);
        listviewProducers.setAdapter(adapter);


        return view;
    }

}
