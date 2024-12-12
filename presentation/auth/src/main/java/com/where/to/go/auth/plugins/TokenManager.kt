package com.where.to.go.auth.plugins

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


object TokenManager {
<<<<<<< HEAD
    private const val PREFS_NAME = "secure_prefs_debug"
    private const val TOKEN_KEY = "auth_token_debug"
=======
    private const val PREFS_NAME = "secure_prefs"
    private const val TOKEN_KEY = "auth_token"
>>>>>>> origin/main

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        sharedPreferences = EncryptedSharedPreferences.create(
            PREFS_NAME,
            masterKey,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

<<<<<<< HEAD
    fun getToken(): String {
        return sharedPreferences.getString(TOKEN_KEY, "") ?: ""
=======
    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
>>>>>>> origin/main
    }

    fun clearToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }
}