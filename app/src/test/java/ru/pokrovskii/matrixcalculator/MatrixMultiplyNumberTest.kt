package ru.pokrovskii.matrixcalculator

import org.junit.Test
import org.junit.Assert.*
import ru.pokrovskii.matrixcalculator.data.MultiplyNumberRepository

class MatrixMultiplyNumberTest {

    private val repository = MultiplyNumberRepository()

    @Test
    fun testMultiplyMatrixByZero() {
        val matrix = listOf(
            listOf(1.0f, 2.0f),
            listOf(3.0f, 4.0f)
        )
        val number = 0.0f
        val expected = listOf(
            listOf(0.0f, 0.0f),
            listOf(0.0f, 0.0f)
        )
        assertEquals(expected, repository.multiplyNumber(matrix, number))
    }

    @Test
    fun testMultiplyMatrixByOne() {
        val matrix = listOf(
            listOf(1.0f, 2.0f),
            listOf(3.0f, 4.0f)
        )
        val number = 1.0f
        val expected = matrix
        assertEquals(expected, repository.multiplyNumber(matrix, number))
    }

    @Test
    fun testMultiplyMatrixByPositiveNumber() {
        val matrix = listOf(
            listOf(1.0f, 2.0f),
            listOf(3.0f, 4.0f)
        )
        val number = 2.0f
        val expected = listOf(
            listOf(2.0f, 4.0f),
            listOf(6.0f, 8.0f)
        )
        assertEquals(expected, repository.multiplyNumber(matrix, number))
    }

    @Test
    fun testMultiplyMatrixByNegativeNumber() {
        val matrix = listOf(
            listOf(1.0f, -2.0f),
            listOf(-3.0f, 4.0f)
        )
        val number = -1.0f
        val expected = listOf(
            listOf(-1.0f, 2.0f),
            listOf(3.0f, -4.0f)
        )
        assertEquals(expected, repository.multiplyNumber(matrix, number))
    }

    @Test
    fun testMultiplyEmptyMatrix() {
        val matrix = listOf<List<Float>>()
        val number = 2.0f
        val expected = listOf<List<Float>>()
        assertEquals(expected, repository.multiplyNumber(matrix, number))
    }
}