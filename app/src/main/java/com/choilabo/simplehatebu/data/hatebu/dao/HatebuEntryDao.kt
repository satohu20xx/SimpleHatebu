package com.choilabo.simplehatebu.data.hatebu.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.choilabo.simplehatebu.data.hatebu.entity.HatebuEntry

/**
 * Created by sato_shinichiro on 2020/09/30
 */
@Dao
interface HatebuEntryDao {

    @Query("SELECT * FROM hatebu_entry ORDER BY updatedAt DESC")
    fun getAll(): PagingSource<Int, HatebuEntry>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIfNotExists(entries: List<HatebuEntry>)

    @Query("UPDATE hatebu_entry SET isRead = 1 WHERE link IN (:links)")
    fun markReadByLinks(links: List<String>)
}