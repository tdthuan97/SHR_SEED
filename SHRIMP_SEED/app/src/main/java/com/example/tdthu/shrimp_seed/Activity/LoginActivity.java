package com.example.tdthu.shrimp_seed.Activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tdthu.shrimp_seed.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    CheckBox cbRemember;
    TextView txtForgot;
    Button btnLogin;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        sharedPreferences = getSharedPreferences("datauser",MODE_PRIVATE);
        getSharedPreferences();
        event();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final String[]user={"hatchery","trader"};
                final String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //example

                if(Arrays.asList(user).contains(username) && password.equals("123") ) {
                    if(cbRemember.isChecked()) {
                        editor.putString("username", edtUsername.getText().toString());
                        editor.putString("password", edtPassword.getText().toString());
                        editor.putBoolean("checked", true);
                        editor.commit();
                    }else{
                        editor.remove("username");
                        editor.remove("password");
                        editor.remove("checked");
                        editor.commit();
                    }
                    Intent intent = new Intent(LoginActivity.this, Home_Activity.class);
                    intent.putExtra("user",username);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




    private void event(){

    }
    private  void getSharedPreferences(){
        edtUsername.setText(sharedPreferences.getString("username",""));
        edtPassword.setText(sharedPreferences.getString("password",""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked",false));
    }
    private void init(){
        edtUsername = findViewById(R.id.edittextUser);
        edtPassword = findViewById(R.id.edittextPassword);
        cbRemember = findViewById(R.id.checkboxRememberAccount);
        txtForgot = findViewById(R.id.textviewForgotpassword);
        btnLogin = findViewById(R.id.buttonLogin);
    }
}
