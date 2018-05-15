package com.choilabo.simplehatebu.data.bookmark.constant

import com.choilabo.simplehatebu.R
import java.util.*


/**
 * Created by sato_shinichiro on 2018/01/11.
 */
enum class BookmarkCategory(
        val title: String,
        val colorResId: Int
) {
    Hotentry("全て", 0),
    Social("世の中", R.color.categorySocial),
    Economics("世の中", R.color.categoryEconomics),
    Life("暮らし", R.color.categoryLife),
    Knowledge("学び", R.color.categoryKnowledge),
    It("テクノロジー", R.color.categoryIt),
    Fun("おもしろ", R.color.categoryFun),
    Entertainment("エンタメ", R.color.categoryEntertainment),
    Game("アニメとゲーム", R.color.categoryGame);

    companion object {
        fun findByTitle(title: String): BookmarkCategory? {
            return values().find { it.title == title }
        }
    }

    fun getKey(): String {
        return this.name.toLowerCase(Locale.ENGLISH)
    }
}