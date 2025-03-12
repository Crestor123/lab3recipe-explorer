package com.example.recipeexplorer

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipeexplorer.ui.theme.RecipeExplorerTheme
import data.Recipe
import ui.RecipeDetailScreen
import ui.RecipeListScreen
import ui.RecipeViewModel

enum class RecipeScreen(@StringRes val title: Int) {
    RecipeList(title = R.string.recipe_list),
    RecipeDetail(title = R.string.recipe_detail)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeExplorerAppBar(
    title: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text( text = title) },
        modifier = modifier
    )
}

@Composable
fun RecipeExplorerApp(modifier: Modifier = Modifier) {
    //Setting up navigation
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RecipeScreen.valueOf(
        backStackEntry?.destination?.route ?: RecipeScreen.RecipeList.name
    )

    val recipeViewModel: RecipeViewModel = viewModel()

    val screenTitle = if (currentScreen == RecipeScreen.RecipeDetail) {
        stringResource(recipeViewModel.currentRecipe.name)
    } else {
        stringResource(currentScreen.title)
    }

    Scaffold(
        topBar = {
            RecipeExplorerAppBar(
                title = screenTitle
            )
        }
    ) { innerPadding ->
        val uiState by recipeViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = RecipeScreen.RecipeList.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = RecipeScreen.RecipeList.name) {
                RecipeListScreen(
                    recipeViewModel.recipes,
                    onRecipeCardClick = {
                        recipeViewModel.setRecipe(it)
                        navController.navigate(RecipeScreen.RecipeDetail.name)
                    }
                )
            }

            composable(route = RecipeScreen.RecipeDetail.name) {
                RecipeDetailScreen(
                    recipeViewModel.currentRecipe
                )
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
