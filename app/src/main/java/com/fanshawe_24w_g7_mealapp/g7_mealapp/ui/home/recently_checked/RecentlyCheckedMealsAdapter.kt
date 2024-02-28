package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.recently_checked

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fanshawe_24w_g7_mealapp.g7_mealapp.R
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal

class RecentlyCheckedMealsAdapter(private val dataSet: Array<Meal>) :
    RecyclerView.Adapter<RecentlyCheckedMealsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val image: ImageView

        init {
            title = view.findViewById(R.id.recentlyCheckedMealTitle)
            image = view.findViewById(R.id.recentlyCheckedMealImg)

            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(dataSet[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recently_checked_meal, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataSet[position].strMeal
        viewHolder.image.load(dataSet[position].strMealThumb)
    }

    override fun getItemCount() = dataSet.size

    private var onItemClickListener: ((Meal) -> Unit)? = null

    fun setOnclickListener(listener: (Meal) -> Unit){
        onItemClickListener = listener
    }

}