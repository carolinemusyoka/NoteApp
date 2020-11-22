package com.carolmusyoka.noteapp.onboarding.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import com.carolmusyoka.noteapp.datastore.UIModePreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnBoardingPrefManager(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = "onboarding_mode_preference"
    )

    suspend fun setFirstTimeLaunch(isFirstTime: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_FIRST_TIME_LAUNCH] = isFirstTime
        }
    }
    val firstTimeLaunch: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[IS_FIRST_TIME_LAUNCH] ?: false
           
        }


    companion object {
        private const val PRIVATE_MODE = 0    // Shared preference mode
        private const val PREF_NAME = "app-prefs"
        private const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    }
}
