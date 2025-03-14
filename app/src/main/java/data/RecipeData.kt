package data

import androidx.annotation.StringRes
import com.example.recipeexplorer.R

data class Recipe(
    @StringRes val name: Int,
    @StringRes val description: Int
)

val recipes: Set<Recipe> =
    setOf(
        Recipe(
            R.string.spaghetti_bolognese_name,
            R.string.spaghetti_bolognese_desc
        ),
        Recipe(
            R.string.chicken_curry_name,
            R.string.chicken_curry_desc
        ),
        Recipe(
            R.string.beef_stroganoff_name,
            R.string.beef_stroganoff_desc
        )
    )