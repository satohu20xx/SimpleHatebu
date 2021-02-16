package com.choilabo.simplehatebu.data.hatebu

import androidx.paging.PagingSource
import com.choilabo.simplehatebu.data.hatebu.converter.HatebuEntryConverter
import com.choilabo.simplehatebu.data.hatebu.entity.HatebuEntry
import com.choilabo.simplehatebu.data.hatebu.remote.HatebuApi
import com.choilabo.simplehatebu.data.hatebu.repository.HatebuRepository
import dagger.Reusable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by sato_shinichiro on 2020/09/29
 */
@Reusable
class HatebuDataModel @Inject constructor(
    private val hatebuApi: HatebuApi,
    private val hatebuEntryConverter: HatebuEntryConverter,
    private val hatebuRepository: HatebuRepository
) {

    fun getAll(): PagingSource<Int, HatebuEntry> {
        return hatebuRepository.getAll()
    }

    suspend fun load() {
        withContext(Dispatchers.IO) {
            hatebuApi.getHotentory()
                .let { hatebuEntryConverter.from(it) }
                .also { hatebuRepository.insertIfNeeded(it) }
        }
    }

    suspend fun markReadByLinks(links: List<String>) {
        withContext(Dispatchers.IO) {
            hatebuRepository.markReadByLinks(links)
        }
    }
}