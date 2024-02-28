package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.categories

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Category

class CategoryAdapter(fragment: Fragment, private  val data: List<Category>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return CategoryMealsFragment.newInstance(data[position])
    }

}