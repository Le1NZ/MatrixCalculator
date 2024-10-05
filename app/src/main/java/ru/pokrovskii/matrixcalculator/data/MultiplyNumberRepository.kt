package ru.pokrovskii.matrixcalculator.data

class MultiplyNumberRepository {

    fun multiplyNumber(matrix: List<List<Float>>, number: Float): List<List<Float>> {
        return matrix.map { row ->
            row.map { it * number }
        }
    }
}