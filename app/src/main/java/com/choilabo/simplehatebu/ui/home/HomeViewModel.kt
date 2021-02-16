package com.choilabo.simplehatebu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.choilabo.simplehatebu.background.hatebu.HatebuWorkerController
import com.choilabo.simplehatebu.data.hatebu.HatebuDataModel
import com.choilabo.simplehatebu.ui.common.event.EventLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by sato_shinichiro on 2020/09/29
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    hatebuWorkerController: HatebuWorkerController,
    private val hatebuDataModel: HatebuDataModel
) : ViewModel(), HomeView.Listener {

    val navUrl = EventLiveData<String>()

    private var readLinks = hashSetOf<String>()

    init {
        hatebuWorkerController.startLoadWorker()
    }

    val hatebuEntries = Pager(
        PagingConfig(pageSize = 20)
    ) {
        hatebuDataModel.getAll()
    }.flow

    override fun onItemClicked(link: String) {
        readLinks.add(link)
        navUrl.emitEvent(link)
    }

    override fun onItemLongClicked(link: String) {
        readLinks.add(link)
        navUrl.emitEvent("https://b.hatena.ne.jp/entry/s/${link}")
    }

    fun onStop() {
        if (readLinks.isEmpty()) {
            return
        }

        viewModelScope.launch {
            hatebuDataModel.markReadByLinks(readLinks.toList())
        }
    }
}