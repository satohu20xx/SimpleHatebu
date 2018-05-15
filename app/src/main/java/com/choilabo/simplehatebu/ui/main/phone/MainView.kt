package com.choilabo.simplehatebu.ui.main.phone

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.choilabo.simplehatebu.R
import com.choilabo.simplehatebu.data.bookmark.entity.Bookmark
import com.choilabo.simplehatebu.databinding.MainViewBinding
import com.choilabo.simplehatebu.ui.bookmark.BookmarkListAdapter
import com.choilabo.simplehatebu.ui.main.MainView
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
class MainView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle), MainView {

    private val binding by lazy {
        DataBindingUtil.inflate<MainViewBinding>(
                LayoutInflater.from(context),
                R.layout.main_view,
                this,
                true
        )
    }

    private val adapter: BookmarkListAdapter
    private val clickProcessor = PublishProcessor.create<Bookmark>()
    private val longClickProcessor = PublishProcessor.create<Bookmark>()

    init {
        adapter = BookmarkListAdapter(context)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { adapterView, view, i, l ->
            clickProcessor.onNext(adapter.getItem(i))
        }

        binding.listView.setOnItemLongClickListener { adapterView, view, i, l ->
            longClickProcessor.onNext(adapter.getItem(i))
            true
        }
    }

    override fun setData(bookmarks: List<Bookmark>) {
        adapter.setItems(bookmarks)
        adapter.notifyDataSetChanged()
    }

    override fun getShownData(): List<Bookmark> {
        val firstPosition = binding.listView.firstVisiblePosition
        return adapter.getItems().toMutableList().subList(0, firstPosition)
    }

    override fun observeClick(): Flowable<Bookmark> {
        return clickProcessor.onBackpressureDrop()
    }

    override fun observeLongClick(): Flowable<Bookmark> {
        return longClickProcessor.onBackpressureDrop()
    }
}