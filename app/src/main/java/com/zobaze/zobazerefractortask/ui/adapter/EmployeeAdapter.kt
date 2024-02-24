package com.zobaze.zobazerefractortask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zobaze.zobazerefractortask.data.model.Employee
import com.zobaze.zobazerefractortask.databinding.ListItemEmployeeBinding

class EmployeeAdapter(private var employees: List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    inner class EmployeeViewHolder(private val binding: ListItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: Employee) {
            binding.employee = employee
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ListItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.bind(employee)
    }

    override fun getItemCount() = employees.size

    fun updateData(newEmployees: List<Employee>) {
        employees = newEmployees
        notifyDataSetChanged()
    }
}
