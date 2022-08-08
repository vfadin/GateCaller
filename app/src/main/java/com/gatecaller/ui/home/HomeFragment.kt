package com.gatecaller.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.ComposeView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gatecaller.Screen
import com.gatecaller.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var isPermissionGranted = false
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                isPermissionGranted = isGranted
            }
        }
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
                        is HomeScreenEvent.OnNewContactClick -> {
                            navigate(Screen.NewContact, Screen.Home)
                        }
                        is HomeScreenEvent.OnItemClick -> {
                            makeCall(event.number)
                        }
                        is HomeScreenEvent.OnDeleteClick -> {
                            viewModel.deleteFromDatabaseById(event.id)
                        }
                        is HomeScreenEvent.OnExistContactClick -> {
                            if (checkPermissions()) {
                                navigate(Screen.ExistContact, Screen.Home)
                            }
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

    private fun checkPermissions(): Boolean {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        } else {
            requestPermissionLauncher.launch(
                Manifest.permission.READ_CONTACTS
            )
        }
        return isPermissionGranted
    }
}
