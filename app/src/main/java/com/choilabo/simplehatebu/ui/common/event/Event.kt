package com.choilabo.simplehatebu.ui.common.event

/**
 * Created by sato_shinichiro on 2020/08/28
 */

class Event<out T : Any>(private val content: T) {

    var hasBeenHandled: Boolean = false
        private set

    /**
     * @return the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? =
        if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }

    /**
     * @return the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
