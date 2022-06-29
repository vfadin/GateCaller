package com.gatecaller.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gatecaller.Screen
import com.gatecaller.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreen(
                    viewModel
                ) { event ->
                    when (event) {
                        is HomeScreenEvent.AddButtonClick -> {
                            navigate(Screen.NewContact, Screen.Home)
                        }
                        is HomeScreenEvent.OnItemClick -> {
                            makeCall(event.number)
                        }
                        is HomeScreenEvent.OnItemLongClick -> {
                            viewModel.deleteFromDatabaseById(event.id)
                        }
                    }
                }
            }
        }
    }

    private fun makeCall(number: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(intent)
    }
}