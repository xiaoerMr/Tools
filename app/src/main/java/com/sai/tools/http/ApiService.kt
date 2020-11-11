package com.sai.tools.http

import com.sai.utils.http.BaseResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {


    @POST("/user/login")
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") passWord: String
    ): BaseResponse<ResUser>

}