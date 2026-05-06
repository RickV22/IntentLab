package com.example.intent

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsManager(context: Context) {
    private val userNameKey = stringPreferencesKey("user_name")

    val userNameFlow: Flow<String> = context.dataStore.data.map { pref ->
        pref[userNameKey] ?: "Invitado"
    }

    suspend fun saveName(name: String, context: Context) {
        context.dataStore.edit { pref ->
            pref[userNameKey] = name
        }
    }
}