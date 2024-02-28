package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.fanshawe_24w_g7_mealapp.g7_mealapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        viewModel.getDetailFromServer(args.mealId)

        addSubscriptions()

    }

    private fun addSubscriptions() {
        viewModel.onDetailState.observe(viewLifecycleOwner) {detailMeal ->
            if(detailMeal != null) {
                binding.apply {
                    mealNameTv.text = detailMeal.strMeal
                    prodDescriptionTv.text = detailMeal.strInstructions
                    mealImg.load(detailMeal.strMealThumb)
                    ingredient1NameTv.text = detailMeal.strIngredient1
                    qty1.text = detailMeal.strMeasure1
                    ingredient2NameTv.text = detailMeal.strIngredient2
                    qty2.text = detailMeal.strMeasure2
                    ingredient3NameTv.text = detailMeal.strIngredient3
                    qty3.text = detailMeal.strMeasure3
                    ingredient4NameTv.text = detailMeal.strIngredient4
                    qty4.text = detailMeal.strMeasure4
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