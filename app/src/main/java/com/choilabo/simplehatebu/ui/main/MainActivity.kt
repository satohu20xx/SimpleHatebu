package com.choilabo.simplehatebu.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import com.choilabo.simplehatebu.R
import com.choilabo.simplehatebu.databinding.MainActivityBinding
import com.choilabo.simplehatebu.di.key.ViewModelKey
import com.choilabo.simplehatebu.ui.ViewModelFactory
import com.choilabo.simplehatebu.ui.bookmark.BookmarkDialogDelegate
import com.choilabo.simplehatebu.ui.common.LifecycleDisposable
import dagger.Binds
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.multibindings.IntoMap
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var bookmarkDialogDelegate: BookmarkDialogDelegate

    private lateinit var presenter: MainPresenter
    private val lifecycleDisposable = LifecycleDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
        presenter = MainPresenter(
                ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java),
                binding.content,
                lifecycleDisposable
        )
        lifecycle.addObserver(presenter)
        lifecycle.addObserver(lifecycleDisposable)
    }

    override fun onResume() {
        super.onResume()
        listenToClick()
        listenToLongClick()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun listenToClick() {
        presenter.observeClick()
                .map { Intent(Intent.ACTION_VIEW, Uri.parse(it.link)) }
                .subscribe({
                    startActivity(it)
                })
                .run { lifecycleDisposable.disposeOnPause(this) }
    }

    private fun listenToLongClick() {
        presenter.observeLongClick()
                .subscribe({
                    bookmarkDialogDelegate.show(this, it.link)
                })
                .run { lifecycleDisposable.disposeOnPause(this) }
    }

    @dagger.Module
    abstract class Module {

        @Binds
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        abstract fun bindViewModel(mainViewModel: MainViewModel): ViewModel

        @ContributesAndroidInjector
        abstract fun contributesAndroidInjector(): MainActivity
    }
}
