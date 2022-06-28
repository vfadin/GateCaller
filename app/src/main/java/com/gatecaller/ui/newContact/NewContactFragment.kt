package com.gatecaller.ui.newContact

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
import javax.inject.Inject

@AndroidEntryPoint
class NewContactFragment : Fragment() {

    private val viewModel : NewContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.validationEvents.collect {
                when(it) {
                    ValidationEvent.Success -> {
                        navigate(Screen.Home, Screen.NewContact)
                    }
                }
            }
        }
        return ComposeView(requireContext()).apply {
            setContent {
                NewContactScreen(viewModel)
            }
        }
    }
}