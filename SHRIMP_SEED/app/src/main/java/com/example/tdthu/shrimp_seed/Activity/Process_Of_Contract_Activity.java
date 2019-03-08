package com.example.tdthu.shrimp_seed.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tdthu.shrimp_seed.Fragment.Fragment_Process_1_Step_2;
import com.example.tdthu.shrimp_seed.R;

public class Process_Of_Contract_Activity extends AppCompatActivity {

    int process = -1, step = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_of_contract);
        getDataIntent();
        displaySelectedScreen(process, step);
    }
    private void getDataIntent(){
        Intent intent = getIntent();
        process = intent.getIntExtra("process",-1);
        step = intent.getIntExtra("step",-1);

    }
    private void displaySelectedScreen(int process, int step) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (process) {
            case 1:
                if(step == 2){
                    fragment = new Fragment_Process_1_Step_2();
                }

                break;

        }
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.process_content_of_contract, fragment);
            ft.commit();
        }

    }
}
