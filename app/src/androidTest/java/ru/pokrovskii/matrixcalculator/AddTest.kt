package ru.pokrovskii.matrixcalculator

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import ru.pokrovskii.matrixcalculator.component.MatrixInput
import ru.pokrovskii.matrixcalculator.component.MatrixOutput
import ru.pokrovskii.matrixcalculator.component.ResultButton
import ru.pokrovskii.matrixcalculator.composables.MainScreen
import ru.pokrovskii.matrixcalculator.state.OperationUiState
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenPresenterImpl
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenViewModel

class AddTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel = MainScreenViewModel()
    private val presenter = MainScreenPresenterImpl(viewModel)

    @Test
    // Тест для работы функции сложения матриц
    fun addTest_common() {

        val matrixInput = MatrixInput(composeTestRule)
        val secondMatrixInput = MatrixInput(
            composeTestRule = composeTestRule,
            prefix = "second_",
        )
        val matrixOutput = MatrixOutput(composeTestRule)
        val findButton = ResultButton(composeTestRule)

        composeTestRule.setContent {
            MainScreen(presenter = presenter)
        }

        // Данные для ввода в первую матрицу
        val firstMatrix = listOf(
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
        )
        // Данные для ввода во вторую матрицу
        val secondMatrix = listOf(
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
        )
        // Ожидаемый результат
        val expected = listOf(
            listOf("2.0", "4.0", "6.0"),
            listOf("2.0", "4.0", "6.0"),
            listOf("2.0", "4.0", "6.0"),
        )

        // Step 1. Установить размер матриц - 3
        presenter.onSizeChanged(3)

        // Step 2. Выбрать функцию сложения
        presenter.onTypeChanged(OperationUiState.Add)

        // Step 3. Поле ввода первой матрицы отобразилось на экране
        matrixInput.assertIsDisplayed()

        // Step 4. Поле ввода второй матрицы отобразилось на экране
        secondMatrixInput.assertIsDisplayed()

        // Step 5. Ввести данные первой матрицы в поле для ввода
        matrixInput.input3x3(firstMatrix)

        // Step 6. Ввести данные второй матрицы в поле для ввода
        secondMatrixInput.input3x3(secondMatrix)

        // Step 7. Нажать на кнопку поиска результата
        findButton.button.performClick()

        // Step 8. Проверить, что результат корректен
        matrixOutput.assertInput3x3(expected)
    }

    @Test
    // Тест для работы функции сложения матриц с ошибкой
    fun addTest_withFail() {

        val matrixInput = MatrixInput(composeTestRule)
        val secondMatrixInput = MatrixInput(
            composeTestRule = composeTestRule,
            prefix = "second_",
        )
        val matrixOutput = MatrixOutput(composeTestRule)
        val findButton = ResultButton(composeTestRule)

        composeTestRule.setContent {
            MainScreen(presenter = presenter)
        }

        // Данные для ввода в первую матрицу
        val firstMatrix = listOf(
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
        )
        // Данные для ввода во вторую матрицу
        val secondMatrix = listOf(
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
        )

        // Step 1. Установить размер матриц - 3
        presenter.onSizeChanged(3)

        // Step 2. Выбрать функцию сложения
        presenter.onTypeChanged(OperationUiState.Add)

        // Step 3. Поле ввода первой матрицы отобразилось на экране
        matrixInput.assertIsDisplayed()

        // Step 4. Поле ввода второй матрицы отобразилось на экране
        secondMatrixInput.assertIsDisplayed()

        // Step 5. Ввести данные первой матрицы в поле для ввода
        matrixInput.input3x3(firstMatrix)

        // Step 6. Ввести данные второй матрицы в поле для ввода
        secondMatrixInput.input3x3(secondMatrix)

        // Step 7. Очистить одно из полей ввода
        matrixInput.setNullTextField()

        // Step 8. Нажать на кнопку поиска результата
        findButton.button.performClick()

        // Step 9. Ожидается, что не случился креш
        matrixInput.assertIsDisplayed()
    }
}