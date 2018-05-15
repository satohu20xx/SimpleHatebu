package com.choilabo.simplehatebu.data.linkfilter.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
open class LinkFilter(
        @PrimaryKey
        var startWith: String = ""
) : RealmObject()