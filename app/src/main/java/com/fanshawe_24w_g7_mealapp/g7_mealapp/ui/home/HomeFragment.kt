package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.fanshawe_24w_g7_mealapp.g7_mealapp.MainActivity
import com.fanshawe_24w_g7_mealapp.g7_mealapp.R
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.FragmentHomeBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.FragmentSearchBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Category
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.MealItemClickListener
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.categories.CategoryAdapter
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.recently_checked.RecentlyCheckedMealsAdapter
import com.fanshawe_24w_g7_mealapp.g7_mealapp.util.hide
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment(), FragmentManager.OnBackStackChangedListener, MealItemClickListener {

    private val viewModel: HomeViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.supportFragmentManager?.addOnBackStackChangedListener(this);
    }

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
            setupCategories(categories)
        }

        viewModel.getMealOfTheDay()
        viewModel.getCategories()

        lifecycleScope.launch {
            setupRecentlyChecked()
        }

        buttonListeners()

    }

    private fun buttonListeners() {
        binding.btnClearRc.setOnClickListener {
            lifecycleScope.launch {
                viewModel.deleteAllRecentMeals()
                setupRecentlyChecked()
            }
        }

    }

    private suspend fun setupRecentlyChecked() {
        val rcMeals = viewModel.getRcMeals()
        val adapter = RecentlyCheckedMealsAdapter(rcMeals, this)

        if (rcMeals.isEmpty()) {
            binding.rcTitles.hide()
        }

        binding.rvRecentlyCheckedMeals.adapter = adapter
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvRecentlyCheckedMeals.layoutManager = layoutManager

    }

    private fun setupCategories(categories: List<Category>) {
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


    override fun onBackStackChanged() {
        // enable Up button only if there are entries on the backstack
        if (requireActivity().supportFragmentManager.backStackEntryCount < 1) {
            (activity as MainActivity?)!!.hideUpButton()
        }
    }

    override fun onItemClick(meal: Meal) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToDetailFragment(
                meal.idMeal
            )
        )
    }

    override fun onFavouriteClick(meal: Meal) {
    }

}