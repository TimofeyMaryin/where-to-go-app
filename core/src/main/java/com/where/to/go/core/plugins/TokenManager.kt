package com.where.to.go.core.plugins

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


object TokenManager {

    private const val PREFS_NAME = "secure_prefs"
    private const val TOKEN_KEY = "auth_token"
    private const val EMAIL_KEY = "email"

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

    fun saveEmail(token: String) {
        sharedPreferences.edit().putString(EMAIL_KEY, token).apply()
    }

    fun getEmail(): String {
        return sharedPreferences.getString(EMAIL_KEY, "") ?: ""
    }


    fun clearEmail() {
        sharedPreferences.edit().remove(EMAIL_KEY).apply()
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String {
        return "${sharedPreferences.getString(TOKEN_KEY, "")}"
    }

    fun getHeaderToken(): String {
        return "Bearer ${sharedPreferences.getString(TOKEN_KEY, "")}"
    }

    /*     fun getToken(): String {
        return "Bearer ${sharedPreferences.getString(TOKEN_KEY, "")}"
    }*/

    fun clearToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }
}