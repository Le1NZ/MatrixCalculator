package ru.pokrovskii.matrixcalculator.state

internal sealed interface ResultUiState {

    data object None : ResultUiState

    data class Matrix(
        val data: List<List<String>>,
    ) : ResultUiState

    data class Number(
        val data: String,
    ) : ResultUiState
}