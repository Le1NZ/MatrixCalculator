package ru.pokrovskii.matrixcalculator.component

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput

class InputTextField(
    composeTestRule: ComposeContentTestRule,
) {

    val field = composeTestRule.onNodeWithTag("input_text_field")

    fun input(query: String) {
        field.performTextInput(query)
    }
}