package com.example.dundermifflinemployee

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.dundermifflinemployee.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
        val employeeData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EMPLOYEE, Employee::class.java)
        } else {
            @Suppress("DEPRECIATION")
            intent.getParcelableExtra(EMPLOYEE)
        }
        employeeData?.let { employee ->
            binding.apply {
                supportActionBar?.title = "${employee.name}'s Profile"
                tvEmployeeName.text = employee.name
                tvEmployeeRole.text = employee.role
                tvEmployeeOverview.text = employee.description
                Glide.with(this@DetailActivity)
                    .load(employee.image)
                    //.circleCrop()
                    .into(imgEmployee)
            }
        }
    }

    companion object {
        const val EMPLOYEE = "employee"
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
