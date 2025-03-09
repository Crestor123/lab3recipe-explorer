package com.example.recipeexplorer

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ui.RecipeViewModel

enum class RecipeScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name)
}

@Composable
fun RecipeExplorerApp() {
    //Setting up navigation
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RecipeScreen.valueOf(
        backStackEntry?.destination?.route ?: RecipeScreen.Start.name
    )

    val recipeViewModel: RecipeViewModel = viewModel()
}

