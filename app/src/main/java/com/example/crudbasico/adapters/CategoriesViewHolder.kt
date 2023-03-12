package com.example.crudbasico.adapters

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.crudbasico.R
import com.example.crudbasico.model.TaskCategory

/**
 * El [ViewHolder] es el contenedor de vistas, recibe como parametro la vista [view] creada en el Adapter,
 * que es, en este caso, el archivo de item_category.xml. En esta clase se crean las referencias
 * a los elementos del layout y se les asigna un valor para que se rendericen.
 */
class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Referencias a los elementos del layout
    private val tvCategoryName: TextView = view.findViewById(R.id.tv_category_name)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.view_container)

    /**
     * La funcion que muestra los datos en pantalla, recibe como parametro el dato en si y, en este caso,
     * una funcion que recibe como parametro un [Int] y devuelve un [Unit]
     */
    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        // Color del fondo de la CardView, varia dependiendo si es seleccionado o no
        val color = if(taskCategory.isSelected) {
            R.color.background_card
        } else{
            R.color.background_disabled
        }
        // Se le asigna a la CardView el color antes creado
        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        /**
         * itemView creo que es una referencia al item en si que esta se estaria renderizando. Cuando se
         * clickea este item, se llama a la funcion [onItemSelected]
         */
        itemView.setOnClickListener {
            onItemSelected(layoutPosition)
        }

        /**
         * Esto es la asignacion de cada item del RecyclerView, que dependiendo de ciertos parametros
         * mostrara una u otra cosa en pantalla
         */
        when(taskCategory){
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context ,R.color.business_category)
                )
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context ,R.color.other_category)
                )
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context ,R.color.personal_category)
                )
            }
        }
    }
}