@file:Suppress("UNCHECKED_CAST")

package com.carolmusyoka.noteapp.onboarding.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.carolmusyoka.noteapp.datastore.UIModePreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnBoardingPrefManager(context: Context) {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = context.createDataStore(
    name = "onboarding_mode_preference"
    )

    suspend fun setFirstTimeLaunch(isFirstTime: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_FIRST_TIME_LAUNCH] = isFirstTime.toString()
        }
    }
    val firstTimeLaunch: Flow<Boolean>
    get() = dataStore.data
            .map { preferences ->
                preferences[IS_FIRST_TIME_LAUNCH] ?: false

            } as Flow<Boolean>


    companion object {
         val IS_FIRST_TIME_LAUNCH = preferencesKey<String>("is_first_time")
    }
}
