package com.example.employeeapi.api;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    @GET("employee/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empId);

    @PUT("update/{empID}")
    Call<Void> updateEmployee(@Path("empID") int empId,@Body EmployeeCUD emp);

    @DELETE ("delete/{empID}")
    Call<Void> deleteEmployee(@Path("empID") int empId);
    @POST("create")
    Call<Void> registerEmployee (@Body EmployeeCUD employeeCUD);
}
