package ru.pokrovskii.matrixcalculator.composables.output

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun MatrixOutput(
    matrix: List<List<String>>,
) {
    Column {
        matrix.forEachIndexed { indexX, row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                row.forEachIndexed { indexY, element ->
                    Text(
                        modifier = Modifier
                            .testTag("matrix_output_${indexX}_${indexY}")
                            .padding(8.dp)
                            .weight(1f),
                        text = element,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun MatrixOutputPreview() {
    MatrixOutput(
        matrix = listOf(listOf("1", "2"), listOf("3", "4")),
    )
}