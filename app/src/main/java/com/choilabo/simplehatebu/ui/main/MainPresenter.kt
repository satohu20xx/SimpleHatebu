package com.choilabo.simplehatebu.ui.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.choilabo.simplehatebu.data.bookmark.entity.Bookmark
import com.choilabo.simplehatebu.ui.common.LifecycleDisposable
import io.reactivex.Flowable
import timber.log.Timber


/**
 * Created by sato_shinichiro on 2018/01/12.
 */
class MainPresenter(
        private val viewModel: MainViewModel,
        private val mainView: MainView,
        private val lifecycleDisposable: LifecycleDisposable
) : LifecycleObserver {

    private var shownData: List<Bookmark>? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchBookmark() {
        viewModel.fetchBookmark()
                .doOnError { Timber.e(it) }
                .onErrorComplete()
                .subscribe()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun updateShownIds() {
        shownData = mainView.getShownData()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun setShownData() {
        shownData?.let {
            viewModel.setShownBookmarkByIds(it)
                    .doOnError { Timber.e(it) }
                    .onErrorComplete()
                    .subscribe()
        }
    }

    fun observeClick(): Flowable<Bookmark> {
        return mainView.observeClick()
    }

    fun observeLongClick(): Flowable<Bookmark> {
        return mainView.observeLongClick()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun observeBookmark() {
        viewModel.observeUnreadBookmark()
                .subscribe({
                    mainView.setData(it)
                }, Timber::e)
                .run { lifecycleDisposable.disposeOnPause(this) }
    }
}