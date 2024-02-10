package com.example.skov.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object UserSession{
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
    private val USER_TOKEN_KEY = stringPreferencesKey("user_token")

    fun getAccessToken(context : Context): Flow<String> {
        return context.dataStore.data.map {
            it[USER_TOKEN_KEY] ?: ""
        }
    }

    suspend fun saveToken(context : Context, token: String) {
        context.dataStore.edit {
            it[USER_TOKEN_KEY] = token
//            it.clear()
        }
    }
}


