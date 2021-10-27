package com.choilabo.simplehatebu.data.setting.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sato_shinichiro on 2020/10/06
 */
@Singleton
class SettingRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private companion object {
        val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("setting")

    fun observeDarkMode(): Flow<Boolean> = context.dataStore.data.map { it[KEY_DARK_MODE] ?: false }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit {
            it[KEY_DARK_MODE] = isDarkMode
        }
    }
}