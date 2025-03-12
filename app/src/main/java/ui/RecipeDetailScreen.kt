package ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import data.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(recipe.description),
        modifier = Modifier.padding(16.dp)
    )
}