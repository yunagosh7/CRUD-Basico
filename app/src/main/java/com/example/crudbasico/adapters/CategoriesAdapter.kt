package com.example.crudbasico.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudbasico.R
import com.example.crudbasico.model.TaskCategory

/**
 * El Adapter recibe dos parametros, la lista de categorias que va a mostrar, [categories], y la funcion
 * que se va a ejecutar cuando se selecciona un elemento, [onItemSelected]
 */
class CategoriesAdapter(
    private val categories: List<TaskCategory>,
    private val onItemSelected: (Int) -> Unit
) :
    RecyclerView.Adapter<CategoriesViewHolder>() {

    /**
     * Esta funcion se ejecuta cuando se crea el [ViewHolder], creas una vista del layout creado en XML, en
     * este caso es el archivo item_category.xml. Esta vista va a contener todos los elementos que necesite para
     * mostar en pantalla la información recibida de la lista de datos que se envio como parametro.
     * No se porque devuelve el ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoriesViewHolder(view)
    }

    // Cantidad de elementos que va a tener en total el RecyclerView
    override fun getItemCount() = categories.size


    /**
     * Esta funcion se llama para mostrar los datos en pantalla.
     */
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position], onItemSelected)

    }

}