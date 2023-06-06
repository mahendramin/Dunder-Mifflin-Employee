package com.example.dundermifflinemployee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dundermifflinemployee.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listEmployee = ArrayList<Employee>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        listEmployee.addAll(getListEmployee())
        setRecyclerView()
    }

    private fun getListEmployee(): ArrayList<Employee> {
        val listEmployee = ArrayList<Employee>()
        val dataName = resources.getStringArray(R.array.data_name)
        val dataRole = resources.getStringArray(R.array.data_role)
        val dataActorName = resources.getStringArray(R.array.data_actor_name)
        val dataOverview = resources.getStringArray(R.array.data_overview)
        val dataPicture = resources.obtainTypedArray(R.array.data_photo)
        for (i in dataName.indices) {
            val employee = Employee(
                dataName[i],
                dataRole[i],
                dataActorName[i],
                dataPicture.getResourceId(i, -1),
                dataOverview[i]
            )
            listEmployee.add(employee)
        }
        dataPicture.recycle()
        return listEmployee
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutIntent = Intent(this, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
        return true
    }

    private fun setRecyclerView() {
        binding.rvEmployee.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            val employeeAdapter = EmployeeAdapter(listEmployee, onClick = {
                val moveToDetailActivityPage = Intent(this@MainActivity, DetailActivity::class.java)
                moveToDetailActivityPage.putExtra(DetailActivity.EMPLOYEE, it)
                startActivity(moveToDetailActivityPage)
            })
            adapter = employeeAdapter
        }
    }
}
