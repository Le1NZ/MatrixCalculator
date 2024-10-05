package ru.pokrovskii.matrixcalculator.converter

import ru.pokrovskii.matrixcalculator.model.OperationResult
import ru.pokrovskii.matrixcalculator.state.ResultUiState

internal object ToResultUiState {

    fun convert(state: OperationResult): ResultUiState {
        return when (state) {
            is OperationResult.Error -> ResultUiState.None
            is OperationResult.Matrix -> ResultUiState.Matrix(
                data = ToStringConverter.convert(state.data),
            )
            is OperationResult.Number -> ResultUiState.Number(
                data = state.data.toString(),
            )
        }
    }
}