package com.example.crudbasico.adapters

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.crudbasico.R
import com.example.crudbasico.model.Task
import com.example.crudbasico.model.TaskCategory

class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask = view.findViewById<TextView>(R.id.tv_task)
    private val cbTask = view.findViewById<CheckBox>(R.id.cb_task)

    fun render(task: Task) {
        if (task.isSelected) {
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }


        tvTask.text = task.name

        val color = when (task.category) {
            TaskCategory.Business -> R.color.business_category
            TaskCategory.Personal -> R.color.personal_category
            else -> R.color.other_category
        }

        cbTask.isChecked = task.isSelected
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)
        )

    }
}