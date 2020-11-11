package com.sai.utils.http

import retrofit2.Retrofit

class RetrofitManger {

    private lateinit var retrofit: Retrofit

    fun setBaseUrl(baseUrl: String): Retrofit {
        retrofit = Retrofit.Builder().baseUrl(baseUrl).build()
        return retrofit
    }


    fun <T> getSingletonService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }


}