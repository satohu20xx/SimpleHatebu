package com.choilabo.simplehatebu.data.bookmark.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
open class Bookmark(
        @PrimaryKey
        var link: String = "",
        var title: String = "",
        var contentSnippet: String = "",
        var category: String = "",
        var publishedData: Long = 0L,
        var faviconUrl: String = "",
        var thumbnailUrl: String = "",
        var hotentry: Boolean = false,
        var shown: Boolean = false,
        var deleted: Boolean = false,
        var createdAt: Long = 0L
) : RealmObject()