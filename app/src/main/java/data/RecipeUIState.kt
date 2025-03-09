package data

data class RecipeUIState(
    val currentRecipe: String = "",
    val currentDescription: String = "",
    val recipeList: MutableSet<Recipe> = mutableSetOf()
)