package data

data class RecipeUIState(
    val currentRecipe: Recipe = Recipe(0, 0),
    val recipeList: MutableList<Recipe> = mutableListOf()
)