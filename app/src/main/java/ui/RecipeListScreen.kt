package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import data.Recipe

@Composable
fun RecipeListScreen(
    recipeList: MutableList<Recipe>,
    onRecipeCardClick: (Recipe) -> Unit,
    modifier : Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(recipeList) { recipe ->
            RecipeCard(recipe, onClick = {
                onRecipeCardClick(recipe)
            } )
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(recipe.name),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Text(
                text = stringResource(recipe.description)
            )
        }

    }
}

@Preview
@Composable
fun RecipeListPreview() {
    val recipeViewModel: RecipeViewModel = viewModel()
    RecipeListScreen(
        recipeList = recipeViewModel.recipes,
        onRecipeCardClick = {}
    )
}