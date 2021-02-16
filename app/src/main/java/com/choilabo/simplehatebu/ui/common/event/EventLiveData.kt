package com.choilabo.simplehatebu.ui.common.event

import android.os.Looper
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by sato_shinichiro on 2020/08/28
 */
class EventLiveData<T : Any> : MutableLiveData<Event<T>>() {

    private val isPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in Event<T>>) {
        if (hasActiveObservers()) {
            throw IllegalStateException("Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner, Observer<Event<T>> {
            if (isPending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(value: Event<T>?) {
        isPending.set(true)
        super.setValue(value)
    }

    fun emitEvent(value: T) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            setValue(Event(value))
        } else {
            postValue(Event(value))
        }
    }
}