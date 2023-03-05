package com.jeyymsantos.ramkyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      sharedPreferences = getSharedPreferences("MyAppName", MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menurates:
                startActivity(new Intent(getApplicationContext(), rates.class));
                Toast.makeText(this, "Parking rates", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menurules:
                startActivity(new Intent(getApplicationContext(), rules.class));
                Toast.makeText(this, "Parking rules", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuprofile:
                startActivity(new Intent(getApplicationContext(), profile.class));
                return true;
            case R.id.menuexit:
                finishAffinity();
                return true;
            case R.id.menulogout:
                finish();
               return true;
        }
        return super.onOptionsItemSelected(item);
    }
}