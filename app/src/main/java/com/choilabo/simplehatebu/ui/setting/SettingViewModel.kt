package com.choilabo.simplehatebu.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.choilabo.simplehatebu.data.setting.SettingDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by sato_shinichiro on 2020/10/06
 */
@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingDataModel: SettingDataModel
) : ViewModel(), SettingView.Listener {

    val darkMode = settingDataModel.observeDarkMode()
        .asLiveData(viewModelScope.coroutineContext)

    override fun onDarkModeCheckedChanged(isDarkMode: Boolean) {
        viewModelScope.launch {
            settingDataModel.setDarkMode(isDarkMode)
        }
    }
}