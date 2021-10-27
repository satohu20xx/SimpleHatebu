package com.choilabo.simplehatebu.ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.choilabo.simplehatebu.R

/**
 * Created by sato_shinichiro on 2021/10/11.
 */
@Composable
fun MainTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (!isSystemInDarkTheme()) {
            lightColors(
                surface = colorResource(id = R.color.white),
                primary = colorResource(id = R.color.gray_60),
                primaryVariant = colorResource(id = R.color.gray_8D),
                background = colorResource(id = R.color.gray_FA),
                secondary = colorResource(id = R.color.gray_60),
                secondaryVariant = colorResource(id = R.color.gray_8D),
                error = colorResource(id = R.color.red),
            )
        } else {
            darkColors(
                surface = colorResource(id = R.color.gray_FA),
                primary = colorResource(id = R.color.gray_C0),
                primaryVariant = colorResource(id = R.color.gray_FA),
                background = colorResource(id = R.color.gray_18),
                secondary = colorResource(id = R.color.gray_C0),
                secondaryVariant = colorResource(id = R.color.gray_FA),
                error = colorResource(id = R.color.red),
            )
        }
    ) {
        Surface(
            color = MaterialTheme.colors.background,
            content = content
        )
    }
}