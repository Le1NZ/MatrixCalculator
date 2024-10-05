package ru.pokrovskii.matrixcalculator.viewmodel

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import ru.pokrovskii.matrixcalculator.state.MainScreenUiEffect
import ru.pokrovskii.matrixcalculator.state.OperationUiState
import ru.pokrovskii.matrixcalculator.state.OperationUiState.Companion.OPERATION_VARIANTS
import ru.pokrovskii.matrixcalculator.state.RequestUiState
import ru.pokrovskii.matrixcalculator.state.ResultUiState
import ru.pokrovskii.matrixcalculator.state.SIZE_VARIANTS

@Stable
internal interface MainScreenPresenter {

    val requestState: StateFlow<RequestUiState>
    val resultState: StateFlow<ResultUiState>
    val sizeVariants: List<Int>
    val operationVariants: List<OperationUiState>
    val currentSize: StateFlow<Int>
    val currentOperation: StateFlow<OperationUiState>
    val eventFlow: SharedFlow<MainScreenUiEffect>

    fun onSizeChanged(newSize: Int)
    fun onTypeChanged(newType: OperationUiState)
    fun onFirstMatrixChanged(positionX: Int, positionY: Int, newNumber: String)
    fun onSecondMatrixChanged(positionX: Int, positionY: Int, newNumber: String)
    fun onNumberChanged(newNumber: String)
    fun findResult()
}

internal class MainScreenPresenterImpl(
    private val viewModel: MainScreenViewModel,
) : MainScreenPresenter {

    override val requestState = viewModel.requestState
    override val resultState = viewModel.resultState
    override val sizeVariants = SIZE_VARIANTS
    override val operationVariants = OPERATION_VARIANTS
    override val currentSize = viewModel.size
    override val currentOperation = viewModel.operation
    override val eventFlow = viewModel.eventFlow

    override fun onSizeChanged(newSize: Int) {
        viewModel.onSizeChanged(newSize)
    }

    override fun onTypeChanged(newType: OperationUiState) {
        viewModel.onTypeChanged(newType)
    }

    override fun onFirstMatrixChanged(positionX: Int, positionY: Int, newNumber: String) {
        viewModel.onFirstMatrixChanged(positionX, positionY, newNumber)
    }

    override fun onSecondMatrixChanged(positionX: Int, positionY: Int, newNumber: String) {
        viewModel.onSecondMatrixChanged(positionX, positionY, newNumber)
    }

    override fun onNumberChanged(newNumber: String) {
        viewModel.onNumberChanged(newNumber)
    }

    override fun findResult() {
        viewModel.findClick()
    }
}