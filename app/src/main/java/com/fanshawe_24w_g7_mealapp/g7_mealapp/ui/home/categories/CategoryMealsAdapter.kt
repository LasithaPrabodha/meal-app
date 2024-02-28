package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fanshawe_24w_g7_mealapp.g7_mealapp.R
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal

class CategoryMealsAdapter (private val dataSet: Array<Meal>) :
    RecyclerView.Adapter<CategoryMealsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val image: ImageView

        init {
            title = view.findViewById(R.id.catItemTitle)
            image = view.findViewById(R.id.categoryItemImage)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_home_category_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataSet[position].strMeal
        viewHolder.image.load(dataSet[position].strMealThumb)
    }

    override fun getItemCount() = dataSet.size

}