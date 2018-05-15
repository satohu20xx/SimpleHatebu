package com.choilabo.simplehatebu.ui.bookmark

import android.content.Context
import android.databinding.DataBindingUtil
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.choilabo.simplehatebu.R
import com.choilabo.simplehatebu.data.bookmark.constant.BookmarkCategory
import com.choilabo.simplehatebu.data.bookmark.entity.Bookmark
import com.choilabo.simplehatebu.databinding.BookmarkItemBinding


/**
 * Created by sato_shinichiro on 2018/01/12.
 */
class BookmarkListAdapter(
        context: Context
) : ArrayAdapter<Bookmark>(context, 0) {

    private var bookmarks: List<Bookmark>? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = convertView?.let { it.tag as BookmarkItemBinding } ?: DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.bookmark_item,
                null,
                false
        )
        binding.root.tag = binding

        getItem(position).run {
            binding.title.setText(title)
            binding.description.setText(contentSnippet)
            if (faviconUrl.isEmpty()) {
                binding.favicon.visibility = View.GONE
            } else {
                Glide.with(context).load(faviconUrl).into(binding.favicon)
                binding.favicon.visibility = View.VISIBLE
            }
            if (thumbnailUrl.isEmpty()) {
                binding.thumbnail.visibility = View.GONE
            } else {
                Glide.with(context).load(thumbnailUrl).into(binding.thumbnail)
                binding.thumbnail.visibility = View.VISIBLE
            }
            binding.category.setText(category)
            binding.category.setTextColor(BookmarkCategory.findByTitle(category)?.colorResId ?: 0)
            Glide.with(context).load("https://b.hatena.ne.jp/entry/image/$link").into(binding.count)
            binding.publishData.setText(DateFormat.format("yyyy/MM/dd kk:mm", publishedData))
            binding.content.alpha = if (shown) 0.5f else 1f
        }

        return binding.root
    }

    fun setItems(bookmarks: List<Bookmark>) {
        clear()
        addAll(bookmarks)
        this.bookmarks = bookmarks
    }

    fun getItems(): List<Bookmark> {
        return this.bookmarks ?: emptyList()
    }
}