package com.jeyymsantos.ramkyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {
    TextView tvsignin, textViewError;
    TextInputEditText itfname, itlname, itemail,itpassword, itcontactnum, itlicensenum;
    SharedPreferences sharedPreferences;
    Button register;
    ProgressBar progressBar;
    String fname, lname, email, password, contactnum, licensenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        tvsignin = findViewById(R.id.signin);
        itfname=findViewById(R.id.fname);
        itlname=findViewById(R.id.lname);
        itemail=findViewById(R.id.email);
        itpassword=findViewById(R.id.password);
        itcontactnum=findViewById(R.id.contactnum);
        itlicensenum=findViewById(R.id.licensenum);
        textViewError=findViewById(R.id.error);
        register=findViewById(R.id.register);
        progressBar = findViewById(R.id.loading);
        sharedPreferences = getSharedPreferences("MyAppName", MODE_PRIVATE);


        if (sharedPreferences.getString("logged", "false").equals("true")){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewError.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                fname = itfname.getText().toString();
                lname = itlname.getText().toString();
                email = itemail.getText().toString();
                password = itpassword.getText().toString();
                contactnum = itcontactnum.getText().toString();
                licensenum = itlicensenum.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.233.106/ramkyandroid/register.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                if(response.equals("success")){
                                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    textViewError.setText(response);
                                    textViewError.setVisibility(View.VISIBLE);
                                }
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        textViewError.setText(error.getLocalizedMessage());
                        textViewError.setVisibility(View.VISIBLE);

                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("fname", fname);
                        paramV.put("lname", lname);
                        paramV.put("email", email);
                        paramV.put("password", password);
                        paramV.put("contactnum", contactnum);
                        paramV.put("licensenum", licensenum);

                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}