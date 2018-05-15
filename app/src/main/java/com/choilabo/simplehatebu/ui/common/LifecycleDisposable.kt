package com.choilabo.simplehatebu.ui.common

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by sato_shinichiro on 2018/03/27.
 */
class LifecycleDisposable : LifecycleObserver {

    private var onCreateDisposable: CompositeDisposable? = null
    private var onResumeDisposable: CompositeDisposable? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        onCreateDisposable?.clear()
        onCreateDisposable = CompositeDisposable()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        onResumeDisposable?.clear()
        onResumeDisposable = CompositeDisposable()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        onResumeDisposable?.clear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        onCreateDisposable?.clear()
    }

    fun disposeOnPause(disposable: Disposable) {
        onResumeDisposable?.add(disposable)
    }

    fun disposeOnDestroy(disposable: Disposable) {
        onCreateDisposable?.add(disposable)
    }
}