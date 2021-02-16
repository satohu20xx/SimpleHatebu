package com.choilabo.simplehatebu.ui.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.choilabo.simplehatebu.data.hatebu.entity.HatebuEntry
import com.choilabo.simplehatebu.databinding.HomeViewBinding
import com.choilabo.simplehatebu.ui.hatebu.HatebuEntryLineAdapter

/**
 * Created by sato_shinichiro on 2020/09/29
 */
class HomeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val entryAdapter = HatebuEntryLineAdapter()

    private val binding = HomeViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    ).apply {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = entryAdapter
        }
    }

    fun setListener(listener: Listener?) {
        entryAdapter.listener = listener
    }

    suspend fun setHatebuEntries(hatebuEntries: PagingData<HatebuEntry>) {
        entryAdapter.submitData(hatebuEntries)
    }

    interface Listener : HatebuEntryLineAdapter.Listener
}