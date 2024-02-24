package com.zobaze.zobazerefractortask.data.model

data class EmployeeResponseDto(
    val data: List<EmployeeDto>
)

data class EmployeeDto(
    val id: Int,
    val employee_name: String
)