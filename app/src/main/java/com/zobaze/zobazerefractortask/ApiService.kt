package com.zobaze.zobazerefractortask

import com.zobaze.zobazerefractortask.data.model.EmployeeResponseDto
import retrofit2.http.GET

interface ApiService {

    @GET("employees")
    suspend fun getEmployees(): EmployeeResponseDto
}