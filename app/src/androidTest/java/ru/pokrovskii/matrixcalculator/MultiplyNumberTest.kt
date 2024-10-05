package ru.pokrovskii.matrixcalculator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import ru.pokrovskii.matrixcalculator.component.InputTextField
import ru.pokrovskii.matrixcalculator.component.MatrixInput
import ru.pokrovskii.matrixcalculator.component.MatrixOutput
import ru.pokrovskii.matrixcalculator.component.ResultButton
import ru.pokrovskii.matrixcalculator.composables.MainScreen
import ru.pokrovskii.matrixcalculator.state.OperationUiState
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenPresenterImpl
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenViewModel

class MultiplyNumberTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel = MainScreenViewModel()
    private val presenter = MainScreenPresenterImpl(viewModel)

    @Test
    // Тест для работы функции умножения матрицы на число
    fun multiplyNumberTest_common() {

        val matrixInput = MatrixInput(composeTestRule)
        val inputTextField = InputTextField(composeTestRule)
        val matrixOutput = MatrixOutput(composeTestRule)
        val findButton = ResultButton(composeTestRule)

        composeTestRule.setContent {
            MainScreen(presenter = presenter)
        }

        // Данные для ввода в матрицу
        val firstMatrix = listOf(
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
        )
        // Ожидаемый результат
        val expected = listOf(
            listOf("6.0", "12.0", "18.0"),
            listOf("6.0", "12.0", "18.0"),
            listOf("6.0", "12.0", "18.0"),
        )

        // Step 1. Установить размер матриц - 3
        presenter.onSizeChanged(3)

        // Step 2. Выбрать функцию умножения на число
        presenter.onTypeChanged(OperationUiState.MultiplyNumber)

        // Step 3. Поле ввода матрицы отобразилось на экране
        matrixInput.assertIsDisplayed()

        // Step 4. Поле ввода для числа отобразилось на экране
        inputTextField.field.assertIsDisplayed()

        // Step 5. Ввести данные матрицы в поле для ввода
        matrixInput.input3x3(firstMatrix)

        // Step 6. Ввести число для умножения в поле для ввода
        inputTextField.input("6")

        // Step 7. Нажать на кнопку поиска результата
        findButton.button.performClick()

        // Step 8. Проверить, что результат корректен
        matrixOutput.assertInput3x3(expected)
    }
}