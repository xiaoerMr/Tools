package com.sai.utils.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManger {

    private lateinit var retrofit: Retrofit

    fun setBaseUrl(baseUrl: String): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }


    fun <T> getSingletonService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}