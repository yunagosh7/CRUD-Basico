package com.example.crudbasico.adapters

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudbasico.R
import com.example.crudbasico.model.Task

class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val tvTask = view.findViewById<TextView>(R.id.tv_task)
    private val cbTask = view.findViewById<CheckBox>(R.id.cb_task)

    fun render(task: Task) {
        tvTask.text = task.name

    }
}