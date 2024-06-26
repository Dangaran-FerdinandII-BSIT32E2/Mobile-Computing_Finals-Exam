package com.example.recipebook.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.recipebook.R
import com.example.recipebook.model.Recipes

data class Recipes(
    @StringRes val stringResourceId: Int, //name of recipe
    @DrawableRes val imageResourceId: Int, //image of recipe
    @StringRes val recipeResourceId: Int //recipe
)

val recipes = listOf(
    Recipes(R.string.recipe1, R.drawable.adobo, R.string.adobo_recipe),
    Recipes(R.string.recipe2, R.drawable.sinigang, R.string.sinigang_recipe),
    Recipes(R.string.recipe3, R.drawable.pares, R.string.pares_recipe),
    Recipes(R.string.recipe4, R.drawable.dinuguan, R.string.dinuguan_recipe),
    Recipes(R.string.recipe5, R.drawable.menudo, R.string.menudo_recipe),
    Recipes(R.string.recipe6, R.drawable.giniling, R.string.giniling_recipe)
)