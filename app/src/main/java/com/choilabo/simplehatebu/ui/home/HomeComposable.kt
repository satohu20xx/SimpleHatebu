package com.choilabo.simplehatebu.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.choilabo.simplehatebu.data.hatebu.entity.HatebuEntry
import com.choilabo.simplehatebu.ui.hatebu.HatebuEntryLineComposable
import com.choilabo.simplehatebu.ui.hatebu.HatebuEntryLineComposableListener
import kotlinx.coroutines.flow.Flow

/**
 * Created by sato_shinichiro on 2021/10/11.
 */
@ExperimentalFoundationApi
@Composable
fun HomeComposable(
    hatebuEntries: Flow<PagingData<HatebuEntry>>,
    listener: HomeComposableListener
) {
    val lazyItems = hatebuEntries.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyItems) {
            HatebuEntryLineComposable(hatebuEntry = it, listener = listener)
        }
    }
}

interface HomeComposableListener : HatebuEntryLineComposableListener