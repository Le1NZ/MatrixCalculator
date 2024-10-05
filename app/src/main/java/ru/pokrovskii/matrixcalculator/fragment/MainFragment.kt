package ru.pokrovskii.matrixcalculator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import ru.pokrovskii.matrixcalculator.R
import ru.pokrovskii.matrixcalculator.composables.MainScreen
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenPresenter
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenPresenterImpl
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenViewModel

class MainFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MainScreen(
                    presenter = remember {
                        MainScreenPresenterImpl(
                            viewModel = viewModel,
                        )
                    }
                )
            }
        }
    }
}