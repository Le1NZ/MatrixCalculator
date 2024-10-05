package ru.pokrovskii.matrixcalculator.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pokrovskii.matrixcalculator.converter.ToFloatConverter
import ru.pokrovskii.matrixcalculator.data.MultiplyNumberRepository
import ru.pokrovskii.matrixcalculator.model.OperationResult

internal class MultiplyNumberCenter(
    private val repository: MultiplyNumberRepository = MultiplyNumberRepository(),
) {

    suspend fun multiplyNumber(matrix: List<List<String>>, number: String): OperationResult {
        return withContext(Dispatchers.IO) {
            OperationResult.Matrix(
                data = repository.multiplyNumber(
                    matrix = ToFloatConverter.convert(matrix) ?: return@withContext OperationResult.Error,
                    number = number.toFloatOrNull() ?: return@withContext OperationResult.Error,
                )
            )
        }
    }
}