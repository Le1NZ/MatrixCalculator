package ru.pokrovskii.matrixcalculator.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import ru.pokrovskii.matrixcalculator.R
import ru.pokrovskii.matrixcalculator.composables.input.MainScreenInput
import ru.pokrovskii.matrixcalculator.composables.output.MainScreenOutput
import ru.pokrovskii.matrixcalculator.showToast
import ru.pokrovskii.matrixcalculator.state.MainScreenUiEffect
import ru.pokrovskii.matrixcalculator.viewmodel.MainScreenPresenter

@Composable
internal fun MainScreen(
    presenter: MainScreenPresenter,
) {
    ShowUiEffectIfNeeded(effectFlow = presenter.eventFlow)

    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp),
    ) {
        MainScreenInput(presenter = presenter)
        item {
            ResultButton(
                onClick = presenter::findResult,
            )
        }
        MainScreenOutput(presenter = presenter)
    }
}

@Composable
private fun ShowUiEffectIfNeeded(
    effectFlow: Flow<MainScreenUiEffect>,
) {
    val somethingWentWrongMessage = stringResource(id = R.string.something_went_wrong)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is MainScreenUiEffect.SomethingWentWrong -> showToast(
                    context = context,
                    message = somethingWentWrongMessage,
                )
            }
        }
    }
}