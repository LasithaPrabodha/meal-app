package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fanshawe_24w_g7_mealapp.g7_mealapp.R
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.FragmentHomeCategoryItemBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.SearchResultItemBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.MealItemClickListener

class CategoryMealsAdapter(
    private val dataSet: List<Meal>,
    private val listener: MealItemClickListener
) :
    RecyclerView.Adapter<CategoryMealsAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val item: FragmentHomeCategoryItemBinding,
        private val listener: MealItemClickListener

        ) : RecyclerView.ViewHolder(item.root) {

        fun bind(meal: Meal){
            item.txtMealTitle.text = meal.strMeal
            item.imgCategoryMeal.load(meal.strMealThumb)

            item.root.setOnClickListener { listener.onItemClick(meal) }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentHomeCategoryItemBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )

        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size


}