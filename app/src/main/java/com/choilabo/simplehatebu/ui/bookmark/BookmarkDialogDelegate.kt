package com.choilabo.simplehatebu.ui.bookmark

import android.app.Activity
import android.content.Intent
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by sato_shinichiro on 2018/03/29.
 */
@Singleton
class BookmarkDialogDelegate @Inject constructor(
) {

    fun show(activity: Activity, link: String) {
        BookmarkDialog(activity)
                .onClick { onDialogClicked(it, activity, link) }
                .show()
    }

    private fun onDialogClicked(item: BookmarkDialog.Item, activity: Activity, link: String) {
        when (item) {
            BookmarkDialog.Item.BOOKMARK -> moveToHatenaApp(activity, link, "com.hatena.android.bookmark.PostActivity")
            BookmarkDialog.Item.COMMENT -> moveToHatenaApp(activity, link, "com.hatena.android.bookmark.CommentActivityForExternalIntent")
            BookmarkDialog.Item.FILTER -> {
            }
        }
    }

    private fun moveToHatenaApp(activity: Activity, link: String, className: String) {
        Intent(Intent.ACTION_SEND).also {
            it.setClassName("com.hatena.android.bookmark", className)
            it.setType("text/plain")
            it.putExtra(Intent.EXTRA_TEXT, link)
            activity.startActivity(it)
        }
    }
}