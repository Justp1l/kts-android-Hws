package org.example.project.cmp.common.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath
import org.example.project.getFilesDir



class AppStorage(private val dataStorage: DataStore<Preferences> = DataStoreProvider.instance) {
    private companion object {
        val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
    }

    val isFirstLaunch: Flow<Boolean> = dataStorage.data
        .map { prefs -> prefs[IS_FIRST_LAUNCH] ?: true }

    suspend fun setFirstLaunchCompleted() {
        dataStorage.edit { preferences -> preferences[IS_FIRST_LAUNCH] = false }
        Napier.d(dataStorage.data.first().toString(), tag = "storage")
    }
}

