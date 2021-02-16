package com.choilabo.simplehatebu.ui.hatebu

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.choilabo.simplehatebu.data.hatebu.entity.HatebuEntry

/**
 * Created by sato_shinichiro on 2020/10/01
 */
class HatebuEntryLineAdapter(
) : PagingDataAdapter<HatebuEntry, HatebuEntryLineDataBinder>(diffCallback) {

    var listener: Listener? = null

    override fun onBindViewHolder(holder: HatebuEntryLineDataBinder, position: Int) {
        val item = getItem(position) ?: return
        holder.bindTo(item, object : HatebuEntryLineDataBinder.Listener {
            override val onRootClicked = View.OnClickListener {
                listener?.onItemClicked(item.link)
            }

            override val onRootLongClicked = View.OnLongClickListener {
                listener?.onItemLongClicked(item.link)
                true
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HatebuEntryLineDataBinder =
        HatebuEntryLineDataBinder(parent)

    private companion object {

        val diffCallback = object : DiffUtil.ItemCallback<HatebuEntry>() {
            override fun areItemsTheSame(oldItem: HatebuEntry, newItem: HatebuEntry): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: HatebuEntry, newItem: HatebuEntry): Boolean =
                oldItem.link == newItem.link
        }
    }

    interface Listener {

        fun onItemClicked(link: String)

        fun onItemLongClicked(link: String)
    }
}