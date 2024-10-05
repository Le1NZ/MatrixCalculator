package ru.pokrovskii.matrixcalculator.state

internal sealed interface RequestUiState {

    val matrix: MutableList<MutableList<String>>

    data class MatrixAndTextField(
        override val matrix: MutableList<MutableList<String>>,
        val number: String,
    ) : RequestUiState

    data class TwoMatrix(
        override val matrix: MutableList<MutableList<String>>,
        val secondMatrix: MutableList<MutableList<String>>,
    ) : RequestUiState

    data class Matrix (
        override val matrix: MutableList<MutableList<String>>,
    ) : RequestUiState
}

internal val SIZE_VARIANTS = listOf(
    1,
    2,
    3,
    4,
)