package com.example.challengeonairandroid.model.data.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenManager @Inject constructor(private val dataStore: DataStore<Preferences>) {
    private val ACCESS_TOKEN = stringPreferencesKey("access_token")
    private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")

    suspend fun saveAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = token
        }
    }

    suspend fun saveRefreshToken(token: String) {
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN] = token
        }
    }

    suspend fun getAccessToken(): String? {
        return dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN]
        }.first()
    }

    suspend fun getRefreshToken(): String? {
        return dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }.first()
    }

    suspend fun clearTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN)
            preferences.remove(REFRESH_TOKEN)
        }
    }
}

private const val TOKEN_DATASTORE = "token_datastore"

val Context.encryptedDataStore: DataStore<Preferences> by preferencesDataStore(
    name = TOKEN_DATASTORE,
    produceMigrations = { context ->
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val encryptedPreferences = EncryptedSharedPreferences.create(
            context,
            TOKEN_DATASTORE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        listOf(
            androidx.datastore.migrations.SharedPreferencesMigration(
                context,
                TOKEN_DATASTORE
            ) { sharedPrefs, currentData ->
                // 여기서 SharedPreferences에서 DataStore로의 마이그레이션 로직을 구현할 수 있습니다.
                // 이 예제에서는 단순히 현재 데이터를 반환합니다.
                currentData
            }
        )
    }
)