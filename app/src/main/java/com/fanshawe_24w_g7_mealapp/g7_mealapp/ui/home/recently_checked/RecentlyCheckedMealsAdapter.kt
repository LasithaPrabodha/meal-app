package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.recently_checked

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fanshawe_24w_g7_mealapp.g7_mealapp.R
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.FragmentHomeCategoryItemBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.RecentlyCheckedMealBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.entities.RecentlyCheckedMeal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.MealItemClickListener
import com.fanshawe_24w_g7_mealapp.g7_mealapp.util.toMeal

class RecentlyCheckedMealsAdapter(
    private val dataSet: List<RecentlyCheckedMeal>,
    private val listener: MealItemClickListener
) :
    RecyclerView.Adapter<RecentlyCheckedMealsAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val item: RecentlyCheckedMealBinding,
        private val listener: MealItemClickListener

    ) : RecyclerView.ViewHolder(item.root) {

        fun bind(rcMeal: RecentlyCheckedMeal) {
            item.txtRecentlyChecked.text = rcMeal.strMeal
            item.imgRecentlyChecked.load(rcMeal.strMealThumb)

            item.root.setOnClickListener { listener.onItemClick(rcMeal.toMeal()) }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecentlyCheckedMealBinding.inflate(
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