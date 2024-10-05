package ru.pokrovskii.matrixcalculator.converter

object ToStringConverter {

    fun convert(matrix: List<List<Float>>): List<List<String>> {
        return matrix.map { it.map { it.toString() } }
    }
}