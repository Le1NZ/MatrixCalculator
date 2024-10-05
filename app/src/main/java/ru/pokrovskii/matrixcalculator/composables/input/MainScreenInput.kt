package ru.pokrovskii.matrixcalculator.composables.input

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.pokrovskii.matrixcalculator.R
import ru.pokrovskii.matrixcalculator.state.RequestUiState
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenPresenter

internal fun LazyListScope.MainScreenInput(
    presenter: MainScreenPresenter,
) {

    item {
        SizeChanger(
            currentSize = presenter.currentSize.collectAsState().value,
            sizeVariants = presenter.sizeVariants,
            onSizeChanged = presenter::onSizeChanged,
        )
    }
    item {
        OperationChanger(
            currentOperation = presenter.currentOperation.collectAsState().value,
            operationVariants = presenter.operationVariants,
            onVariantChanged = presenter::onTypeChanged,
            modifier = Modifier
                .padding(top = 16.dp),
        )
    }

    item {
        when (val requestState = presenter.requestState.collectAsState().value) {
            is RequestUiState.TwoMatrix -> TwoMatrixInput(
                state = requestState,
                onFirstMatrixChanged = presenter::onFirstMatrixChanged,
                onSecondMatrixChanged = presenter::onSecondMatrixChanged,
            )
            is RequestUiState.MatrixAndTextField -> MatrixAndTextFieldInput(
                state = requestState,
                onMatrixChanged = presenter::onFirstMatrixChanged,
                onNumberChanged = presenter::onNumberChanged,
            )
            is RequestUiState.Matrix -> OneMatrixInput(
                state = requestState,
                onMatrixChanged = presenter::onFirstMatrixChanged,
            )
        }
    }
}

@Composable
private fun TwoMatrixInput(
    state: RequestUiState.TwoMatrix,
    onFirstMatrixChanged: (positionX: Int, positionY: Int, newNumber: String) -> Unit,
    onSecondMatrixChanged: (positionX: Int, positionY: Int, newNumber: String) -> Unit,
) {
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp),
        text = stringResource(id = R.string.matrix_first),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.White,
    )
    MatrixInput(
        modifier = Modifier
            .padding(vertical = 16.dp),
        currentMatrix = state.matrix,
        onTextChanged = onFirstMatrixChanged,
    )
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp),
        text = stringResource(id = R.string.matrix_second),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.White,
    )
    MatrixInput(
        modifier = Modifier
            .padding(bottom = 16.dp),
        currentMatrix = state.secondMatrix,
        onTextChanged = onSecondMatrixChanged,
        testTagPrefix = "second_"
    )
}

@Composable
private fun MatrixAndTextFieldInput(
    state: RequestUiState.MatrixAndTextField,
    onMatrixChanged: (positionX: Int, positionY: Int, newNumber: String) -> Unit,
    onNumberChanged: (newNumber: String) -> Unit,
) {
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp),
        text = stringResource(id = R.string.matrix),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.White,
    )
    MatrixInput(
        modifier = Modifier
            .padding(vertical = 16.dp),
        currentMatrix = state.matrix,
        onTextChanged = onMatrixChanged,
    )
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp),
        text = stringResource(id = R.string.number),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.White,
    )
    InputTextField(
        modifier = Modifier
            .testTag("input_text_field")
            .padding(bottom = 16.dp),
        currentText = state.number,
        onTextChanged = onNumberChanged,
        keyboardType = KeyboardType.Number,
    )
}

@Composable
private fun OneMatrixInput(
    state: RequestUiState.Matrix,
    onMatrixChanged: (positionX: Int, positionY: Int, newNumber: String) -> Unit,
) {
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp),
        text = stringResource(id = R.string.matrix),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.White,
    )
    MatrixInput(
        modifier = Modifier
            .padding(vertical = 16.dp),
        currentMatrix = state.matrix,
        onTextChanged = onMatrixChanged,
    )
}