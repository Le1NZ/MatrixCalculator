package ru.pokrovskii.matrixcalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.pokrovskii.matrixcalculator.converter.ToResultUiState
import ru.pokrovskii.matrixcalculator.createMatrixBySize
import ru.pokrovskii.matrixcalculator.domain.AddCenter
import ru.pokrovskii.matrixcalculator.domain.DeterminantCenter
import ru.pokrovskii.matrixcalculator.domain.MultiplyCenter
import ru.pokrovskii.matrixcalculator.domain.MultiplyNumberCenter
import ru.pokrovskii.matrixcalculator.modifyMatrix
import ru.pokrovskii.matrixcalculator.state.MainScreenUiEffect
import ru.pokrovskii.matrixcalculator.model.OperationResult
import ru.pokrovskii.matrixcalculator.state.OperationUiState
import ru.pokrovskii.matrixcalculator.state.RequestUiState
import ru.pokrovskii.matrixcalculator.state.ResultUiState
import ru.pokrovskii.matrixcalculator.state.SIZE_VARIANTS

internal class MainScreenViewModel(
    private val multiplyNumberCenter: MultiplyNumberCenter = MultiplyNumberCenter(),
    private val addCenter: AddCenter = AddCenter(),
    private val multiplyCenter: MultiplyCenter = MultiplyCenter(),
    private val determinantCenter: DeterminantCenter = DeterminantCenter(),
) : ViewModel() {

    private val _size = MutableStateFlow(SIZE_VARIANTS.first())
    val size = _size.asStateFlow()
    private val _operation = MutableStateFlow(OperationUiState.OPERATION_VARIANTS.first())
    val operation = _operation.asStateFlow()

    private val _requestState = MutableStateFlow(resolveRequestState(size.value, operation.value))
    val requestState = _requestState.asStateFlow()

    private val _resultState = MutableStateFlow<ResultUiState>(ResultUiState.None)
    val resultState = _resultState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<MainScreenUiEffect>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onSizeChanged(newSize: Int) {
        _size.value = newSize
        _requestState.value = resolveRequestState(
            size = size.value,
            operation = operation.value,
        )
    }

    fun onTypeChanged(newType: OperationUiState) {
        _operation.value = newType
        _requestState.value = resolveRequestState(
            size = size.value,
            operation = operation.value,
        )
    }

    fun onFirstMatrixChanged(positionX: Int, positionY: Int, newNumber: String) {
        _requestState.update { currentState ->
            val newMatrix = modifyMatrix(
                originalMatrix = currentState.matrix,
                positionX = positionX,
                positionY = positionY,
                newValue = newNumber,
            )
            when (currentState) {
                is RequestUiState.Matrix -> currentState.copy(
                    matrix = newMatrix,
                )
                is RequestUiState.TwoMatrix -> currentState.copy(
                    matrix = newMatrix,
                )
                is RequestUiState.MatrixAndTextField -> currentState.copy(
                    matrix = newMatrix,
                )
            }
        }
    }

    fun onSecondMatrixChanged(positionX: Int, positionY: Int, newNumber: String) {
        _requestState.update { currentState ->
            if (currentState is RequestUiState.TwoMatrix) {
                val newMatrix = modifyMatrix(
                    originalMatrix = currentState.secondMatrix,
                    positionX = positionX,
                    positionY = positionY,
                    newValue = newNumber,
                )
                currentState.copy(
                    secondMatrix = newMatrix,
                )
            } else {
                currentState
            }
        }
    }

    fun onNumberChanged(newNumber: String) {
        _requestState.update { currentState ->
            if (currentState is RequestUiState.MatrixAndTextField) {
                currentState.copy(
                    number = newNumber,
                )
            } else {
                currentState
            }
        }
    }

    fun findClick() {
        viewModelScope.launch {
            val result = when (val request = requestState.value) {
                is RequestUiState.MatrixAndTextField -> matrixAndTextFieldClick(request)
                is RequestUiState.TwoMatrix -> twoMatrixClick(request)
                is RequestUiState.Matrix -> matrixClick(request)
            }

            if (result is OperationResult.Error) {
                _eventFlow.emit(MainScreenUiEffect.SomethingWentWrong)
                _resultState.value = ResultUiState.None
            } else {
                _resultState.value = ToResultUiState.convert(result)
            }
        }
    }

    private suspend fun matrixAndTextFieldClick(request: RequestUiState.MatrixAndTextField): OperationResult {
        return if (operation.value is OperationUiState.MultiplyNumber) {
            multiplyNumberCenter.multiplyNumber(
                matrix = request.matrix,
                number = request.number,
            )
        } else {
            OperationResult.Error
        }
    }

    private suspend fun twoMatrixClick(request: RequestUiState.TwoMatrix): OperationResult {
        return when (operation.value) {
            is OperationUiState.Add -> addCenter.addMatrix(
                matrix = request.matrix,
                secondMatrix = request.secondMatrix,
            )
            is OperationUiState.Multiply -> multiplyCenter.multiplyMatrix(
                matrix = request.matrix,
                secondMatrix = request.secondMatrix,
            )
            else -> OperationResult.Error
        }
    }

    private suspend fun matrixClick(request: RequestUiState.Matrix): OperationResult {
        return if (operation.value is OperationUiState.Determinant) {
            determinantCenter.findDeterminant(request.matrix)
        } else {
            OperationResult.Error
        }
    }

    private fun resolveRequestState(
        size: Int,
        operation: OperationUiState,
    ): RequestUiState {

        return when(operation) {
            is OperationUiState.MultiplyNumber -> RequestUiState.MatrixAndTextField(
                matrix = createMatrixBySize(size),
                number = "0",
            )
            is OperationUiState.Multiply -> RequestUiState.TwoMatrix(
                matrix = createMatrixBySize(size),
                secondMatrix = createMatrixBySize(size),
            )
            is OperationUiState.Add -> RequestUiState.TwoMatrix(
                matrix = createMatrixBySize(size),
                secondMatrix = createMatrixBySize(size),
            )
            is OperationUiState.Determinant -> RequestUiState.Matrix(
                matrix = createMatrixBySize(size)
            )
        }
    }
}