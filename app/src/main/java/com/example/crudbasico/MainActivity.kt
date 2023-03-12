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
        /**
         * Creas los Adapters, despues le asignas a el RecyclerView el LayoutManager que va a decidir si
         * las vistas van a de manera lineal(horizontal o vertical) o en manera de grilla(Grid)
         */
        categoriesAdapter = CategoriesAdapter(categories) { position ->
            updateCategories(position)
        }
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter



        tasksAdapter = TaskAdapter(tasksList) { position ->
            onItemSelected(position)
        }

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }


    /**
     * Esta es la funcion que se ejecuta cuando una tarea es seleccionada, cambia el estado del
     * isSelected de la tarea seleccionada y actualiza la lista de tareas
     */
    private fun onItemSelected(i: Int) {
        tasksList[i].isSelected = !tasksList[i].isSelected
        updateTasks()
    }

    /**
     * Cuando se hacen cambios en un RecyclerView se tienen que notificar los cambios. Una practica
     * recomendada es crear una funcion que actualice los datos, esta funcion hace eso, notificar los cambios
     * del RecyclerView de las Categories y el de las Tasks, ya que la actualizacion de las categories
     * trae consigo un cambio visible en la lista de tareas(desaparecen las tareas de la categoria seleccionada)
     */
    private fun updateCategories(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }


    // Muestra el dialogo, se activa cuando el FloatingActionButton es clickeado
    private fun showDialog() {
        // Se crea el Dialog y se le pasa el contexto de la actividad(this)
        val dialog = Dialog(this)
        // Se establece la vista con el archivo xml creado en la carpeta de layout
        dialog.setContentView(R.layout.dialog_add_task)

        // Referencias a los elementos del Dialog, notese que se utiliza dialog.findViewById,
        // no se porque se hace.
        val btnAddTask: Button = dialog.findViewById(R.id.btn_add_task)
        val etTaskName: EditText = dialog.findViewById(R.id.et_task_name)
        val rgCategory: RadioGroup = dialog.findViewById(R.id.rg_dialog_categories)


        btnAddTask.setOnClickListener {
            // Valida que el campo del nombre de la tarea no este vacio
            if (etTaskName.text.toString().isNotEmpty()) {
                val idSelected = rgCategory.checkedRadioButtonId
                val itemSelected: RadioButton = rgCategory.findViewById(idSelected)
                val currentCategory: TaskCategory = when (itemSelected.text) {
                    getString(R.string.personal) -> TaskCategory.Personal
                    getString(R.string.business) -> TaskCategory.Business
                    else -> TaskCategory.Other
                }
                // Si no esta vacio, añade la tarea a la lista
                tasksList.add(Task(etTaskName.text.toString(), currentCategory))
                updateTasks()
                dialog.hide()
            }


        }
        // Muestra el dialog
        dialog.show()
    }



     // Actualiza la lista de tareas de la UI.

    private fun updateTasks() {
        // Lista de las categorias seleccionadas
        val selectedCategories: List<TaskCategory> = categories.filter {
            it.isSelected
        }

        // Lista de las tareas que se van a renderizar, descartando las tareas que sean de la o las
        // categorias que esten seleccionadas
        val newTasks = tasksList.filter {
            selectedCategories.contains(it.category)
        }

        // Al adapter le cambias la lista de tareas que recibe inicialmente por la nueva lista
        // filtrada de tareas y notificas el cambio
        tasksAdapter.tasksList = newTasks
        tasksAdapter.notifyDataSetChanged()
    }

}