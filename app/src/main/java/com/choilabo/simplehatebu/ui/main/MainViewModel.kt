package com.choilabo.simplehatebu.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.choilabo.simplehatebu.data.setting.SettingDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by sato_shinichiro on 2020/10/07
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    settingDataModel: SettingDataModel
) : ViewModel() {

    val darkMode = settingDataModel.observeDarkMode()
        .asLiveData(viewModelScope.coroutineContext)
}