package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.employeeapi.api.EmployeeAPI;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    private EditText etemployeeNo;
    private TextView tvData;
    private Button btnsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etemployeeNo=findViewById(R.id.etemployeeNo);
        tvData=findViewById(R.id.tvData);
        btnsearch=findViewById(R.id.btnsearch);

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    loadData();

                }

        });

     
    }
    private void loadData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Employee> listcall =employeeAPI.getEmployeeByID(Integer.parseInt(etemployeeNo.getText().toString()));
        
        listcall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchActivity.this,response.body().toString(), Toast.LENGTH_SHORT).show();
                String data ="";
                data +="Id :" + response.body().getId() +"\n";
                data +="Name :" + response.body().getEmployee_name() +"\n";
                data +="Age :" + response.body().getEmployee_age() +"\n";
                data +="Salary :" + response.body().getEmployee_salary() +"\n";

                tvData.setText(data);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

                Toast.makeText(SearchActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
