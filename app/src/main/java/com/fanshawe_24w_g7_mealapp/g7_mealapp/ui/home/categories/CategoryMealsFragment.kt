package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.categories

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.FragmentCategoryItemsBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Category
import kotlinx.coroutines.launch


class CategoryMealsFragment : Fragment() {
    private val viewModel: CategoryMealsViewModel by activityViewModels()
    private var category: Category? = null

    private var _binding: FragmentCategoryItemsBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            category = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(CATEGORY, Category::class.java)
            } else {
                bundle.getParcelable<Category>(CATEGORY)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoryItemsBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            category?.let {


                val recyclerDataArrayList = viewModel.loadItemInCategory(it.strCategory)

                recyclerDataArrayList?.let {meals->

                    val adapter = CategoryMealsAdapter(meals)

                    val layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


                    binding.root.layoutManager = layoutManager
                    binding.root.adapter = adapter
                }
            }
        }


        binding.root.isVerticalScrollBarEnabled = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        private const val CATEGORY = "category"

        @JvmStatic
        fun newInstance(category: Category) =
            CategoryMealsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CATEGORY, category);
                }
            }
    }
}