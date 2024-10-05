package ru.pokrovskii.matrixcalculator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import ru.pokrovskii.matrixcalculator.component.MatrixInput
import ru.pokrovskii.matrixcalculator.component.OutputText
import ru.pokrovskii.matrixcalculator.component.ResultButton
import ru.pokrovskii.matrixcalculator.composables.MainScreen
import ru.pokrovskii.matrixcalculator.state.OperationUiState
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenPresenterImpl
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenViewModel

class DeterminantTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel = MainScreenViewModel()
    private val presenter = MainScreenPresenterImpl(viewModel)

    @Test
    // Тест для работы функции нахождения определителя матрицы
    fun multiplyNumberTest_common() {

        val matrixInput = MatrixInput(composeTestRule)
        val findButton = ResultButton(composeTestRule)
        val outputText = OutputText(composeTestRule)

        composeTestRule.setContent {
            MainScreen(presenter = presenter)
        }

        // Данные для ввода в матрицу
        val firstMatrix = listOf(
            listOf("1", "2", "3"),
            listOf("2", "2", "6"),
            listOf("4", "8", "5"),
        )
        // Ожидаемый результат
        val expected = "14.0"

        // Step 1. Установить размер матриц - 3
        presenter.onSizeChanged(3)

        // Step 2. Выбрать функцию нахождения определителя
        presenter.onTypeChanged(OperationUiState.Determinant)

        // Step 3. Поле ввода матрицы отобразилось на экране
        matrixInput.assertIsDisplayed()

        // Step 4. Ввести данные матрицы в поле для ввода
        matrixInput.input3x3(firstMatrix)

        // Step 6. Нажать на кнопку поиска результата
        findButton.button.performClick()

        // Step 7. Поле вывода результата отобразилось на экране
        outputText.outputText.assertIsDisplayed()

        // Step 8. Проверить, что результат корректен
        outputText.outputText.assertTextEquals(expected)
    }
}