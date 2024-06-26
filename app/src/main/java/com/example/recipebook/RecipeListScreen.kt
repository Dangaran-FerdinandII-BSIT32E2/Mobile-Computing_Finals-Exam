package com.example.recipebook

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipebook.data.recipes
import com.example.recipebook.model.Recipes
import com.example.recipebook.ui.theme.RecipeBookTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.ui.res.dimensionResource

@Composable
fun RecipeApp(
) {
    Scaffold(
        topBar = {
            RecipeTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(recipes) {
                RecipeItem(
                    recipe = it,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }

    }
}

@Composable
fun RecipeItem(
    recipe: Recipes,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )
    Card(
        modifier = modifier
    ) {
        Column(
               modifier = Modifier
                   .animateContentSize(
                       animationSpec = spring(
                           dampingRatio = Spring.DampingRatioNoBouncy,
                           stiffness = Spring.StiffnessMedium
                       )
                   )
                   .background(color = color)
        ) {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            RecipeIcon(recipe.imageResourceId)
            RecipeInformation(recipe.stringResourceId)
            Spacer(modifier = Modifier.weight(1f))
            RecipeItemButton(
                expanded = expanded,
                onClick = { expanded = !expanded }
            )
        }
        if(expanded) {
            RecipeRecipes(
                recipe.recipeResourceId,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
            )
        }
    }
}

@Composable
private fun RecipeItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = painterResource(R.drawable.recipe_app_icon),
                    contentDescription = null //IMAGE NEEDS CONTENTDESCRIPTION
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun RecipeIcon(
    @DrawableRes recipeIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(recipeIcon),
        contentDescription = null
    )
}

@Composable
fun RecipeInformation(
    @StringRes recipeName: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(recipeName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun RecipeRecipes(
    @StringRes recipeRecipe: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.recipe),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(recipeRecipe),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun RecipeLightPreview() {
    RecipeBookTheme(darkTheme = false) {
        RecipeApp()
    }
}

@Preview
@Composable
fun RecipeDarkPreview() {
    RecipeBookTheme(darkTheme = true) {
        RecipeApp()
    }
}