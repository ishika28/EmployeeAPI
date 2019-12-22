package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {
    private Button btnshowallemployee;
    private Button btnregisteremployee;
    private Button btnsearchemployee;
    private Button btnupdateemployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnshowallemployee=findViewById(R.id.btnshowallemployee);
        btnregisteremployee=findViewById(R.id.btnregisteremployee);
        btnsearchemployee=findViewById(R.id.btnsearchemployee);
        btnupdateemployee=findViewById(R.id.btnupdateemployee);

        btnshowallemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
                startActivity(intent);


            }
        });
        btnregisteremployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,RegisterEmployeeActivity.class);
                startActivity(intent);
            }
        });

        btnsearchemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        btnupdateemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,UpdateActivity.class);
                startActivity(intent);
            }
        });
    }

}
