package com.where.to.go.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080"
    //"http://176.192.150.45:8080" "http://192.168.1.107:8080" "http://10.0.2.2:8080" "http://80-78-241-166.cloudvps.regruhosting.ru:8081"


    private val client = OkHttpClient.Builder()
        .build()


    private val gsonBuilder = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()

    }

    val userService: com.where.to.go.data.dao.UserService by lazy {
        retrofit.create(com.where.to.go.data.dao.UserService::class.java)
    }
    val authService: com.where.to.go.data.dao.AuthService by lazy {
        retrofit.create(com.where.to.go.data.dao.AuthService::class.java)
    }
    val partyService: com.where.to.go.data.dao.PartyService by lazy {
        retrofit.create(com.where.to.go.data.dao.PartyService::class.java)
    }

}
*/
