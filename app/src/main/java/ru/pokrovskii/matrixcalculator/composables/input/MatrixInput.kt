package ru.pokrovskii.matrixcalculator.composables.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun MatrixInput(
    currentMatrix: List<List<String>>,
    onTextChanged: (positionX: Int, positionY: Int, newNumber: String) -> Unit,
    modifier: Modifier = Modifier,
    testTagPrefix: String = "",
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        repeat(currentMatrix.size) { indexX ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                val rowSize = currentMatrix.firstOrNull()?.size ?: return@Row
                repeat(rowSize) { indexY ->
                    ShortInputTextField(
                        modifier = Modifier
                            .testTag("${testTagPrefix}matrix_input_${indexX}_${indexY}")
                            .weight(1f),
                        currentText = currentMatrix[indexX][indexY],
                        onTextChanged = { newText ->
                            onTextChanged(
                                indexX,
                                indexY,
                                newText,
                            )
                        },
                        keyboardType = KeyboardType.Decimal,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun MatrixInputPreview() {
    MatrixInput(
        currentMatrix = listOf(listOf("1", "2"), listOf("3", "4")),
        onTextChanged = { _, _, _ -> }
    )
}