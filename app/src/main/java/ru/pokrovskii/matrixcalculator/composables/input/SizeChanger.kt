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
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.pokrovskii.matrixcalculator.R

@Composable
internal fun SizeChanger(
    currentSize: Int,
    sizeVariants: List<Int>,
    onSizeChanged: (Int) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = R.string.choose_size),
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
            text = currentSize.toString(),
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
            sizeVariants.forEach {
                Item(
                    variant = it,
                    onSizeChanged = {
                        onSizeChanged(it)
                        isExpanded = false
                    },
                )
            }
        }
    }
}

@Composable
private fun Item(
    variant: Int,
    onSizeChanged: (Int) -> Unit,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onSizeChanged(variant) },
            )
            .padding(16.dp),
        text = variant.toString(),
        fontWeight = FontWeight.Bold,
    )
}

@Preview
@Composable
private fun ItemPreview() {
    SizeChanger(
        currentSize = 1,
        sizeVariants = listOf(1, 2),
        onSizeChanged = {},
    )
}