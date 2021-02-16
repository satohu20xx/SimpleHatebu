package com.choilabo.simplehatebu.ui.setting

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.ObservableBoolean
import com.choilabo.simplehatebu.databinding.SettingViewBinding

/**
 * Created by sato_shinichiro on 2020/10/06
 */
class SettingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = SettingViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    ).apply {
        viewData = ViewData()
        darkMode.setOnCheckedChangeListener { v, isDarkMode ->
            if (v.isPressed) {
                listener?.onDarkModeCheckedChanged(isDarkMode)
            }
        }
    }

    fun setListener(listener: Listener?) {
        binding.listener = listener
    }

    fun setDarkMode(isDarkMode: Boolean) {
        binding.viewData?.setDarkMode(isDarkMode)
        binding.executePendingBindings()
    }

    class ViewData {

        val darkMode = ObservableBoolean()

        fun setDarkMode(isDarkMode: Boolean) {
            darkMode.set(isDarkMode)
        }
    }

    interface Listener {

        fun onDarkModeCheckedChanged(isDarkMode: Boolean)
    }
}