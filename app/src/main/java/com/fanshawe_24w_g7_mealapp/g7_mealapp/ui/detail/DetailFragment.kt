package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.detail


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.fanshawe_24w_g7_mealapp.g7_mealapp.MainActivity
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.FragmentDetailBinding
import com.fanshawe_24w_g7_mealapp.g7_mealapp.util.LoadingDialog
import com.fanshawe_24w_g7_mealapp.g7_mealapp.util.getIngredients
import kotlinx.coroutines.launch
import javax.annotation.Nullable


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by activityViewModels()
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var loader: LoadingDialog

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // displays the back button on toolbar
        (activity as MainActivity?)!!.showUpButton()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = ""
        loader = LoadingDialog(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loader.show()
        viewModel.getDetailFromServer(args.mealId)

        addSubscriptions()

    }

    private fun addSubscriptions() {
        viewModel.onDetailState.observe(viewLifecycleOwner) { detailMeal ->
            if (detailMeal != null) {

                if(loader.isShowing) loader.hide()

                lifecycleScope.launch {
                    viewModel.insertRcMeal(detailMeal)
                }
                binding.apply {

                    (requireActivity() as AppCompatActivity).supportActionBar?.title =
                        detailMeal.strMeal

                    val ingredients = detailMeal.getIngredients()
                    val adapter = IngredientsAdapter(ingredients)
                    rvIngredients.layoutManager = LinearLayoutManager(requireContext())
                    rvIngredients.adapter = adapter


                    prodDescriptionTv.text = detailMeal.strInstructions
                    mealImg.load(detailMeal.strMealThumb)
                    categoryTv.text = detailMeal.strCategory
                    areaTv.text = detailMeal.strArea
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}