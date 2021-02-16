package com.choilabo.simplehatebu.data.hatebu.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by sato_shinichiro on 2020/09/30
 */
@Entity(tableName = "hatebu_entry")
data class HatebuEntry(
    @PrimaryKey @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "updatedAt") val updatedAt: Long,
    @ColumnInfo(name = "isRead") val isRead: Boolean
)