package com.choilabo.simplehatebu.ui.bookmark

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface


/**
 * Created by sato_shinichiro on 2018/03/29.
 */
class BookmarkDialog(private val context: Context) {

    enum class Item {
        BOOKMARK,
        COMMENT,
        FILTER
    }

    private var onClickListener: ((BookmarkDialog.Item) -> Unit)? = null

    fun onClick(onClickListener: (BookmarkDialog.Item) -> Unit): BookmarkDialog {
        this.onClickListener = onClickListener
        return this
    }

    fun show() {
        AlertDialog.Builder(context)
                .setItems(Item.values().map { it.name }.toTypedArray(), DialogInterface.OnClickListener { dialog, index ->
                    this.onClickListener?.invoke(Item.values().get(index))
                })
                .show()
    }
}