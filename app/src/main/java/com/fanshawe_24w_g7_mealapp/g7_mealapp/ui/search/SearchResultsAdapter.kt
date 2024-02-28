package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fanshawe_24w_g7_mealapp.g7_mealapp.R
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.SearchResultItemBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.MealItemClickListener

class SearchResultsAdapter(
    private val dataSet: MutableList<Meal>,
    private val listener: MealItemClickListener
) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchResultItemBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )

        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
//        viewHolder.setIsRecyclable(false)
    }

    inner class ViewHolder(
        private val item: SearchResultItemBinding,
        private val listener: MealItemClickListener
    ) : RecyclerView.ViewHolder(item.root) {
        fun bind(meal: Meal) {
            item.txtSearchItem.text = meal.strMeal
            item.imgSearchItem.load(meal.strMealThumb)

            item.root.setOnClickListener { listener.onItemClick(meal) }
        }
    }

    override fun getItemCount() = dataSet.size

    fun updateList(list: MutableList<Meal>) {
        val callback = MealsDiffCallBack(dataSet, list)
        val result = DiffUtil.calculateDiff(callback)
        dataSet.apply {
            clear()
            addAll(list)
        }
        Log.e("Search", dataSet.toString())
        result.dispatchUpdatesTo(this)
    }

    fun reset() {
        val size = dataSet.size
        dataSet.clear()
        notifyItemRangeRemoved(0, size)
    }



}