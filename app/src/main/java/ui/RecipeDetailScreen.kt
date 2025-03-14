package ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecipeDetailScreen(
    recipeDescription: String,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    Column (
        modifier = modifier
    ) {
        Text(
            text = recipeDescription,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }

}

@Preview
@Composable
fun RecipeDetailPreview() {
    val recipeViewModel: RecipeViewModel = viewModel()
    RecipeDetailScreen(
        recipeDescription = stringResource(recipeViewModel.currentRecipe.description),
        onBackPressed = {}
    )
}