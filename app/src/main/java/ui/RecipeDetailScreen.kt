package ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import data.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    Text(
        text = stringResource(recipe.description),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    )
}

@Preview
@Composable
fun RecipeDetailPreview() {
    val recipeViewModel: RecipeViewModel = viewModel()
    RecipeDetailScreen(
        recipe = recipeViewModel.currentRecipe,
        onBackPressed = {}
    )
}