package com.zobaze.zobazerefractortask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zobaze.zobazerefractortask.databinding.ActivityMainBinding
import com.zobaze.zobazerefractortask.ui.adapter.EmployeeAdapter
import com.zobaze.zobazerefractortask.ui.viewModel.EmployeeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: EmployeeViewModel
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        val recyclerView = binding.rvEmployeeList
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EmployeeAdapter(emptyList())
        recyclerView.adapter = adapter

        observeViewModel()
        viewModel.getEmployees()
    }

    private fun observeViewModel() {
        viewModel.employees.observe(this) { employees ->
            adapter.updateData(employees)
        }

        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}

