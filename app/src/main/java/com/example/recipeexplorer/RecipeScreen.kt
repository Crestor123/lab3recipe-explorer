package com.example.recipeexplorer

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import data.Recipe
import data.RecipeUIState
import ui.RecipeDetailScreen
import ui.RecipeListScreen
import ui.RecipeViewModel
import utils.RecipeContentType

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
fun RecipeExplorerApp(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    windowSize: WindowWidthSizeClass
) {
    //Setting up navigation
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RecipeScreen.valueOf(
        backStackEntry?.destination?.route ?: RecipeScreen.RecipeList.name
    )

    val recipeViewModel: RecipeViewModel = viewModel()

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> RecipeContentType.ListOnly

        WindowWidthSizeClass.Expanded -> RecipeContentType.ListAndDetail
        else -> RecipeContentType.ListOnly
    }

    val screenTitle = if (currentScreen == RecipeScreen.RecipeDetail) {
        stringResource(recipeViewModel.currentRecipe.name)
    } else {
        stringResource(currentScreen.title)
    }

    val uiState by recipeViewModel.uiState.collectAsState()

    if (contentType == RecipeContentType.ListAndDetail) {
        ListAndDetail(
            recipeViewModel,
            onRecipeCardClick = { recipeViewModel.setRecipe(it) },
            onBackPressed = onBackPressed,
            uiState = uiState,
            modifier = modifier
        )
    }

    else {
        Scaffold(
            topBar = {
                RecipeExplorerAppBar(
                    title = screenTitle
                )
            }
        ) { innerPadding ->
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
                        recipeViewModel.currentRecipe,
                        onBackPressed = {
                            navController.navigate(RecipeScreen.RecipeList.name)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ListAndDetail(
    recipeViewModel: RecipeViewModel,
    onRecipeCardClick: (Recipe) -> Unit,
    onBackPressed: () -> Unit,
    uiState: RecipeUIState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {

        Scaffold(
            topBar = {
                RecipeExplorerAppBar(
                    title = stringResource(R.string.recipe_list)
                )
            },
            modifier = Modifier.weight(2f)
        ) { innerPadding ->
            RecipeListScreen(
                recipeViewModel.recipes,
                onRecipeCardClick = onRecipeCardClick,
                modifier = Modifier
                    .padding(innerPadding)
            )
        }

        Scaffold (
            topBar = {
                RecipeExplorerAppBar(
                    title = stringResource(recipeViewModel.currentRecipe.name)
                )
            },
            modifier = Modifier.weight(3f)
        ) { innerPadding ->
            RecipeDetailScreen(
                uiState.currentRecipe,
                onBackPressed = onBackPressed,
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
    }
}

@Preview
@Composable
fun CompactPreview() {
    val recipeViewModel: RecipeViewModel = viewModel()
    RecipeExplorerApp(
        onBackPressed = {},
        windowSize = WindowWidthSizeClass.Compact
    )
}

@PreviewScreenSizes
@Composable
fun ExpandedPreview() {
    val recipeViewModel: RecipeViewModel = viewModel()
    RecipeExplorerApp(
        onBackPressed = {},
        windowSize = WindowWidthSizeClass.Expanded
    )
}
