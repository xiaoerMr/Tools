package com.sai.utils.event

import androidx.lifecycle.Observer

class ObserverWrapper<T> constructor(val observer: Observer< T>) : Observer<T> {


    override fun onChanged(t: T) {

        observer.let {
            if (isCallOnObserve()) {
                it.onChanged(t)
            }
        }
    }

    private fun isCallOnObserve(): Boolean {
        val stackTrace = Thread.currentThread().stackTrace

        if (stackTrace != null && stackTrace.isNotEmpty()) {
            for (element in stackTrace) {
                if ("androidx.lifecycle.LiveData" == element.className && "observeForever" == element.methodName) {
//                if ("android.arch.lifecycle.LiveData" == element.className && "observeForever" == element.methodName) {
                    return true
                }
            }
        }
        return false
    }

}