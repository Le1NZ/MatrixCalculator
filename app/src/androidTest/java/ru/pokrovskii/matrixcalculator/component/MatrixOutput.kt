package ru.pokrovskii.matrixcalculator.component

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag

class MatrixOutput(
    composeTestRule: ComposeContentTestRule,
) {

    val matrixOutput3x3 = listOf(
        listOf(
            composeTestRule.onNodeWithTag("matrix_output_0_0"),
            composeTestRule.onNodeWithTag("matrix_output_0_1"),
            composeTestRule.onNodeWithTag("matrix_output_0_2"),
        ),
        listOf(
            composeTestRule.onNodeWithTag("matrix_output_1_0"),
            composeTestRule.onNodeWithTag("matrix_output_1_1"),
            composeTestRule.onNodeWithTag("matrix_output_1_2"),
        ),
        listOf(
            composeTestRule.onNodeWithTag("matrix_output_2_0"),
            composeTestRule.onNodeWithTag("matrix_output_2_1"),
            composeTestRule.onNodeWithTag("matrix_output_2_2"),
        ),
    )

    fun assertInput3x3(matrix: List<List<String>>) {
        matrix.forEachIndexed { indexX, row ->
            row.forEachIndexed { indexY, num ->
                matrixOutput3x3[indexX][indexY].assertTextEquals(num)
            }
        }
    }
}