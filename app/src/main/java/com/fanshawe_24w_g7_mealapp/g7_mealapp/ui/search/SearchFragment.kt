package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fanshawe_24w_g7_mealapp.g7_mealapp.R
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.FragmentSearchBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.MealItemClickListener
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.HomeFragmentDirections
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.recently_checked.RecentlyCheckedMealsAdapter
import com.fanshawe_24w_g7_mealapp.g7_mealapp.util.hide
import com.fanshawe_24w_g7_mealapp.g7_mealapp.util.hideKeyboard
import com.fanshawe_24w_g7_mealapp.g7_mealapp.util.show
import kotlinx.coroutines.launch


class SearchFragment : Fragment(), MealItemClickListener {
    private val viewModel: SearchViewModel by activityViewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var mealsList = mutableListOf<Meal>()
    private lateinit var searchAdapter: SearchResultsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        Log.e("Search", "Binding added")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchResulta()



        setupTextEditorListener()
        observeResult()

    }

    private fun observeResult() {
        viewModel.mealsLiveData.observe(viewLifecycleOwner) {

            when (it) {
                is SearchViewModel.MealState.Success -> {
                    mealsList.addAll(it.data)
                    if (mealsList.isEmpty()) {
                        onEmptySearchResult(true, binding.searchView.text.toString())
                    }
                    searchAdapter.updateList(it.data.toMutableList())
                }

                is SearchViewModel.MealState.Loading -> {
                    if (it.isLoading) onEmptySearchResult(false)
                    onProgress(it.isLoading)
                }

                is SearchViewModel.MealState.Error -> {
                    onEmptySearchResult(true, it.throwable.message.toString(), isError = true)
                    onProgress(false)
                }
            }
        }
    }

    private fun setupTextEditorListener() {
        binding.searchView.editText.setOnEditorActionListener { _, _, _ ->
            cleanUpSearch()
            val text = binding.searchView.text.toString()

            when {
                text.trim().isNotEmpty() -> {
                    hideKeyboard()
                    cleanUpSearch()
                    viewModel.searchMeals(text)
                    true
                }

                text.trim().isEmpty() -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.enter_text_to_search),
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }

                else -> {
                    onEmptySearchResult(true, getString(R.string.no_internet), true)
                    true
                }
            }
        }

    }


    private fun cleanUpSearch() {
        mealsList.clear()
        searchAdapter.reset()
    }

    private fun onProgress(shouldShow: Boolean) {
        if (shouldShow) binding.progress.root.show()
        else binding.progress.root.hide()
    }

    private fun onEmptySearchResult(
        shouldShow: Boolean,
        message: String = "",
        isError: Boolean = false
    ) {
        if (shouldShow) binding.emptySearchLayout.root.show()
        else binding.emptySearchLayout.root.hide()

        if (isError) binding.emptySearchLayout.itemText.text = message
        else binding.emptySearchLayout.itemText.text = getString(
            R.string.we_found_no_results_for_try_searching_another_meal,
            message
        )
    }

    private fun setupSearchResulta() {
        searchAdapter = SearchResultsAdapter(mealsList, this)
        binding.rvSearchResults.adapter = searchAdapter
        binding.rvSearchResults.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("Search", "Binding removed")

        _binding = null
    }

    override fun onItemClick(meal: Meal) {
        findNavController().navigate(
            SearchFragmentDirections.actionNavigationSearchToDetailFragment(
                meal.idMeal
            )
        )
    }

}