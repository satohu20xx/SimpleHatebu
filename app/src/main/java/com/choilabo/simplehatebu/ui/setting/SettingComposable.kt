package com.choilabo.simplehatebu.ui.setting

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.choilabo.simplehatebu.R
import com.choilabo.simplehatebu.ui.common.MainTheme

/**
 * Created by sato_shinichiro on 2021/10/11.
 */
@Composable
fun SettingComposable(
    isDarkMode: Boolean,
    listener: SettingComposableListener
) {
    MainTheme {
        Row(Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.setting_dark_mode_title)
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isDarkMode,
                onCheckedChange = {
                    listener.onDarkModeCheckedChanged(it)
                }
            )
        }
    }
}

interface SettingComposableListener {

    fun onDarkModeCheckedChanged(isDarkMode: Boolean)
}