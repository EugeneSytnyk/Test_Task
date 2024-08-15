package eugene.sytnyk.testtask.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "action_usage_time")

class ActionUsageTimeRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun getLastUsageTimeForActionType(type: String): Long? {
        return context.dataStore.data.map { it[longPreferencesKey(type)] }.first()
    }

    suspend fun setLastUsageTimeForActionType(type: String, time: Long) {
        context.dataStore.edit { data ->
            data[longPreferencesKey(type)] = time
        }
    }
}
