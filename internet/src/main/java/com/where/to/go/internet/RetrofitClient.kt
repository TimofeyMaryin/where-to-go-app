package com.where.to.go.internet

import com.where.to.go.internet.dao.AuthService
import com.where.to.go.internet.dao.PartyService
import com.where.to.go.internet.dao.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.108:8080"
//"http://176.192.150.45:8080" "http://192.168.1.107:8080" "http://10.0.2.2:8080"

    private val client = OkHttpClient.Builder()
        .build()


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
    val partyService: PartyService by lazy {
        retrofit.create(PartyService::class.java)
    }

}