package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.fanshawe_24w_g7_mealapp.g7_mealapp.R
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.FragmentHomeBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.FragmentSearchBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Category
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.categories.CategoryAdapter
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.recently_checked.RecentlyCheckedMealsAdapter
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.isVerticalScrollBarEnabled = false

        viewModel.mealOfTheDay.observe(viewLifecycleOwner) { meal ->
            binding.imgTodaysSpecial.load(meal.strMealThumb)
            binding.txtTodaysSpecialMealname.text = meal.strMeal
        }

        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            setupCategories(  categories)
        }

        viewModel.getMealOfTheDay()
        viewModel.getCategories()
        setupRecentlyChecked()

    }

    private fun setupRecentlyChecked() {
        val meals = arrayOf(
            Meal(
                "1",
                "Brown Stew Chicken",
                "Chicken",
                "",
                "https://www.themealdb.com/images/media/meals/sypxpx1515365095.jpg"
            ),
            Meal(
                "52819",
                "Cajun spiced fish tacos",
                "Seafood",
                "",
                "https://www.themealdb.com/images/media/meals/uvuyxu1503067369.jpg"
            )
        )
        val adapter = RecentlyCheckedMealsAdapter(meals)
        adapter.setOnclickListener { clickedMeal ->
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToDetailFragment(clickedMeal.idMeal))
        }
        binding.rvRecentlyCheckedMeals.adapter = adapter
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvRecentlyCheckedMeals.layoutManager = layoutManager
    }

    private fun setupCategories( categories: List<Category>) {
        val adapter = CategoryAdapter(this, categories)
        binding.viewpMealsInCategories.adapter = adapter
        binding.viewpMealsInCategories.isUserInputEnabled = false


        TabLayoutMediator(binding.tabCategories, binding.viewpMealsInCategories) { tab, position ->
            tab.text = categories[position].strCategory
        }.attach()

        for (i in 0..categories.size) {
            val textView =
                LayoutInflater.from(requireContext())
                    .inflate(R.layout.tab_title, null, false) as TextView
            binding.tabCategories.getTabAt(i)?.customView = textView
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}