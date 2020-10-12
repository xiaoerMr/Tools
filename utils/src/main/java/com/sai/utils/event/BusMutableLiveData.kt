package com.sai.utils.event

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.NullPointerException
import java.lang.reflect.Field

class BusMutableLiveData<T> : MutableLiveData<T>() {
    private val observerMap: MutableMap<Observer<in T>, Observer<in T>> = HashMap()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        try {
            hook(observer)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun observeForever(observer: Observer<in T>) {
        if (!observerMap.containsKey(observer)) {
            observerMap[observer] = ObserverWrapper(observer)
        }
        val get: Observer<in T> = observerMap[observer]!!
        super.observeForever(get)

    }

    override fun removeObserver(observer: Observer<in T>) {

        if (observerMap.contains(observer)) {
            val remove: Observer<in T> = observerMap.remove(observer)!!
            super.removeObserver(remove)
        } else {
            super.removeObserver(observer)
        }
    }

    private fun hook(observer: Observer<in T>) {

        val classLiveData = LiveData::class.java
        val fieldObservers: Field = classLiveData.getDeclaredField("mObservers")
        fieldObservers.isAccessible = true
        val objectObservers: Any = fieldObservers.get(this)
        val classObservers: Class<Any> = objectObservers.javaClass
        val getMethod = classObservers.getDeclaredMethod("get", Any::class.java)
        getMethod.isAccessible = true
        val objectWrapperEntry = getMethod.invoke(objectObservers, observer)
        var objectWrapper: Any? = null
        if (objectWrapperEntry is Map.Entry<*, *>) {
            objectWrapper = objectWrapperEntry.value
        }
        if (objectWrapper == null) {
            throw  NullPointerException("wrapper can not be bull")
        }
        val classObserverWrapper = objectWrapper.javaClass.superclass

        val fieldLastVersion = classObserverWrapper!!.getDeclaredField("mLastVersion")
        fieldLastVersion.isAccessible =true

        val fieldVersion = classLiveData.getDeclaredField("mVersion")
        fieldVersion.isAccessible =true
        val objectVersion = fieldVersion.get(this)
        fieldLastVersion.set(objectWrapper,objectVersion)
    }

}