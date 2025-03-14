package ui

import androidx.lifecycle.ViewModel
import data.Recipe
import data.RecipeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeUIState())
    val uiState: StateFlow<RecipeUIState> = _uiState.asStateFlow()

    var recipes: MutableList<Recipe> = mutableListOf()
    lateinit var currentRecipe: Recipe

    init {
        initialize()
    }

    fun initialize() {
        recipes.clear()
        //Populate the recipe list
        data.recipes.forEach { item ->
            recipes.add(item)
        }
        setRecipe(recipes[0])   //set default recipe
        _uiState.value = RecipeUIState(
            recipeList = recipes,
            currentRecipe = currentRecipe
        )
    }

    fun setRecipe(recipe: Recipe) {
        currentRecipe = recipe
        _uiState.value = RecipeUIState(
            recipeList = recipes,
            currentRecipe = currentRecipe
        )
    }
}