package ru.pokrovskii.matrixcalculator.model

internal sealed interface OperationResult {

    data class Number(
        val data: Float,
    ) : OperationResult

    data class Matrix(
        val data: List<List<Float>>,
    ) : OperationResult

    data object Error : OperationResult
}