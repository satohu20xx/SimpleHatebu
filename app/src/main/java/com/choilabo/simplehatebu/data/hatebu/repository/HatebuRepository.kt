package com.choilabo.simplehatebu.data.hatebu.repository

import androidx.paging.PagingSource
import com.choilabo.simplehatebu.data.base.SimpleHatebuDatabase
import com.choilabo.simplehatebu.data.hatebu.entity.HatebuEntry
import dagger.Reusable
import javax.inject.Inject

/**
 * Created by sato_shinichiro on 2020/09/30
 */
@Reusable
class HatebuRepository @Inject constructor(
    private val db: SimpleHatebuDatabase
) {

    fun getAll(): PagingSource<Int, HatebuEntry> {
        return db.hatebuEntryDao().getAll()
    }

    fun insertIfNeeded(entries: List<HatebuEntry>) {
        db.runInTransaction {
            db.hatebuEntryDao().insertIfNotExists(entries)
        }
    }

    fun markReadByLinks(links: List<String>) {
        db.runInTransaction {
            db.hatebuEntryDao().markReadByLinks(links)
        }
    }
}