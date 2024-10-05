package ru.pokrovskii.matrixcalculator

import org.junit.Test

import org.junit.Assert.*
import ru.pokrovskii.matrixcalculator.data.AddRepository

class MatrixAddTest {

    private val repository = AddRepository()

    @Test
    fun testAdditionOfTwoMatrices() {
        val matrix1 = listOf(
            listOf(1.0f, 2.0f),
            listOf(3.0f, 4.0f)
        )
        val matrix2 = listOf(
            listOf(5.0f, 6.0f),
            listOf(7.0f, 8.0f)
        )
        val expected = listOf(
            listOf(6.0f, 8.0f),
            listOf(10.0f, 12.0f)
        )
        assertEquals(expected, repository.addMatrix(matrix1, matrix2))
    }

    @Test
    fun testAdditionOfEmptyMatrices() {
        val matrix1 = listOf<List<Float>>()
        val matrix2 = listOf<List<Float>>()
        val expected = listOf<List<Float>>()
        assertEquals(expected, repository.addMatrix(matrix1, matrix2))
    }

    @Test
    fun testAdditionWithZeroMatrix() {
        val matrix1 = listOf(
            listOf(1.0f, 2.0f),
            listOf(3.0f, 4.0f)
        )
        val zeroMatrix = listOf(
            listOf(0.0f, 0.0f),
            listOf(0.0f, 0.0f)
        )
        val expected = listOf(
            listOf(1.0f, 2.0f),
            listOf(3.0f, 4.0f)
        )
        assertEquals(expected, repository.addMatrix(matrix1, zeroMatrix))
    }

    @Test
    fun testAdditionOfSingleElementMatrices() {
        val matrix1 = listOf(listOf(5.0f))
        val matrix2 = listOf(listOf(10.0f))
        val expected = listOf(listOf(15.0f))
        assertEquals(expected, repository.addMatrix(matrix1, matrix2))
    }

    @Test
    fun testAdditionOfSingleRowMatrices() {
        val matrix1 = listOf(listOf(1.0f, 2.0f, 3.0f))
        val matrix2 = listOf(listOf(4.0f, 5.0f, 6.0f))
        val expected = listOf(listOf(5.0f, 7.0f, 9.0f))
        assertEquals(expected, repository.addMatrix(matrix1, matrix2))
    }

    @Test
    fun testAdditionOfSingleColumnMatrices() {
        val matrix1 = listOf(
            listOf(1.0f),
            listOf(2.0f),
            listOf(3.0f)
        )
        val matrix2 = listOf(
            listOf(4.0f),
            listOf(5.0f),
            listOf(6.0f)
        )
        val expected = listOf(
            listOf(5.0f),
            listOf(7.0f),
            listOf(9.0f)
        )
        assertEquals(expected, repository.addMatrix(matrix1, matrix2))
    }
}