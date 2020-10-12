package com.sai.utils.event

import androidx.lifecycle.MutableLiveData

object LiveDataBus {

    private val bus = HashMap<String, BusMutableLiveData<Any>>()

    fun <T> with(key: String): MutableLiveData<T> {
        if (!bus.containsKey(key)) {
            bus[key] = BusMutableLiveData()
        }
        return bus[key] as MutableLiveData<T>
    }
}

