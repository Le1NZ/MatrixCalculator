package ru.pokrovskii.matrixcalculator

import org.junit.Test
import org.junit.Assert.*
import ru.pokrovskii.matrixcalculator.data.MultiplyRepository

class MatrixMultiplyTest {

    private val repository = MultiplyRepository()

    @Test
    fun testMultiplicationOfTwoMatrices() {
        val matrix1 = listOf(
            listOf(1.0f, 2.0f),
            listOf(3.0f, 4.0f)
        )
        val matrix2 = listOf(
            listOf(5.0f, 6.0f),
            listOf(7.0f, 8.0f)
        )
        val expected = listOf(
            listOf(19.0f, 22.0f),
            listOf(43.0f, 50.0f)
        )
        assertEquals(expected, repository.multiplyMatrix(matrix1, matrix2))
    }

    @Test
    fun testMultiplicationWithIdentityMatrix() {
        val matrix = listOf(
            listOf(1.0f, 2.0f),
            listOf(3.0f, 4.0f)
        )
        val identityMatrix = listOf(
            listOf(1.0f, 0.0f),
            listOf(0.0f, 1.0f)
        )
        val expected = matrix
        assertEquals(expected, repository.multiplyMatrix(matrix, identityMatrix))
    }

    @Test
    fun testMultiplicationWithZeroMatrix() {
        val matrix = listOf(
            listOf(1.0f, 2.0f),
            listOf(3.0f, 4.0f)
        )
        val zeroMatrix = listOf(
            listOf(0.0f, 0.0f),
            listOf(0.0f, 0.0f)
        )
        val expected = zeroMatrix
        assertEquals(expected, repository.multiplyMatrix(matrix, zeroMatrix))
    }

    @Test
    fun testMultiplicationOfSingleElementMatrices() {
        val matrix1 = listOf(listOf(2.0f))
        val matrix2 = listOf(listOf(3.0f))
        val expected = listOf(listOf(6.0f))
        assertEquals(expected, repository.multiplyMatrix(matrix1, matrix2))
    }

    @Test
    fun testMultiplicationOfSingleRowAndColumnMatrices() {
        val matrix1 = listOf(
            listOf(1.0f, 2.0f, 3.0f)
        )
        val matrix2 = listOf(
            listOf(4.0f),
            listOf(5.0f),
            listOf(6.0f)
        )
        val expected = listOf(
            listOf(32.0f)
        )
        assertEquals(expected, repository.multiplyMatrix(matrix1, matrix2))
    }
}