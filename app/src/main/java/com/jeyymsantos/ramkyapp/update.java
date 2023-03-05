package com.jeyymsantos.ramkyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class update extends AppCompatActivity {
    Button updatenow, back;
    EditText  etpassword;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updatenow=findViewById(R.id.updatenow);
        back=findViewById(R.id.back);


        etpassword=findViewById(R.id.password);

        sharedPreferences = getSharedPreferences("MyAppName", MODE_PRIVATE);

        updatenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.233.106/ramkyandroid/update.php";

                if(etpassword.getText().toString().isEmpty()){
                    Toast.makeText(update.this, "Update your Password", Toast.LENGTH_SHORT).show();
                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Intent intent = new Intent(getApplicationContext(), profile.class);
                                    startActivity(intent);
                                    finish();

                                    Toast.makeText(update.this, response, Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("password", etpassword.getText().toString());
                                    editor.apply();

                                }

                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();

                        }
                    }) {
                        protected Map<String, String> getParams() {
                            Map<String, String> paramV = new HashMap<>();
                            paramV.put("id", sharedPreferences.getString("id", ""));
                            paramV.put("password",etpassword.getText().toString());


                            return paramV;
                        }
                    };
                    queue.add(stringRequest);
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), profile.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

