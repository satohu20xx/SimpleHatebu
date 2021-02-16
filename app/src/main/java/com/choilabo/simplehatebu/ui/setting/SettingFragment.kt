package com.choilabo.simplehatebu.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.choilabo.simplehatebu.R
import com.choilabo.simplehatebu.databinding.SettingFragmentBinding
import com.wada811.databinding.dataBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by sato_shinichiro on 2020/10/06
 */
@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.setting_fragment) {

    private val viewModel: SettingViewModel by viewModels()

    private val binding: SettingFragmentBinding by dataBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}