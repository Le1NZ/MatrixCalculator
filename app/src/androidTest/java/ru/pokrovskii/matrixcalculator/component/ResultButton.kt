package ru.pokrovskii.matrixcalculator.component

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag

class ResultButton(
    composeTestRule: ComposeContentTestRule,
) {

    val button = composeTestRule.onNodeWithTag("find_button")
}