package com.gatecaller.ui.existContact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gatecaller.Screen
import com.gatecaller.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExistContactFragment : Fragment() {

    private val viewModel: ExistContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                ExistContactScreen(viewModel) { event ->
                    when (event) {
                        is ExistContactEvent.OnItemClick -> {
                            viewModel.addToDatabase(event.contact)
                            navigate(Screen.Home, Screen.ExistContact)
                        }
                    }
                }
            }
        }
    }
}