package ru.pokrovskii.matrixcalculator.composables.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.pokrovskii.matrixcalculator.R
import ru.pokrovskii.matrixcalculator.state.OperationUiState

@Composable
internal fun OperationChanger(
    currentOperation: OperationUiState,
    operationVariants: List<OperationUiState>,
    onVariantChanged: (OperationUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.choose_operation_type),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White,
        )

        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 8.dp)
                .clickable {
                    isExpanded = true
                }
                .clip(CircleShape)
                .background(Color.White)
                .padding(vertical = 16.dp)
                .padding(start = 16.dp)
                .fillMaxWidth(),
            text = stringResource(id = currentOperation.name),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )

        DropdownMenu(
            modifier = Modifier
                .padding(start = 16.dp)
                .wrapContentSize(),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            operationVariants.forEach { variant ->
                Item(
                    variant = variant,
                    onVariantChanged = {
                        onVariantChanged(it)
                        isExpanded = false
                    },
                )
            }
        }
    }
}

@Composable
private fun Item(
    variant: OperationUiState,
    onVariantChanged: (OperationUiState) -> Unit,
) {
    Text(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(
                onClick = { onVariantChanged(variant) },
            ),
        text = stringResource(id = variant.name),
        fontWeight = FontWeight.Bold,
    )
}