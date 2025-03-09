package com.example.recipeexplorer

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipeexplorer.ui.theme.RecipeExplorerTheme
import ui.RecipeListScreen
import ui.RecipeViewModel

enum class RecipeScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name)
}

@Composable
fun RecipeExplorerApp(modifier: Modifier = Modifier) {
    //Setting up navigation
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RecipeScreen.valueOf(
        backStackEntry?.destination?.route ?: RecipeScreen.Start.name
    )

    val recipeViewModel: RecipeViewModel = viewModel()

    Scaffold() { innerPadding ->
        val uiState by recipeViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = RecipeScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = RecipeScreen.Start.name) {
                RecipeListScreen(recipeViewModel.recipes)
            }
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    RecipeExplorerTheme {
        RecipeExplorerApp()
    }
}
