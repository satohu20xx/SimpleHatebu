package com.choilabo.simplehatebu.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.choilabo.simplehatebu.R
import com.choilabo.simplehatebu.databinding.HomeFragmentBinding
import com.choilabo.simplehatebu.ui.common.event.EventLiveDataObserver
import com.wada811.databinding.dataBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * Created by sato_shinichiro on 2020/09/29
 */
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModels()

    private val binding: HomeFragmentBinding by dataBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel.navUrl.observe(
            viewLifecycleOwner,
            EventLiveDataObserver {
                startActivity(Intent(Intent.ACTION_VIEW, it.toUri()))
            }
        )

        binding.content.setListener(viewModel)

        lifecycleScope.launchWhenStarted {
            viewModel.hatebuEntries.collectLatest {
                binding.content.setHatebuEntries(it)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSetting -> {
                HomeFragmentDirections.navigateToSetting()
                    .also { findNavController().navigate(it) }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}