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
    private lateinit var currentRecipe: Recipe

    init {
        initialize()
    }

    fun initialize() {
        recipes.clear()
        //Populate the recipe list
        data.recipes.forEach { item ->
            recipes.add(item)
        }
        _uiState.value = RecipeUIState(recipeList = recipes)
    }
}