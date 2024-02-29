package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.IngredientsItemCardViewBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Ingredient


class IngredientsAdapter(private val placesList: List<Ingredient>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientsAdapter.ViewHolder {
        val binding =
            IngredientsItemCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsAdapter.ViewHolder, position: Int) {
        val item = placesList[position]
        holder.bind(item)
    }


    override fun getItemCount(): Int {
        return placesList.size
    }

    inner class ViewHolder(private val binding: IngredientsItemCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Ingredient) {
            binding.ingredientNameTv.text = item.name
            binding.qtyTv.text = item.quantity
            binding.imageView.load("https://www.themealdb.com/images/ingredients/${item.name}.png")

        }
    }
}