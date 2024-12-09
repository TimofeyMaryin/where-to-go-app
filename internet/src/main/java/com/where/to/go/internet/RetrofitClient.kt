package com.where.to.go.internet

import com.where.to.go.internet.dao.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.107:8080" // http://127.0.0.1:8080

    private val client = OkHttpClient.Builder()
        .build()


    val instance: UserService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

}