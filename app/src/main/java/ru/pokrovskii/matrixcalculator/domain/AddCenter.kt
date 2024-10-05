package ru.pokrovskii.matrixcalculator.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pokrovskii.matrixcalculator.converter.ToFloatConverter
import ru.pokrovskii.matrixcalculator.data.AddRepository
import ru.pokrovskii.matrixcalculator.model.OperationResult

internal class AddCenter(
    private val repository: AddRepository = AddRepository(),
) {

    suspend fun addMatrix(matrix: List<List<String>>, secondMatrix: List<List<String>>): OperationResult {
        return withContext(Dispatchers.IO) {
            OperationResult.Matrix(
                data = repository.addMatrix(
                    matrix = ToFloatConverter.convert(matrix) ?: return@withContext OperationResult.Error,
                    secondMatrix = ToFloatConverter.convert(secondMatrix) ?: return@withContext OperationResult.Error
                )
            )
        }
    }
}