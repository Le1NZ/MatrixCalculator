package ru.pokrovskii.matrixcalculator.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pokrovskii.matrixcalculator.converter.ToFloatConverter
import ru.pokrovskii.matrixcalculator.data.DeterminantRepository
import ru.pokrovskii.matrixcalculator.model.OperationResult

internal class DeterminantCenter(
    private val repository: DeterminantRepository = DeterminantRepository(),
) {

    suspend fun findDeterminant(matrix: List<List<String>>): OperationResult {
        return withContext(Dispatchers.IO) {
            OperationResult.Number(
                data = repository.determinantOfMatrix(
                    matrix = ToFloatConverter.convert(matrix) ?: return@withContext OperationResult.Error,
                )
            )
        }
    }
}