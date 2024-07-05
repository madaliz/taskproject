package com.example.taskproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskproject.ui.fragment.FoodFragment
import com.example.taskproject.ui.fragment.PersonsFragment
import com.example.taskproject.R
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var taskTabLayout = findViewById<TabLayout>(R.id.task_tabLayout)
        val fragments = listOf(FoodFragment(), PersonsFragment())

        val fragmentManager = supportFragmentManager

        taskTabLayout.addTab(taskTabLayout.newTab().setText( getString(R.string.food_title).uppercase()))
        taskTabLayout.addTab(taskTabLayout.newTab().setText( getString(R.string.person_title).uppercase()))

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FoodFragment())
                .commit()
        }

        taskTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment = when (tab.position) {
                    0 -> FoodFragment()
                    1 -> PersonsFragment()
                    else -> FoodFragment()
                }
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })


    }

}