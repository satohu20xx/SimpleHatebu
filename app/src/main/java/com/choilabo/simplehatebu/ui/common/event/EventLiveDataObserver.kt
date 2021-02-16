package com.choilabo.simplehatebu.ui.common.event

import androidx.lifecycle.Observer

/**
 * Created by sato_shinichiro on 2020/08/28
 */
class EventLiveDataObserver<T : Any>(
    private val onEventUnhandledContent: (T) -> Unit
) : Observer<Event<T>> {

    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}
