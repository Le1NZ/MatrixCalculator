package ru.pokrovskii.matrixcalculator.component

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextInputSelection
import androidx.compose.ui.text.TextRange

class MatrixInput(
    composeTestRule: ComposeContentTestRule,
    prefix: String = "",
) {

    val matrixInput3x3 = listOf(
        listOf(
            composeTestRule.onNodeWithTag("${prefix}matrix_input_0_0"),
            composeTestRule.onNodeWithTag("${prefix}matrix_input_0_1"),
            composeTestRule.onNodeWithTag("${prefix}matrix_input_0_2"),
        ),
        listOf(
            composeTestRule.onNodeWithTag("${prefix}matrix_input_1_0"),
            composeTestRule.onNodeWithTag("${prefix}matrix_input_1_1"),
            composeTestRule.onNodeWithTag("${prefix}matrix_input_1_2"),
        ),
        listOf(
            composeTestRule.onNodeWithTag("${prefix}matrix_input_2_0"),
            composeTestRule.onNodeWithTag("${prefix}matrix_input_2_1"),
            composeTestRule.onNodeWithTag("${prefix}matrix_input_2_2"),
        ),
    )

    fun input3x3(matrix: List<List<String>>) {
        matrix.forEachIndexed { indexX, row ->
            row.forEachIndexed { indexY, num ->
                matrixInput3x3[indexX][indexY].performTextInput(num)
            }
        }
    }

    fun assertInput3x3(matrix: List<List<String>>) {
        matrix.forEachIndexed { indexX, row ->
            row.forEachIndexed { indexY, num ->
                matrixInput3x3[indexX][indexY].assertTextContains(num)

            }
        }
    }

    fun assertIsDisplayed() {
        matrixInput3x3.forEach { row ->
            row.forEach {
                it.assertIsDisplayed()
            }
        }
    }

    @OptIn(ExperimentalTestApi::class)
    fun setNullTextField() {
        matrixInput3x3[0][0].performTextInputSelection(TextRange.Zero)
        matrixInput3x3[0][0].performTextInput("")
    }
}