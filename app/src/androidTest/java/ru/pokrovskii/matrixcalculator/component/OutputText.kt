package ru.pokrovskii.matrixcalculator.component

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText

class OutputText(
    composeTestRule: ComposeContentTestRule,
) {

    val outputText = composeTestRule.onNodeWithTag("number_output")
}