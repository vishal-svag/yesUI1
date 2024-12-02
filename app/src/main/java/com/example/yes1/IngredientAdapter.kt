package com.example.yes1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView

class IngredientAdapter(
    private val items: List<IngredientItem>
) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: AppCompatCheckBox = itemView.findViewById(R.id.checkBox)
        val tvIngredients: TextView = itemView.findViewById(R.id.tvIngredients)
        val txtKcal: TextView = itemView.findViewById(R.id.txt2)
        val txt1: TextView = itemView.findViewById(R.id.txt1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val item = items[position]
        holder.tvIngredients.text = item.ingredients
        holder.txtKcal.text = item.kcal
        holder.txt1.text = item.item1
        holder.checkBox.setOnClickListener {
            item.isChecked = !item.isChecked
        }

        // Handle click on the checkbox
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
        }
    }

    override fun getItemCount(): Int = items.size
}
