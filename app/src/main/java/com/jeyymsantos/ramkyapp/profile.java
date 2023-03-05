package com.jeyymsantos.ramkyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class profile extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView tvfname, tvlname, tvemail, tvpassword,tvcontact, tvlicense, tvplate, tvvtype, textViewFetchResult;
    Button btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvfname = findViewById(R.id.fname);
        tvlname = findViewById(R.id.lname);
        tvemail = findViewById(R.id.email);
        tvpassword = findViewById(R.id.password);
        tvcontact = findViewById(R.id.contactnum);
        tvlicense = findViewById(R.id.licensenum);


        textViewFetchResult= findViewById(R.id.fetchResult);

         btnupdate= findViewById(R.id.update);


        sharedPreferences = getSharedPreferences("MyAppName", MODE_PRIVATE);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.233.106/ramkyandroid/profile.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        tvfname.setText(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String fname = jsonObject.getString("fname");
                            String lname = jsonObject.getString("lname");
                            String email = jsonObject.getString("email");
                            String password = jsonObject.getString("password");
                            String contactnum = jsonObject.getString("contactnum");
                            String licensenum = jsonObject.getString("licensenum");


                            tvfname.setText(fname);
                            tvlname.setText(lname);
                            tvemail.setText(email);
                            tvpassword.setText(password);
                            tvcontact.setText(contactnum);
                            tvlicense.setText(licensenum);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            tvfname.setText(error.getLocalizedMessage());

        }
    }) {
        protected Map<String, String> getParams() {
            Map<String, String> paramV = new HashMap<>();
            paramV.put("id", sharedPreferences.getString("id", ""));


            return paramV;
        }
    };
        queue.add(stringRequest);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), update.class));
            }
        });
}

}