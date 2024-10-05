package ru.pokrovskii.matrixcalculator.converter

object ToFloatConverter {

    fun convert(matrix: List<List<String>>): List<List<Float>>? {
        return matrix.map { it.map { it.toFloatOrNull() ?: return null } }
    }
}