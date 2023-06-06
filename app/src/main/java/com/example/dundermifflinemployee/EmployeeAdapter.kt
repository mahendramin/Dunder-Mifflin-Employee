package com.example.dundermifflinemployee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dundermifflinemployee.databinding.ItemRowEmployeeBinding

class EmployeeAdapter(
    private val listEmployee: ArrayList<Employee>,
    private val onClick: (Employee) -> Unit
) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {
    inner class EmployeeViewHolder(var binding: ItemRowEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding =
            ItemRowEmployeeBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        return EmployeeViewHolder(binding)
    }

    override fun getItemCount() = listEmployee.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val name = listEmployee[position].name
        val role = listEmployee[position].role
        val image = listEmployee[position].image
        holder.binding.apply {
            tvEmployeeName.text = name
            tvEmployeeRole.text = role
            Glide.with(holder.itemView.context)
                .load(image)
                .into(imgEmployee)
        }
        holder.itemView.setOnClickListener {
            onClick(listEmployee[position])
        }
    }
}
