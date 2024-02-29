package com.fanshawe_24w_g7_mealapp.g7_mealapp.util

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Ingredient
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealDetail


fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun Fragment.hideKeyboard() = ViewCompat
    .getWindowInsetsController(requireView())
    ?.hide(WindowInsetsCompat.Type.ime())

fun MealDetail.getIngredients(): List<Ingredient> {
    var ind = listOf<Ingredient>()
    this?.let {
        ind = listOf<Ingredient>(
            Ingredient(this.strIngredient1, this.strMeasure1),
            Ingredient(this.strIngredient2, this.strMeasure2),
            Ingredient(this.strIngredient3, this.strMeasure3),
            Ingredient(this.strIngredient4, this.strMeasure4),
            Ingredient(this.strIngredient5, this.strMeasure5),
            Ingredient(this.strIngredient6, this.strMeasure6),
            Ingredient(this.strIngredient7, this.strMeasure7),
            Ingredient(this.strIngredient8, this.strMeasure8),
            Ingredient(this.strIngredient9, this.strMeasure9),
            Ingredient(this.strIngredient10, this.strMeasure10),
            Ingredient(this.strIngredient11, this.strMeasure12),
            Ingredient(this.strIngredient12, this.strMeasure12),
            Ingredient(this.strIngredient13, this.strMeasure13),
            Ingredient(this.strIngredient14, this.strMeasure14),
            Ingredient(this.strIngredient15, this.strMeasure15),
            Ingredient(this.strIngredient16, this.strMeasure16),
            Ingredient(this.strIngredient17, this.strMeasure17),
            Ingredient(this.strIngredient18, this.strMeasure18),
            Ingredient(this.strIngredient19, this.strMeasure19),
            Ingredient(this.strIngredient20, this.strMeasure20)
        )
        ind =
            ind.filterNot { it.name == null || it.name == "" || it.quantity == null || it.quantity == "" }

    }

    return ind

}

