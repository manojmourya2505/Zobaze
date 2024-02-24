package com.zobaze.zobazerefractortask.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zobaze.zobazerefractortask.data.model.Employee
import com.zobaze.zobazerefractortask.data.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel : ViewModel() {

    private val employeeRepository = EmployeeRepository()

    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>>get() = _employees

    fun getEmployees() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val employeesList = employeeRepository.getEmployees()
                val filteredEmployees = employeesList.filter { employee ->
                    val id = employee.id
                    id % 3 == 0 || id % 7 == 0 || id % 4 == 0
                }
                _employees.postValue(filteredEmployees)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}