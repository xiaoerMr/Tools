package com.sai.utils.http

data class BaseResponse<T> (val code :Int, val errorMsg:String, val data:T)