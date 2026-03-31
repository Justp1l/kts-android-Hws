package org.example.spaceShape.cmp.feature.onBoard.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.example.spaceShape.getFilesDir

internal const val DATASTORE_FILE_NAME = "prefs.preferences_pb"
object DataStoreProvider {
    val instance: DataStore<Preferences> by lazy {
        PreferenceDataStoreFactory.createWithPath (
            produceFile = {"${getFilesDir()}/$DATASTORE_FILE_NAME".toPath()}
        )
    }
}

