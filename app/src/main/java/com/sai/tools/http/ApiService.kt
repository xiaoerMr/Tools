package com.sai.tools.http

import com.sai.utils.http.BaseResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*


interface ApiService {


    @POST("/user/login")
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") passWord: String
    ): BaseResponse<ResUser>

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun create(@Body user: ResUser)

    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): BaseResponse<ResUser>

    // http://127.0.0.1:8089/api/web/policeTask/findAppByConditions?page=1&size=2&taskState=0&receiver=wc12345
    @GET("api/web/policeTask/findAppByConditions?page=1&size=10&taskState=0&receiver=wc12345")
    suspend fun allTasks(): BaseResponse<ResTask>

    @GET("api/web/policeTask/findAppByConditions?page=1&size=10&taskState=0&receiver=wc12345")
     fun allTasksFlow(): BaseResponse<ResTask>

}