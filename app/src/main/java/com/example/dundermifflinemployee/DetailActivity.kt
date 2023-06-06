package com.example.dundermifflinemployee

import android.content.Intent
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
                supportActionBar?.title = getString(R.string.title_format_employee_name, employee.name)
                tvEmployeeName.text = employee.name
                tvEmployeeRole.text = getString(R.string.label_format_employee_role, employee.role)
                tvActorName.text = getString(R.string.label_format_actor_name, employee.actor)
                tvEmployeeOverview.text = employee.overview
                Glide.with(this@DetailActivity)
                    .load(employee.image)
                    .into(imgEmployee)
                actionShare.setOnClickListener {
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT,
                            getString(R.string.label_format_share_text_content, employee.name, employee.role, employee.actor, employee.overview)
                        )
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(shareIntent)
                }
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
