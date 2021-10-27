package com.choilabo.simplehatebu.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by sato_shinichiro on 2020/10/06
 */
@AndroidEntryPoint
class SettingFragment : Fragment() {

    private var composeView: ComposeView? = null

    private val viewModel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SettingComposable(isDarkMode = false, listener = viewModel)
            }
        }.also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.darkMode.observe(viewLifecycleOwner) {
            composeView?.setContent {
                SettingComposable(isDarkMode = it, listener = viewModel)
            }
        }
    }
}