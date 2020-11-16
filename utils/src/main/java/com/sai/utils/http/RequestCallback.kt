package com.sai.utils.http

interface RequestCallback<T> {
    fun onSuccess(data:T)
    fun onError(errorMsg:String)
}