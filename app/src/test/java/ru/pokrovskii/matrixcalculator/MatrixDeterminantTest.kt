package ru.pokrovskii.matrixcalculator

import org.junit.Test
import org.junit.Assert.*
import ru.pokrovskii.matrixcalculator.data.DeterminantRepository

class MatrixDeterminantTest {

    private val repository = DeterminantRepository()

    @Test
    fun testDeterminantOf1x1Matrix() {
        val matrix = listOf(
            listOf(5.0f)
        )
        val expected = 5.0f
        assertEquals(expected, repository.determinantOfMatrix(matrix), 1e-6f)
    }

    @Test
    fun testDeterminantOf2x2Matrix() {
        val matrix = listOf(
            listOf(1.0f, 2.0f),
            listOf(3.0f, 4.0f)
        )
        val expected = -2.0f
        assertEquals(expected, repository.determinantOfMatrix(matrix), 1e-6f)
    }

    @Test
    fun testDeterminantOf3x3Matrix() {
        val matrix = listOf(
            listOf(6.0f, 1.0f, 1.0f),
            listOf(4.0f, -2.0f, 5.0f),
            listOf(2.0f, 8.0f, 7.0f)
        )
        val expected = -306.0f
        assertEquals(expected, repository.determinantOfMatrix(matrix), 1e-6f)
    }

    @Test
    fun testDeterminantOfZeroMatrix() {
        val matrix = listOf(
            listOf(0.0f, 0.0f),
            listOf(0.0f, 0.0f)
        )
        val expected = 0.0f
        assertEquals(expected, repository.determinantOfMatrix(matrix), 1e-6f)
    }

    @Test
    fun testDeterminantOfIdentityMatrix() {
        val matrix = listOf(
            listOf(1.0f, 0.0f, 0.0f),
            listOf(0.0f, 1.0f, 0.0f),
            listOf(0.0f, 0.0f, 1.0f)
        )
        val expected = 1.0f
        assertEquals(expected, repository.determinantOfMatrix(matrix), 1e-6f)
    }

    @Test
    fun testDeterminantOfSingularMatrix() {
        val matrix = listOf(
            listOf(2.0f, 3.0f),
            listOf(2.0f, 3.0f)
        )
        val expected = 0.0f
        assertEquals(expected, repository.determinantOfMatrix(matrix), 1e-6f)
    }
}