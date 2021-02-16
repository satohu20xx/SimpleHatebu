package com.choilabo.simplehatebu.data.setting

import com.choilabo.simplehatebu.data.setting.repository.SettingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by sato_shinichiro on 2020/10/06
 */
class SettingDataModel @Inject constructor(
    private val settingRepository: SettingRepository
) {

    fun observeDarkMode(): Flow<Boolean> = settingRepository.observeDarkMode()

    suspend fun setDarkMode(isDarkMode: Boolean) {
        withContext(Dispatchers.IO) {
            settingRepository.setDarkMode(isDarkMode)
        }
    }
}