package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeeapi.api.EmployeeAPI;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall = employeeAPI.getAllEmployees();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                LoadDataList(response.body());

//                List<Employee> employeeList = response.body();
//                for (Employee employee : employeeList){
//                    String data = "";
//                    data += "Employee ID : " + employee.getId() + "\n";
//                    data += "Employee Name : " + employee.getEmployee_name() + "\n";
//                    data += "Employee Salary : " + employee.getEmployee_salary() + "\n";
//                    data += "Employee Age : " + employee.getEmployee_age() + "\n";
//                    data += "Employee profile : " + employee.getProfile_image() + "\n";
//                    data += "-----------------------------------------------------" + "\n";
//                    tvOutput.append(data);


                }



            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Log.d("Mero msg", "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
    private void LoadDataList(List<Employee>employeeList){
        recyclerView=findViewById(R.id.recyclerview);
        employeeAdapter=new EmployeeAdapter(employeeList);


        //using a linear layout manager
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        //set adapter to the recyclerview
        recyclerView.setAdapter(employeeAdapter);
    }
}
