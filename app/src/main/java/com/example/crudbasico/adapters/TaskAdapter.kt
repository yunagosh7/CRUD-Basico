package com.example.crudbasico.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.crudbasico.model.Task
import com.example.crudbasico.R

class TaskAdapter(private val tasksList: List<Task>,
                  private val onTaskSelected: (Int) -> Unit) : RecyclerView.Adapter<TaskViewHolder>(){
    override fun getItemCount() = tasksList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(tasksList[position])
        holder.itemView.setOnClickListener {
            onTaskSelected(position)
        }
    }

}