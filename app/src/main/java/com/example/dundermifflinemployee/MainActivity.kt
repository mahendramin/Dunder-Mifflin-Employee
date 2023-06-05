package com.example.dundermifflinemployee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dundermifflinemployee.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listEmployee = ArrayList<Employee>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listEmployee.addAll(getListEmployee())
        setRecylerView()
    }
    private fun getListEmployee() : ArrayList<Employee> {
        val listEmployee = ArrayList<Employee>()
        val dataName = resources.getStringArray(R.array.data_name)
        val dataRole = resources.getStringArray(R.array.data_role)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPicture = resources.obtainTypedArray(R.array.data_photo)
        for (i in dataName.indices) {
            val employee = Employee(dataName[i], dataRole[i], dataPicture.getResourceId(i, -1), dataDescription[i])
            listEmployee.add(employee)
        }
        dataPicture.recycle()
        return listEmployee
    }

    private fun setRecylerView() {
        binding.rvEmployee.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            val employeeAdapter = EmployeeAdapter(listEmployee)
            adapter = employeeAdapter
        }
    }
}
