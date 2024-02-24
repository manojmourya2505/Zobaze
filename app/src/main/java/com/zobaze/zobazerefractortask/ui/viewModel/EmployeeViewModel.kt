package com.zobaze.zobazerefractortask.ui.viewModel

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import com.zobaze.zobazerefractortask.data.model.Employee
import com.zobaze.zobazerefractortask.data.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class EmployeeViewModel : ViewModel() {

    private val employeeRepository = EmployeeRepository()

    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>>get() = _employees

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getEmployees() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val employeesList = employeeRepository.getEmployees()

                val employeesMod3 = mutableListOf<Employee>()
                val employeesMod7 = mutableListOf<Employee>()
                val employeesMod4 = mutableListOf<Employee>()

                for (employee in employeesList) {
                    val id = employee.id
                    if (id % 3 == 0) {
                        employeesMod3.add(employee)
                    }
                    if (id % 7 == 0) {
                        employeesMod7.add(employee)
                    }
                    if (id % 4 == 0) {
                        employeesMod4.add(employee)
                    }
                }

                val concatenatedList = mutableListOf<Employee>()
                concatenatedList.addAll(employeesMod3)
                concatenatedList.addAll(employeesMod7)
                concatenatedList.addAll(employeesMod4)

                _employees.postValue(concatenatedList)
            } catch (e: NetworkErrorException) {
                _error.postValue("Network error occurred: ${e.message}")
                e.printStackTrace()
            } catch (e: HttpException) {
                _error.postValue("${e.message} : Something went wrong")
                e.printStackTrace()
            } catch (e: IOException) {
                _error.postValue("Please check your network connection")
                e.printStackTrace()
            } catch (e: Exception) {
                _error.postValue("An unexpected error occurred: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}