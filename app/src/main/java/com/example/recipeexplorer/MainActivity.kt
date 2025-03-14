package com.example.recipeexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.recipeexplorer.ui.theme.RecipeExplorerTheme

@ExperimentalMaterial3WindowSizeClassApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeExplorerTheme {
                    val windowSize = calculateWindowSizeClass(this)
                    RecipeExplorerApp(
                        onBackPressed = { finish() },
                        windowSize = windowSize.widthSizeClass
                    )
                }
            }
        }
    }

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@PreviewScreenSizes
@Composable
fun AppPreview() {
    RecipeExplorerApp(
        onBackPressed = {},
        windowSize = WindowWidthSizeClass.Expanded
    )
}