package com.sai.tools.http

import com.sai.utils.http.RetrofitManger

class HttpRequest {
    // 请求地址
    private val defBaseUrl = ""
    private val defApiService: ApiService

    init {
        val retrofitManger = RetrofitManger()
        retrofitManger.setBaseUrl(defBaseUrl)
        defApiService = retrofitManger.getSingletonService(ApiService::class.java)
    }

    suspend fun getLogin() {

        val login = defApiService.login("", "")
        if (login.code == 200) {
            val data = login.data
            data.name
        } else {
            login.errorMsg
        }
    }
}