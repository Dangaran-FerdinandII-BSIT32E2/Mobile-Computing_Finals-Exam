package com.example.recipebook.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recipes(
    @StringRes val stringResourceId: Int, //name of recipe
    @DrawableRes val imageResourceId: Int, //image of recipe
    @StringRes val recipeResourceId: Int //recipe
)
