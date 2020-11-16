package com.sai.tools.http

import android.text.TextUtils
import com.sai.utils.http.RequestCallback
import com.sai.utils.http.RetrofitManger
import com.sai.utils.loge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation

class HttpRequest {
    // 请求地址
    private val defBaseUrl = "http://39.97.235.83:8089/"
    private val defApiService: ApiService by lazy {
        val retrofitManger = RetrofitManger()
        retrofitManger.setBaseUrl(defBaseUrl)
        val defApiService = retrofitManger.getSingletonService(ApiService::class.java)
        defApiService
    }

    suspend fun getAllTask(callback: RequestCallback<ResTask>) {
        coroutineScope {

                try {
                    val allTasks = defApiService.allTasks()

                    when (allTasks.code) {
                        200 -> callback.onSuccess(allTasks.data)

                        404 -> callback.onError("页面没找到")
                        500 -> callback.onError("服务器拒绝")
                        else -> callback.onError(allTasks.errorMsg)
                    }
                } catch (e: Exception) {
                    if (TextUtils.isEmpty(e.message)) callback.onError("访问错误")
                    else callback.onError(e.message!!)
                }

        }
    }

}