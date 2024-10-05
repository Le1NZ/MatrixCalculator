package ru.pokrovskii.matrixcalculator.composables.output

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.pokrovskii.matrixcalculator.R
import ru.pokrovskii.matrixcalculator.state.ResultUiState
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenPresenter

internal fun LazyListScope.MainScreenOutput(
    presenter: MainScreenPresenter,
) {
    item {
        val state = presenter.resultState.collectAsState().value

        if (state !is ResultUiState.None) {
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                text = stringResource(id = R.string.result),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
            )
        }
        when (state) {
            is ResultUiState.Number -> Number(state)
            is ResultUiState.Matrix -> Matrix(state)
            is ResultUiState.None -> Unit
        }
    }
}

@Composable
private fun Matrix(
    state: ResultUiState.Matrix,
) {
    MatrixOutput(matrix = state.data)
}

@Composable
private fun Number(
    state: ResultUiState.Number,
) {
    Text(
        modifier = Modifier
            .testTag("number_output")
            .padding(vertical = 8.dp),
        text = state.data,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.White,
    )
}