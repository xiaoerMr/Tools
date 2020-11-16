package com.sai.utils.http

data class BaseResponse<out T> (val code :Int, val errorMsg:String, val data:T){
    val isSuccess  get() = code == 200
}