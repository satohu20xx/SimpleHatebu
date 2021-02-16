package com.choilabo.simplehatebu.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.choilabo.simplehatebu.data.hatebu.dao.HatebuEntryDao
import com.choilabo.simplehatebu.data.hatebu.entity.HatebuEntry

/**
 * Created by sato_shinichiro on 2020/09/30
 */
@Database(
    entities = [
        HatebuEntry::class
    ],
    version = 1
)
abstract class SimpleHatebuDatabase : RoomDatabase() {

    abstract fun hatebuEntryDao(): HatebuEntryDao
}