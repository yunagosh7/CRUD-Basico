package com.example.crudbasico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudbasico.adapters.CategoriesAdapter
import com.example.crudbasico.adapters.TaskAdapter
import com.example.crudbasico.databinding.ActivityMainBinding
import com.example.crudbasico.model.Task
import com.example.crudbasico.model.TaskCategory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var tasksAdapter: TaskAdapter

    private val categories = listOf(
        TaskCategory.Other,
        TaskCategory.Business,
        TaskCategory.Personal
    )

    private val tasksList = mutableListOf(
        Task("Negocios", TaskCategory.Business),
        Task("Otro", TaskCategory.Other),
        Task("Personal", TaskCategory.Personal)
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var rvTasks: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        initUI()
    }

    private fun initComponents() {
        rvCategories = binding.rvCategories
        rvTasks = binding.rvTasks
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TaskAdapter(tasksList)
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }
}