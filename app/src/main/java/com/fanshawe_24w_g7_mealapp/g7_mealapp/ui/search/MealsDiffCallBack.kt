package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import javax.annotation.Nullable

class MealsDiffCallBack(
    private val oldList: MutableList<Meal>,
    private val newList: MutableList<Meal>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].idMeal == newList[newItemPosition].idMeal
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].strMeal == newList[newItemPosition].strMeal

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}
