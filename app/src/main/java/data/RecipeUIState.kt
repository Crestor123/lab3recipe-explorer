package data

data class RecipeUIState(
    val currentRecipe: String = "",
    val currentDescription: String = "",
    val recipeList: MutableList<Recipe> = mutableListOf()
)