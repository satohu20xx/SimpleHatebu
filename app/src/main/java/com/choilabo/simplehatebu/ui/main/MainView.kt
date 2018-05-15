package com.choilabo.simplehatebu.ui.main

import com.choilabo.simplehatebu.data.bookmark.entity.Bookmark
import io.reactivex.Flowable


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
interface MainView {

    fun setData(bookmarks: List<Bookmark>)

    fun getShownData(): List<Bookmark>

    fun observeClick(): Flowable<Bookmark>

    fun observeLongClick(): Flowable<Bookmark>
}