package ru.pokrovskii.matrixcalculator.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pokrovskii.matrixcalculator.converter.ToFloatConverter
import ru.pokrovskii.matrixcalculator.data.MultiplyRepository
import ru.pokrovskii.matrixcalculator.model.OperationResult

internal class MultiplyCenter(
    private val repository: MultiplyRepository = MultiplyRepository(),
) {

    suspend fun multiplyMatrix(matrix: List<List<String>>, secondMatrix: List<List<String>>): OperationResult {
        return withContext(Dispatchers.IO) {
            OperationResult.Matrix(
                data = repository.multiplyMatrix(
                    matrix = ToFloatConverter.convert(matrix) ?: return@withContext OperationResult.Error,
                    secondMatrix = ToFloatConverter.convert(secondMatrix) ?: return@withContext OperationResult.Error
                )
            )
        }
    }
}