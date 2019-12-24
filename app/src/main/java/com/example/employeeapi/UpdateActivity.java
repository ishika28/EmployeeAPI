package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employeeapi.api.EmployeeAPI;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.model.EmployeeCUD;
import com.example.employeeapi.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {
    private Button btnserachemp, btnupdate,btndelete;
    private EditText etemployeeNumber;
    private EditText etempname, etempsalary, etempage;
    Retrofit retrofit;
    EmployeeAPI employeeAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btnserachemp=findViewById(R.id.btnsearchemp);
        btndelete=findViewById(R.id.btndeleteemp);
        btnupdate=findViewById(R.id.btnupdate);
        etempname=findViewById(R.id.etempname);
        etempage=findViewById(R.id.etempage);
        etempsalary=findViewById(R.id.etempsalary);
        etemployeeNumber=findViewById(R.id.etemployeeNumber);

        btnserachemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });

    }
    private void createInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        employeeAPI= retrofit.create(EmployeeAPI.class);
    }
    private void loadData(){
        createInstance();
        Call<Employee> employeeCall = employeeAPI.getEmployeeByID(Integer.parseInt(etemployeeNumber.getText().toString()));
        employeeCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                etempname.setText(response.body().getEmployee_name());
                etempsalary.setText(Float.toString(response.body().getEmployee_salary()));
                etempage.setText(Integer.toString(response.body().getEmployee_age()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void updateEmployee(){
createInstance();
        EmployeeCUD employee = new EmployeeCUD(
                etempname.getText().toString(),
                Float.parseFloat(etempsalary.getText().toString()),
                Integer.parseInt(etempage.getText().toString())
        );
        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etemployeeNumber.getText().toString()),employee);
         voidCall.enqueue(new Callback<Void>() {
             @Override
             public void onResponse(Call<Void> call, Response<Void> response) {
                 Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onFailure(Call<Void> call, Throwable t) {
                 Toast.makeText(UpdateActivity.this, "Error", Toast.LENGTH_SHORT).show();
             }
         });
    }

    private void deleteEmployee(){
        createInstance();
        Call<Void> callvoid =employeeAPI.deleteEmployee(Integer.parseInt(etemployeeNumber.getText().toString()));

        callvoid.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
