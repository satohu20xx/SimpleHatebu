package com.choilabo.simplehatebu.data.setting.repository

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by sato_shinichiro on 2020/10/06
 */
@Reusable
class SettingRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private companion object {
        val KEY_DARK_MODE = preferencesKey<Boolean>("dark_mode")
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore("setting")

    fun observeDarkMode(): Flow<Boolean> = dataStore.data.map { it[KEY_DARK_MODE] ?: false }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        dataStore.edit {
            it[KEY_DARK_MODE] = isDarkMode
        }
    }
}