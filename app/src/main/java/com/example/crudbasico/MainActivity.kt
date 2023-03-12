package com.example.crudbasico

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudbasico.adapters.CategoriesAdapter
import com.example.crudbasico.adapters.TaskAdapter
import com.example.crudbasico.databinding.ActivityMainBinding
import com.example.crudbasico.model.Task
import com.example.crudbasico.model.TaskCategory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    // Cosas de logica de la UI
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var tasksAdapter: TaskAdapter


    // Datos para mostrar en la UI
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


    // Elementos de la UI
    private lateinit var rvCategories: RecyclerView
    private lateinit var rvTasks: RecyclerView
    private lateinit var fabAddTask: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        initUI()
        initListeners()
    }

    private fun initComponents() {
        rvCategories = binding.rvCategories
        rvTasks = binding.rvTasks
        fabAddTask = binding.fabAddTask
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TaskAdapter(tasksList)
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }


    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_task)

        val btnAddTask: Button = dialog.findViewById(R.id.btn_add_task)
        val etTaskName: EditText = dialog.findViewById(R.id.et_task_name)
        val rgCategory: RadioGroup = dialog.findViewById(R.id.rg_dialog_categories)

        btnAddTask.setOnClickListener {

            if(etTaskName.text.toString().isNotEmpty()) {

                val idSelected = rgCategory.checkedRadioButtonId
                val itemSelected: RadioButton = rgCategory.findViewById(idSelected)
                val currentCategory: TaskCategory = when(itemSelected.text) {
                    getString(R.string.personal) -> TaskCategory.Personal
                    getString(R.string.business) -> TaskCategory.Business
                    else -> TaskCategory.Other
                }

                tasksList.add(Task(etTaskName.text.toString(), currentCategory))
                updateTasks()
                dialog.hide()
            }


        }


        dialog.show()
    }

    private fun updateTasks() {
        tasksAdapter.notifyDataSetChanged()
    }

}