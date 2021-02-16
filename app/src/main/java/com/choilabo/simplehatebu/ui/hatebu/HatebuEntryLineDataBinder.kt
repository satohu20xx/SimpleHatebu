package com.choilabo.simplehatebu.ui.hatebu

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import com.choilabo.simplehatebu.R
import com.choilabo.simplehatebu.data.hatebu.entity.HatebuEntry
import com.choilabo.simplehatebu.databinding.HatebuEntryLineViewBinding

/**
 * Created by sato_shinichiro on 2020/09/30
 */
class HatebuEntryLineDataBinder(
    private val binding: HatebuEntryLineViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        HatebuEntryLineViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            viewData = ViewData()
        }
    )

    var link: String? = null

    fun bindTo(hatebuEntry: HatebuEntry?, listener: Listener?) {
        link = hatebuEntry?.link
        binding.listener = listener
        binding.viewData?.set(hatebuEntry)
        binding.executePendingBindings()
    }

    class ViewData {

        val background = ObservableInt()
        val title = ObservableField<String>()
        val description = ObservableField<String>()
        val imageUrl = ObservableField<String>()
        val faviconUrl = ObservableField<String>()
        val host = ObservableField<String>()
        val date = ObservableField<String>()

        fun set(hatebuEntry: HatebuEntry?) {
            if (hatebuEntry?.isRead ?: false) {
                background.set(R.color.disable)
            } else {
                background.set(R.color.transparent)
            }
            title.set(hatebuEntry?.title)
            description.set(hatebuEntry?.description)
            imageUrl.set(hatebuEntry?.imageUrl)
            faviconUrl.set(hatebuEntry?.link?.let { "https://cdn-ak2.favicon.st-hatena.com/?url=$it" })
            host.set(hatebuEntry?.link?.let { Uri.parse(it).host })
            date.set(hatebuEntry?.date)
        }
    }

    interface Listener {

        val onRootClicked: View.OnClickListener?

        val onRootLongClicked: View.OnLongClickListener
    }
}