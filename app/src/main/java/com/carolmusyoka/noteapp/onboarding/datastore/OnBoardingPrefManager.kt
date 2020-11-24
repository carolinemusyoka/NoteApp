@file:Suppress("UNCHECKED_CAST")

package com.carolmusyoka.noteapp.onboarding.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.map

class OnBoardingPrefManager(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(
    name = "onboarding_mode_preference"
    )

     suspend fun setFirstTimeLaunch(isFirstTime: Boolean) {
        dataStore.edit {preferences ->
            preferences[IS_FIRST_TIME_LAUNCH] = isFirstTime
        }

    }

    val firstTimeLaunch =  dataStore.data
            .map { preferences ->
                preferences[IS_FIRST_TIME_LAUNCH] ?: false

            }



    companion object {
        private val IS_FIRST_TIME_LAUNCH = preferencesKey<Boolean>("is_first_time")
    }
}
