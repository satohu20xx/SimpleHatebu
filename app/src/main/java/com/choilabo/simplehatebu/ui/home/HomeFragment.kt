package com.choilabo.simplehatebu.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.ComposeView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.choilabo.simplehatebu.R
import com.choilabo.simplehatebu.ui.common.MainTheme
import com.choilabo.simplehatebu.ui.common.event.EventLiveDataObserver
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by sato_shinichiro on 2020/09/29
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    @ExperimentalFoundationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MainTheme {
                    HomeComposable(
                        hatebuEntries = viewModel.hatebuEntries,
                        listener = viewModel
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel.navUrl.observe(
            viewLifecycleOwner,
            EventLiveDataObserver {
                startActivity(Intent(Intent.ACTION_VIEW, it.toUri()))
            }
        )
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