package ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import data.Recipe
import data.recipes

@Composable
fun RecipeListScreen(recipeList: MutableList<Recipe>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(recipeList) { recipe ->
            RecipeCard(recipe)
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(16.dp),

    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(recipe.name)
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(recipe.description)
        )
    }
}