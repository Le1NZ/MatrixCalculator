package ru.pokrovskii.matrixcalculator.converter

object ToIntConverter {

    fun convert(matrix: List<List<String>>): List<List<Int>>? {
        return matrix.map { it.map { it.toIntOrNull() ?: return null } }
    }
}