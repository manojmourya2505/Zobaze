package com.zobaze.zobazerefractortask.data.repository

import com.zobaze.zobazerefractortask.ApiService
import com.zobaze.zobazerefractortask.data.model.Employee
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EmployeeRepository {

    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dummy.restapiexample.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    suspend fun getEmployees(): List<Employee> {
        return apiService.getEmployees().data.map { employeeDto ->
            Employee(employeeDto.id, employeeDto.employee_name)
        }
    }
}